### 2. 使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例



环境及内存配置：jdk1.8.0_131，-Xmx512m -Xms512m

 

串行GC

![1632672423906](https://github.com/J-doIt/geektime_java_tc/blob/main/k-jvm/src/main/resources/img/SerGC-1.jpg)



并行GC

![1632672452536](https://github.com/J-doIt/geektime_java_tc/blob/main/k-jvm/src/main/resources/img/ParGC-1.jpg)


CMS GC

![img](https://github.com/J-doIt/geektime_java_tc/blob/main/k-jvm/src/main/resources/img/CMSGC-1.jpg) 

G1 GC

![img](https://github.com/J-doIt/geektime_java_tc/blob/main/k-jvm/src/main/resources/img/G1GC-1.jpg) 

 

总结（JVM整个堆内存大小分配）：

- 串行GC、并行GC、CMS GC相差不多；


- 但区别最大的是G1 GC，它的内存分配要比其他三个的多分配大约0.3倍，这很可能是因为G1 GC中不再分为年轻代和老年代，而是划分成了多个小块堆区域，每个小块在不同的时间，都有可能存放不同特性的数据。


 

 

 

串行GC

![img](https://github.com/J-doIt/geektime_java_tc/blob/main/k-jvm/src/main/resources/img/SerGC-2.jpg) 

并行GC

![img](https://github.com/J-doIt/geektime_java_tc/blob/main/k-jvm/src/main/resources/img/ParGC-2.jpg) 

CMS GC

![img](https://github.com/J-doIt/geektime_java_tc/blob/main/k-jvm/src/main/resources/img/CMSGC-2.jpg) 

G1 GC

![img](https://github.com/J-doIt/geektime_java_tc/blob/main/k-jvm/src/main/resources/img/G1GC-2.jpg) 



总结（GC次数）：

- CMS GC、G1 GC的GC暂停时间都没有超过20毫秒；其中CMS GC的GC 暂停时间约99%控制在`0~10`毫秒，G1 GC只有约75%；这是因为CMS的六个阶段中只有两个阶段需要STW的，其余阶段都可以和业务线程并发执行，很大程度上减少了停顿时间，增加了吞吐量；


- 串行GC的暂停时间有1.23%用了`30~60`毫秒，并行GC有1.64%的GC暂停时间处于`20~30`毫秒，两个明显没有CMS GC和G1 GC的吞度量高；


 

 

 

 

待完善...

