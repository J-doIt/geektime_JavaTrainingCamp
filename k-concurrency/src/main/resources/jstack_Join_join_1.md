### MyThread.run() 中，thread1 执行完 obj.notify(), 还未退出 synchronized(obj) 时:

thread1.join() 不在 synchronized(obj) 内

```shell
2021-10-16 22:07:22
Full thread dump Java HotSpot(TM) 64-Bit Server VM (12.0.2+10 mixed mode, sharing):

Threads class SMR info:
_java_thread_list=0x000001bec7e258a0, length=12, elements={
0x000001bea860b800, 0x000001bec7215000, 0x000001bec7217000, 0x000001bec7233800,
0x000001bec7235000, 0x000001bec7236800, 0x000001bec7239800, 0x000001bec7242000,
0x000001bec71fb800, 0x000001bec7cc3000, 0x000001bec7cc5000, 0x000001bec7ccb800
}

"main" #1 prio=5 os_prio=0 cpu=140.63ms elapsed=151.52s allocated=2567K defined_classes=71 tid=0x000001bea860b800 nid=0x2964 in Object.wait()  [0x0000008b56dff000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@12.0.2/Native Method)
        - waiting on <0x000000008a158a60> (a org.kayla.concurrency.conc0301.op.MyThread)
        at java.lang.Thread.join(java.base@12.0.2/Thread.java:1308)
        - locked <0x000000008a158a60> (a org.kayla.concurrency.conc0301.op.MyThread)
        at java.lang.Thread.join(java.base@12.0.2/Thread.java:1375)
        at org.kayla.concurrency.conc0301.op.Join.main(Join.java:83)

   Locked ownable synchronizers:
        - None

"Reference Handler" #2 daemon prio=10 os_prio=2 cpu=0.00ms elapsed=151.51s allocated=0B defined_classes=0 tid=0x000001bec7215000 nid=0x31f8 waiting on condition  [0x0000008b574ff000]
   java.lang.Thread.State: RUNNABLE
        at java.lang.ref.Reference.waitForReferencePendingList(java.base@12.0.2/Native Method)
        at java.lang.ref.Reference.processPendingReferences(java.base@12.0.2/Reference.java:241)
        at java.lang.ref.Reference$ReferenceHandler.run(java.base@12.0.2/Reference.java:213)

   Locked ownable synchronizers:
        - None

"Finalizer" #3 daemon prio=8 os_prio=1 cpu=0.00ms elapsed=151.51s allocated=0B defined_classes=0 tid=0x000001bec7217000 nid=0x100c in Object.wait()  [0x0000008b575fe000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@12.0.2/Native Method)
        - waiting on <0x000000008a30af78> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@12.0.2/ReferenceQueue.java:155)
        - locked <0x000000008a30af78> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@12.0.2/ReferenceQueue.java:176)
        at java.lang.ref.Finalizer$FinalizerThread.run(java.base@12.0.2/Finalizer.java:170)

   Locked ownable synchronizers:
        - None

"Signal Dispatcher" #4 daemon prio=9 os_prio=2 cpu=0.00ms elapsed=151.50s allocated=0B defined_classes=0 tid=0x000001bec7233800 nid=0x2034 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"Attach Listener" #5 daemon prio=5 os_prio=2 cpu=15.63ms elapsed=151.50s allocated=613K defined_classes=24 tid=0x000001bec7235000 nid=0x2984 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"C2 CompilerThread0" #6 daemon prio=9 os_prio=2 cpu=31.25ms elapsed=151.50s allocated=0B defined_classes=0 tid=0x000001bec7236800 nid=0x5fd0 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

   Locked ownable synchronizers:
        - None

"C1 CompilerThread0" #9 daemon prio=9 os_prio=2 cpu=125.00ms elapsed=151.50s allocated=1112B defined_classes=0 tid=0x000001bec7239800 nid=0x3a20 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

   Locked ownable synchronizers:
        - None

"Sweeper thread" #10 daemon prio=9 os_prio=2 cpu=0.00ms elapsed=151.50s allocated=0B defined_classes=0 tid=0x000001bec7242000 nid=0x63c0 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"Common-Cleaner" #11 daemon prio=8 os_prio=1 cpu=0.00ms elapsed=151.47s allocated=0B defined_classes=0 tid=0x000001bec71fb800 nid=0xc9c in Object.wait()  [0x0000008b57bff000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
        at java.lang.Object.wait(java.base@12.0.2/Native Method)
        - waiting on <0x000000008a3b7b50> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@12.0.2/ReferenceQueue.java:155)
        - locked <0x000000008a3b7b50> (a java.lang.ref.ReferenceQueue$Lock)
        at jdk.internal.ref.CleanerImpl.run(java.base@12.0.2/CleanerImpl.java:148)
        at java.lang.Thread.run(java.base@12.0.2/Thread.java:835)
        at jdk.internal.misc.InnocuousThread.run(java.base@12.0.2/InnocuousThread.java:134)

   Locked ownable synchronizers:
        - None

"Monitor Ctrl-Break" #12 daemon prio=5 os_prio=0 cpu=15.63ms elapsed=151.39s allocated=90776B defined_classes=32 tid=0x000001bec7cc3000 nid=0x5244 runnable  [0x0000008b57dfe000]
   java.lang.Thread.State: RUNNABLE
        at java.net.SocketInputStream.socketRead0(java.base@12.0.2/Native Method)
        at java.net.SocketInputStream.socketRead(java.base@12.0.2/SocketInputStream.java:115)
        at java.net.SocketInputStream.read(java.base@12.0.2/SocketInputStream.java:168)
        at java.net.SocketInputStream.read(java.base@12.0.2/SocketInputStream.java:140)
        at sun.nio.cs.StreamDecoder.readBytes(java.base@12.0.2/StreamDecoder.java:284)
        at sun.nio.cs.StreamDecoder.implRead(java.base@12.0.2/StreamDecoder.java:326)
        at sun.nio.cs.StreamDecoder.read(java.base@12.0.2/StreamDecoder.java:178)
        - locked <0x000000008a19c108> (a java.io.InputStreamReader)
        at java.io.InputStreamReader.read(java.base@12.0.2/InputStreamReader.java:185)
        at java.io.BufferedReader.fill(java.base@12.0.2/BufferedReader.java:161)
        at java.io.BufferedReader.readLine(java.base@12.0.2/BufferedReader.java:326)
        - locked <0x000000008a19c108> (a java.io.InputStreamReader)
        at java.io.BufferedReader.readLine(java.base@12.0.2/BufferedReader.java:392)
        at com.intellij.rt.execution.application.AppMainV2$1.run(AppMainV2.java:49)

   Locked ownable synchronizers:
        - None

"Service Thread" #13 daemon prio=9 os_prio=0 cpu=0.00ms elapsed=151.39s allocated=0B defined_classes=0 tid=0x000001bec7cc5000 nid=0x45b4 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"Thread-0" #14 prio=5 os_prio=0 cpu=15.63ms elapsed=151.38s allocated=621K defined_classes=43 tid=0x000001bec7ccb800 nid=0x444c runnable  [0x0000008b580fe000]
   java.lang.Thread.State: RUNNABLE
        at java.io.FileInputStream.readBytes(java.base@12.0.2/Native Method)
        at java.io.FileInputStream.read(java.base@12.0.2/FileInputStream.java:273)
        at java.io.BufferedInputStream.read1(java.base@12.0.2/BufferedInputStream.java:290)
        at java.io.BufferedInputStream.read(java.base@12.0.2/BufferedInputStream.java:351)
        - locked <0x000000008a344e88> (a java.io.BufferedInputStream)
        at sun.nio.cs.StreamDecoder.readBytes(java.base@12.0.2/StreamDecoder.java:284)
        at sun.nio.cs.StreamDecoder.implRead(java.base@12.0.2/StreamDecoder.java:326)
        at sun.nio.cs.StreamDecoder.read(java.base@12.0.2/StreamDecoder.java:178)
        - locked <0x000000008a050710> (a java.io.InputStreamReader)
        at java.io.InputStreamReader.read(java.base@12.0.2/InputStreamReader.java:185)
        at java.io.Reader.read(java.base@12.0.2/Reader.java:189)
        at java.util.Scanner.readInput(java.base@12.0.2/Scanner.java:882)
        at java.util.Scanner.findWithinHorizon(java.base@12.0.2/Scanner.java:1796)
        at java.util.Scanner.nextLine(java.base@12.0.2/Scanner.java:1649)
        at org.kayla.gateway.util.Print.consoleInput(Print.java:126)
        at org.kayla.concurrency.conc0301.op.MyThread.run(Join.java:130)
        - locked <0x000000008a157048> (a java.lang.Object)

   Locked ownable synchronizers:
        - None

"VM Thread" os_prio=2 cpu=46.88ms elapsed=151.51s tid=0x000001bec7211000 nid=0x2fcc runnable

"GC Thread#0" os_prio=2 cpu=0.00ms elapsed=151.52s tid=0x000001bea864b000 nid=0x1738 runnable

"G1 Main Marker" os_prio=2 cpu=0.00ms elapsed=151.52s tid=0x000001bea8658800 nid=0x2778 runnable

"G1 Conc#0" os_prio=2 cpu=0.00ms elapsed=151.52s tid=0x000001bea865a800 nid=0x2180 runnable

"G1 Refine#0" os_prio=2 cpu=0.00ms elapsed=151.52s tid=0x000001bec7090800 nid=0x2fc8 runnable

"G1 Young RemSet Sampling" os_prio=2 cpu=31.25ms elapsed=151.52s tid=0x000001bec7091800 nid=0x615c runnable
"VM Periodic Task Thread" os_prio=2 cpu=15.63ms elapsed=151.39s tid=0x000001bec7cc7800 nid=0x368c waiting on condition  

JNI global refs: 16, weak refs: 0
```