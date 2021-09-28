# 如果自己本地有可以运行的项目，可以按照 2 的方式进行演练



BaseAction.java 解析一个保存了User类型的json配置文件，并过滤User的敏感信息。



5.29 mb 的文件

gc.s.64.1.log

gc策略  Xmx  第n次 



导致结果不一致的多个变量：

- 处理的文件的大小
- gc策略
- 分配的堆空间
- 第n次执行
- 线程个数
- -Xms 和 -Xmx 配置是否相同
- ....



## 1. 同一个配置执行多次，查看结果是否存在巨大差异：

#### 串行GC

```sh
> D:\Java\jdk1.8.0_131\bin\java -XX:+UseSerialGC -XX:+PrintGCDetails -Xloggc:gc.s.64.1.log -Xmx64m -Xms64m com.agree.framework.web.action.base.BaseAction
```



#### 并行GC

```sh
> java -XX:+UseParallelGC -XX:+PrintGCDetails -Xloggc:gc.p.64.1.log -Xmx64m -Xms64m org.kayla.jvm.GCLogAnalysis
```



堆栈溢出

```java
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOfRange(Arrays.java:3664)
	at java.lang.StringBuffer.toString(StringBuffer.java:669)
	at java.io.StringWriter.toString(StringWriter.java:210)
	at com.google.gson.JsonElement.toString(JsonElement.java:317)
	at com.agree.framework.web.action.base.BaseAction.main(BaseAction.java:311)

进程已结束，退出代码为 1
```




#### CMS  GC

```sh
> java -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -Xloggc:gc.c.64.1.log -Xmx64m -Xms64m org.kayla.jvm.GCLogAnalysis
```





#### G1 GC

```sh
> java -XX:+UseG1GC -XX:+PrintGCDetails -Xloggc:gc.g.64.1.log -Xmx64m -Xms64m org.kayla.jvm.GCLogAnalysis
```




