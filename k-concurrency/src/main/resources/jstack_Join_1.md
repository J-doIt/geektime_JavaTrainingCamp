## 

- 第一部分: JVM虚拟机的信息, 显示了虚拟机的 thread dump 时间和虚拟机的版本等信息;
- 第二部分: JVM中非JVM(非VM和非GC的线程)的内部线程信息
  - SMR: 安全的内存分配(Safe Memory Reclamation); 非自动GC的编程语言中如C++, 
    需要自己来为对象分配内存和销毁对象，这样就可能导致在多线程的环境中，一个地址可能被分配给了多个对象，从而出现了内存分配的不安全。
  - elements: 是和后面线程的 tid 相匹配的(不是线程的id), 表示的是本地线程对象的地址;
- 第三部分: 线程的具体信息
- 第四部分: JVM的线程信息
- 第五部分: JNI（Java Native Interface）引用的信息，注意这些引用可能会导致内存泄露，因为这些native的引用并不会被自动垃圾回收。

|                  字段 | 含义                          |
| -------------------: | :---------------------------- |
|               "main" | 线程名                         |
|                   #1 | 线程id                         |
|                 prio | 优先级                         |
|              os_prio | OS线程的优先级                  |
|         cpu=109.38ms | cpu时间:线程获得CPU的时间        |
|              elapsed | 线程启动后经过的 wall clock time |
|            allocated | 本线程分配的分配的bytes数         |
|   defined_classes=75 | 本线程定义的class个数            |
|                  tid | java 线程的地址                 |
|                  nid | OS 线程id                      |
|     in Object.wait() | 线程状态                        |
| [0x000000cb11ffe000] | 最新的java堆栈指针SP             |

|                           状态 | 含义                                             |
| ----------------------------: | :-----------------------------------------------|
|              locked <地址> 目标 | 使用synchronized申请对象锁成功,监视器的拥有者 (RUNNABLE)        |
|     waiting to lock <地址> 目标 | 使用synchronized申请对象锁未成功,在进入区等待 (BLOCKED)       |
|          waiting on <地址> 目标 | 使用synchronized申请对象锁成功后,释放锁后在等待区等待 () |
| parking to wait for <地址> 目标 | 不限时等待 (WAITING)   |


```shell
2021-10-16 17:14:22
Full thread dump Java HotSpot(TM) 64-Bit Server VM (12.0.2+10 mixed mode, sharing):

Threads class SMR info:
_java_thread_list=0x000001d5cfac96e0, length=17, elements={
0x000001d5aff9b800, 0x000001d5cebc3800, 0x000001d5cebc6800, 0x000001d5cebe0800,
0x000001d5cebe1800, 0x000001d5cebe3000, 0x000001d5cebe6000, 0x000001d5cebf2000,
0x000001d5cebaa800, 0x000001d5cf6e3800, 0x000001d5cf6d7000, 0x000001d5cf6e9000,
0x000001d5cf7cb000, 0x000001d5cf8ac800, 0x000001d5cf9ac800, 0x000001d5cf979800,
0x000001d5cfaf7000
}

"main" #1 prio=5 os_prio=0 cpu=109.38ms elapsed=66.89s allocated=2616K defined_classes=75 tid=0x000001d5aff9b800 nid=0x3bc4 in Object.wait()  [0x000000135bffe000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at java.lang.Object.wait(java.base@12.0.2/Native Method)
        - waiting to re-lock in wait() <0x000000008a1570a8> (a java.lang.Object)
        at org.kayla.concurrency.conc0301.op.Join.main(Join.java:61)
        - locked <0x000000008a1570a8> (a java.lang.Object)

   Locked ownable synchronizers:
        - None

"Thread-0" #14 prio=5 os_prio=0 cpu=31.25ms elapsed=66.76s allocated=547K defined_classes=37 tid=0x000001d5cf6e9000 nid=0x1538 runnable  [0x000000135d2fe000]
   java.lang.Thread.State: RUNNABLE
        at java.io.FileInputStream.readBytes(java.base@12.0.2/Native Method)
        at java.io.FileInputStream.read(java.base@12.0.2/FileInputStream.java:273)
        at java.io.BufferedInputStream.read1(java.base@12.0.2/BufferedInputStream.java:290)
        at java.io.BufferedInputStream.read(java.base@12.0.2/BufferedInputStream.java:351)
        - locked <0x000000008a344e88> (a java.io.BufferedInputStream)
        at sun.nio.cs.StreamDecoder.readBytes(java.base@12.0.2/StreamDecoder.java:284)
        at sun.nio.cs.StreamDecoder.implRead(java.base@12.0.2/StreamDecoder.java:326)
        at sun.nio.cs.StreamDecoder.read(java.base@12.0.2/StreamDecoder.java:178)
        - locked <0x000000008a0b95c8> (a java.io.InputStreamReader)
        at java.io.InputStreamReader.read(java.base@12.0.2/InputStreamReader.java:185)
        at java.io.Reader.read(java.base@12.0.2/Reader.java:189)
        at java.util.Scanner.readInput(java.base@12.0.2/Scanner.java:882)
        at java.util.Scanner.findWithinHorizon(java.base@12.0.2/Scanner.java:1796)
        at java.util.Scanner.nextLine(java.base@12.0.2/Scanner.java:1649)
        at org.kayla.gateway.util.Print.consoleInput(Print.java:126)
        at org.kayla.concurrency.conc0301.op.MyThread.run(Join.java:108)
        - locked <0x000000008a1570a8> (a java.lang.Object)

   Locked ownable synchronizers:
        - None

"VM Thread" os_prio=2 cpu=93.75ms elapsed=66.88s tid=0x000001d5cebc0000 nid=0x312c runnable

"GC Thread#0" os_prio=2 cpu=0.00ms elapsed=66.88s tid=0x000001d5affdb000 nid=0x3be0 runnable

"G1 Main Marker" os_prio=2 cpu=0.00ms elapsed=66.88s tid=0x000001d5affe8800 nid=0x2b6c runnable

"G1 Conc#0" os_prio=2 cpu=0.00ms elapsed=66.88s tid=0x000001d5affea000 nid=0x4154 runnable

"G1 Refine#0" os_prio=2 cpu=0.00ms elapsed=66.88s tid=0x000001d5cea40800 nid=0x5ef8 runnable

"G1 Young RemSet Sampling" os_prio=2 cpu=0.00ms elapsed=66.88s tid=0x000001d5cea41800 nid=0x1dc runnable
"VM Periodic Task Thread" os_prio=2 cpu=31.25ms elapsed=66.76s tid=0x000001d5cf6e4000 nid=0x1ecc waiting on condition

JNI global refs: 27, weak refs: 0
```