```shell
2021-10-16 18:54:55
Full thread dump Java HotSpot(TM) 64-Bit Server VM (12.0.2+10 mixed mode, sharing):

Threads class SMR info:
_java_thread_list=0x0000022cba3e8780, length=12, elements={
0x0000022cb953a000, 0x0000022cb953c800, 0x0000022cb9de1800, 0x0000022cb9de2800,
0x0000022cb9de6000, 0x0000022cb9deb000, 0x0000022cb9df2800, 0x0000022cb9520800,
0x0000022cba077000, 0x0000022cba077800, 0x0000022cba07f800, 0x0000022c9a8ed000
}

"Thread-0" #14 prio=5 os_prio=0 cpu=31.25ms elapsed=25.21s allocated=563K defined_classes=38 tid=0x0000022cba07f800 nid=0x1f6c runnable  [0x000000cc7e4fe000]
   java.lang.Thread.State: RUNNABLE
        at java.io.FileInputStream.readBytes(java.base@12.0.2/Native Method)
        at java.io.FileInputStream.read(java.base@12.0.2/FileInputStream.java:273)
        at java.io.BufferedInputStream.read1(java.base@12.0.2/BufferedInputStream.java:290)
        at java.io.BufferedInputStream.read(java.base@12.0.2/BufferedInputStream.java:351)
        - locked <0x000000008a344e88> (a java.io.BufferedInputStream)
        at sun.nio.cs.StreamDecoder.readBytes(java.base@12.0.2/StreamDecoder.java:284)
        at sun.nio.cs.StreamDecoder.implRead(java.base@12.0.2/StreamDecoder.java:326)
        at sun.nio.cs.StreamDecoder.read(java.base@12.0.2/StreamDecoder.java:178)
        - locked <0x0000000089f04f50> (a java.io.InputStreamReader)
        at java.io.InputStreamReader.read(java.base@12.0.2/InputStreamReader.java:185)
        at java.io.Reader.read(java.base@12.0.2/Reader.java:189)
        at java.util.Scanner.readInput(java.base@12.0.2/Scanner.java:882)
        at java.util.Scanner.findWithinHorizon(java.base@12.0.2/Scanner.java:1796)
        at java.util.Scanner.nextLine(java.base@12.0.2/Scanner.java:1649)
        at org.kayla.gateway.util.Print.consoleInput(Print.java:126)
        at org.kayla.concurrency.conc0301.op.MyThread.run(Join.java:122)

"DestroyJavaVM" #15 prio=5 os_prio=0 cpu=140.63ms elapsed=22.58s allocated=488B defined_classes=0 tid=0x0000022c9a8ed000 nid=0x2930 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"VM Thread" os_prio=2 cpu=15.63ms elapsed=25.32s tid=0x0000022cb9536000 nid=0x6050 runnable

"GC Thread#0" os_prio=2 cpu=0.00ms elapsed=25.33s tid=0x0000022c9a92d000 nid=0x55dc runnable

"G1 Main Marker" os_prio=2 cpu=0.00ms elapsed=25.33s tid=0x0000022c9a93a800 nid=0x434c runnable

"G1 Conc#0" os_prio=2 cpu=0.00ms elapsed=25.33s tid=0x0000022c9a93b800 nid=0x3cf8 runnable

"G1 Refine#0" os_prio=2 cpu=0.00ms elapsed=25.33s tid=0x0000022c9a9be000 nid=0x3c58 runnable

"G1 Young RemSet Sampling" os_prio=2 cpu=0.00ms elapsed=25.33s tid=0x0000022cb93af800 nid=0x2c30 runnable
"VM Periodic Task Thread" os_prio=2 cpu=0.00ms elapsed=25.22s tid=0x0000022cba07b000 nid=0x38c4 waiting on condition

JNI global refs: 16, weak refs: 0
```