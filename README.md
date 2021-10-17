# geektime_Java进阶训练营

## Week 01

1. [XlassClassLoader.java](https://github.com/J-doIt/geektime_java_tc/blob/main/k-jvm/src/main/java/org/kayla/jvm/XlassClassLoader.java)
2. [内存参数关系图](https://github.com/J-doIt/geektime_java_tc/tree/main/resources/week01)
   1.  JVM_JMM.png
   2.  JVM_JMM_补充.png

## Week 02

1. [ 使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例 < 持续补充中 >  ](https://github.com/J-doIt/geektime_java_tc/blob/main/resources/week02/Week02%E4%BD%9C%E4%B8%9A-1.md)
2. [ 使用压测工具，演练 gateway-server-0.0.1-SNAPSHOT.jar 示例 < 持续补充中 > ](https://github.com/J-doIt/geektime_java_tc/blob/main/resources/week02/Week02%E4%BD%9C%E4%B8%9A-2.md)
3. [ 如果自己本地有可以运行的项目，可以按照 2 的方式进行演练 < 持续补充中 > ](https://github.com/J-doIt/geektime_java_tc/blob/main/resources/week02/Week02%E4%BD%9C%E4%B8%9A-3.md)
4. [ * 根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结 < 持续补充中 > ](https://github.com/J-doIt/geektime_java_tc/blob/main/resources/week02/Week02%E4%BD%9C%E4%B8%9A-4.md)
5. 运行课上的例子，以及 Netty 的例子，分析相关现象
6. [ * 使用 HttpClient 访问给定的URL ](https://github.com/J-doIt/geektime_java_tc/tree/main/k-nio/src/main/java/org/kayla/nio/httpclient/client)
   1.  HttpClient.java (以GET请求的方式请求`http://127.0.0.1:8808/`)
   2.  HttpClientHelper.java (从线程池获取客户端并使用长连接，去请求`http://127.0.0.1:8808/test`)

## Week 03
1. [ * 整合上次作业的 httpclient/okhttp ]()
   - 在提示的基础上，整合前面的作业里去实现的客户端，去真实地去调用一个后端的业务服务，然后再把它的响应结果返回给前端的客户端；
2. 使用 netty 实现后端 http 访问（代替上一步骤）
   - 使用 Netty 创建一个客户端，去访问后端的业务服务，然后拿到响应的结果；
3. [ * 实现过滤器 ](https://github.com/J-doIt/geektime_java_tc/blob/main/k-nio-02/src/main/java/org/kayla/gateway/NettyServerApplication.java)
   - 按照给大家提示写的Filter的接口去实现一个Filter；比如说对所有请求做一次过滤，添加一个请求头，再发给后端的业务服务；比如给请求头里面加一个 xjava 这么个 key，然后通过 HpptClient/OkHttp 请求后端的具体的真实的业务服务的时候，把这个头带进去；
4. 实现路由
   - 让后端 可以同时支撑多个业务服务的实例，让它们能够做负载均衡；
5. 跑一跑课上的各个例子，加深对多线程的理解
6. 完善网关的例子，试着调整其中的线程池参数
7. ++ 实现一个http文件服务器和一个ftp文件服务器

## Week 04 - 并发编程
- [Week04 作业](https://github.com/J-doIt/geektime_java_tc/tree/main/k-concurrency/src/main/java/org/kayla/concurrency)