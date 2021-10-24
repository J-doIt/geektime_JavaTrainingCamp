

# JDBC 驱动



## 桥接模式：Bridge Design Pattern

> “Decouple an abstraction from its implementation so that the two can vary independently .”（GoF）
> 将抽象和实现解耦，让它们可以独立变化。
>
> 另一种理解方式：“一个类存在两个（或多个）独立变化的维度；通过组合的方式，让这两个（或多个）维度可以独立进行扩展。



## JDBC 驱动是桥接模式的经典应用：



### 如何利用 JDBC 驱动来查询数据库



```java
Class.forName("com.mysql.jdbc.Driver");//加载及注册JDBC驱动程序
String url = "jdbc:mysql://localhost:3306/sample_db?user=root&password=your_password";
Connection con = DriverManager.getConnection(url);
Statement stmt = con.createStatement();
String query = "select * from test";
ResultSet rs=stmt.executeQuery(query);
while(rs.next()) {
	rs.getString(1);
	rs.getInt(2);
}
```

- 如果把 MySQL 数据库换成 Oracle 数据库，只需要把第一行代码中的 `com.mysql.jdbc.Driver` 换成 `oracle.jdbc.driver.OracleDriver` 就可以了；或者直接改配置文件；



### 优雅的数据库切换



```java
package com.mysql.jdbc;
import java.sql.SQLException;

public class Driver extends NonRegisteringDriver implements java.sql.Driver {
	static {
		try {
			// 将 Driver 注册到 DriverManager 类中
			java.sql.DriverManager.registerDriver(new Driver());
		} catch (SQLException E) {
			throw new RuntimeException("Can't register driver!");
		}
	}

	/**
	* Construct a new driver and register it with DriverManager.
	* @throws SQLException if a database error occurs.
	*/
	public Driver() throws SQLException {
		// Required for Class.forName().newInstance()
	}
}
```



当执行 `Class.forName(“com.mysql.jdbc.Driver”)` 的时候做了两件事：

1. 第一件事：要求 JVM **查找**并**加载**指定的 Driver 类；
2. 第二件事：执行该类的静态代码，也就是将 MySQL Driver 注册到 DriverManager 类中。



#### **DriverManager** 类

```java
/**
 * The basic service for managing a set of JDBC drivers.
 * 用于管理一组 JDBC 驱动程序的基本服务。
 */
public class DriverManager {
	// 组合：*抽象*（JDBC）和 *实现*（Driver） 独立开发，通过对象之间的组合关系，组装在一起；
	// List of registered JDBC drivers. 已注册的 JDBC 驱动程序列表
	private final static CopyOnWriteArrayList<DriverInfo> registeredDrivers = new CopyOnWriteArrayList<DriverInfo>();
	
	//...
	static {
		loadInitialDrivers();
		println("JDBC DriverManager initialized");
	}
	//...
	
	// registerDriver(driver, da: null);
	public static synchronized void registerDriver(java.sql.Driver driver) throws SQLException {
		if (driver != null) {
			// DriverInfo：注册驱动程序的包装类(final Driver driver; 和 DriverAction da;)
			// DriverAction：当Driver希望被DriverManager通知时必须实现的接口；DriverManager.registerDriver(Driver, DriverAction)。
			registeredDrivers.addIfAbsent(new DriverInfo(driver));
		} else {
			throw new NullPointerException();
		}
	}
	
	public static Connection getConnection(String url, String user, String password) throws SQLException {
		java.util.Properties info = new java.util.Properties();
		if (user != null) {
			info.put("user", user);
		}
			if (password != null) {
			info.put("password", password);
		}
		return (getConnection(url, info, Reflection.getCallerClass()));
	}
	//...
}
```



> 可以灵活切换 Driver 的原因：
>
> 把 **Driver 实现类**注册到 DriverManager 之后（ `DriverManager.registerDriver(new Driver()) `），后续所有对 JDBC 接口的调用，都会**委派**到 Driver 实现类来执行。而 Driver 实现类都**实现了相同的接口**（`java.sql.Driver `），这也是可以灵活切换 Driver 的原因。

<img src="https://github.com/J-doIt/geektime_java_tc/blob/main/resources/week05/img_2.png" alt="java.sql.Driver 源码" style="zoom:50%;" />


#### 理解桥接模式的关键

桥接模式的定义是“将抽象和实现解耦，让它们可以独立变化”。那弄懂定义中“抽象”和“实现”两个概念，就是理解桥接模式的关键。


那在 JDBC 这个例子中，什么是"抽象"？什么是"实现"呢？
- 实际上，JDBC 本身就相当于“抽象”（标准、规范、协议、约定......）。这里所说的“抽象”，指的并非“抽象类”或“接口”，而是跟具体的数据库无关的、被抽象出来的一套“类库”。具体的 Driver（比如，com.mysql.jdbc.Driver）就相当于“实现”（根据“协议、规范”开发的中间件、库、框架......）。注意，这里所说的“实现”，也并非指“接口的实现类”，而是跟具体数据库相关的一套“类库”。JDBC 和 Driver **独立开发，通过对象之间的组合关系，组装在一起（**`DriverManager.registeredDrivers` **）。JDBC 的所有逻辑操作，最终都委托给 Driver 来执行**。

<img src="https://github.com/J-doIt/geektime_java_tc/blob/main/resources/week05/img_1.png" alt="JDBC 接口与具体的实现类 Driver 关系图" style="zoom:50%;" />
