# geektime_Java进阶训练营

## Week 01

1. [XlassClassLoader.java](https://github.com/J-doIt/geektime_java_tc/blob/main/k-jvm/src/main/java/org/kayla/jvm/XlassClassLoader.java)
2. [内存参数关系图](https://github.com/J-doIt/geektime_java_tc/tree/main/k-jvm/src/main/resources)
   1.  JVM_JMM.png
   2.  JVM_JMM_补充.png

## Week 02

1. 使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例
2. 使用压测工具，演练 gateway-server-0.0.1-SNAPSHOT.jar 示例
3. 如果自己本地有可以运行的项目，可以按照 2 的方式进行演练
4. [ （必做）根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结 ](https://github.com/J-doIt/geektime_java_tc/blob/main/k-jvm/src/main/resources/Week02作业-4.md)
5. 运行课上的例子，以及 Netty 的例子，分析相关现象
6. [ （必做）使用 HttpClient 访问给定的URL ](https://github.com/J-doIt/geektime_java_tc/tree/main/k-nio/src/main/java/org/kayla/nio/httpclient/client)
   1.  HttpClient.java (以GET请求的方式请求"http://127.0.0.1:8808/")
   2.  HttpClientHelper.java (从线程池获取客户端并使用长连接，去请求"http://127.0.0.1:8808/test")
