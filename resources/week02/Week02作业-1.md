### 1. 使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例



#### 串行GC - 128m

```sh
> cd E:\gk_java_tc\working-area\k-jvm\out\production\classes
> java -XX:+UseSerialGC -XX:+PrintGCDetails -Xloggc:gc.ser128.log -Xmx128m -Xms128m org.kayla.jvm.GCLogAnalysis
```

| **Generation**           | **Allocated** | **Peak** |
| :----------------------- | :------------ | :------- |
| Young Generation         | 38.38 mb      | 34.11 mb |
| Old Generation           | 85.38 mb      | n/a      |
| Meta Space               | 4.75 mb       | 438 kb   |
| Young + Old + Meta space | 127.75 mb     | 34.43 mb |





#### 串行GC - 64m

```sh
> java -XX:+UseSerialGC -XX:+PrintGCDetails -Xloggc:gc.ser64.log -Xmx64m -Xms64m org.kayla.jvm.GCLogAnalysis
```

| **Generation**           | **Allocated** | **Peak** |
| :----------------------- | :------------ | :------- |
| Young Generation         | 19.19 mb      | 19.08 mb |
| Old Generation           | 42.69 mb      | 3.79 mb  |
| Meta Space               | 4.75 mb       | 439 kb   |
| Young + Old + Meta space | 65.75 mb      | 22.43 mb |



#### 并行GC - 128

```sh
> java -XX:+UseParallelGC -XX:+PrintGCDetails -Xloggc:gc.par128.log -Xmx128m -Xms128m org.kayla.jvm.GCLogAnalysis
```

| **Generation**           | **Allocated** | **Peak** |
| :----------------------- | :------------ | :------- |
| Young Generation         | 37.5 mb       | 32.5 mb  |
| Old Generation           | 85.5 mb       | n/a      |
| Meta Space               | 4.75 mb       | 446 kb   |
| Young + Old + Meta space | 127.75 mb     | 32.44 mb |





#### 并行GC - 64

```sh
> java -XX:+UseParallelGC -XX:+PrintGCDetails -Xloggc:gc.par64.log -Xmx64m -Xms64m org.kayla.jvm.GCLogAnalysis
```

| **Generation**           | **Allocated** | **Peak** |
| :----------------------- | :------------ | :------- |
| Young Generation         | 18.5 mb       | 18.44 mb |
| Old Generation           | 43 mb         | 4.61 mb  |
| Meta Space               | 4.75 mb       | 444 kb   |
| Young + Old + Meta space | 65.75 mb      | 23.43 mb |



#### CMS  GC - 128

```sh
> java -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -Xloggc:gc.cms128.log -Xmx128m -Xms128m org.kayla.jvm.GCLogAnalysis
```



#### CMS  GC - 64

```sh
> java -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -Xloggc:gc.cms64.log -Xmx64m -Xms64m org.kayla.jvm.GCLogAnalysis
```



#### G1 GC - 128

```sh
> java -XX:+UseG1GC -XX:+PrintGCDetails -Xloggc:gc.g1128.log -Xmx128m -Xms128m org.kayla.jvm.GCLogAnalysis
```



#### G1 GC - 64

```sh
> java -XX:+UseG1GC -XX:+PrintGCDetails -Xloggc:gc.g164.log -Xmx64m -Xms64m org.kayla.jvm.GCLogAnalysis
```


