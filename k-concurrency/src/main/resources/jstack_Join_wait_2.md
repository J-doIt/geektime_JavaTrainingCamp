### MyThread.run() 中，thread1 执行完 obj.notify(), 还未退出 synchronized(obj) 时:

thread1.wait() 在 synchronized(obj) 内
obj.notify() 在 synchronized(obj) 内

```shell
2021-10-16 22:10:44
Full thread dump Java HotSpot(TM) 64-Bit Server VM (12.0.2+10 mixed mode, sharing):

Threads class SMR info:
_java_thread_list=0x0000026786a90be0, length=12, elements={
0x00000267ffca3000, 0x00000267ffca6000, 0x00000267ffcc3000, 0x00000267ffcc4000,
0x00000267ffcc7800, 0x00000267ffcd1000, 0x00000267ffce5000, 0x00000267ffc89800,
0x000002678686c000, 0x0000026786963800, 0x000002678696e800, 0x00000267e6f6a800
}

"Reference Handler" #2 daemon prio=10 os_prio=2 cpu=0.00ms elapsed=51.99s allocated=0B defined_classes=0 tid=0x00000267ffca3000 nid=0x5620 waiting on condition  [0x000000e1e16ff000]
   java.lang.Thread.State: RUNNABLE
        at java.lang.ref.Reference.waitForReferencePendingList(java.base@12.0.2/Native Method)
        at java.lang.ref.Reference.processPendingReferences(java.base@12.0.2/Reference.java:241)
        at java.lang.ref.Reference$ReferenceHandler.run(java.base@12.0.2/Reference.java:213)

   Locked ownable synchronizers:
        - None

"Finalizer" #3 daemon prio=8 os_prio=1 cpu=0.00ms elapsed=51.98s allocated=0B defined_classes=0 tid=0x00000267ffca6000 nid=0x5590 in Object.wait()  [0x000000e1e17fe000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@12.0.2/Native Method)
        - waiting on <0x000000008a30af78> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@12.0.2/ReferenceQueue.java:155)
        - locked <0x000000008a30af78> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@12.0.2/ReferenceQueue.java:176)
        at java.lang.ref.Finalizer$FinalizerThread.run(java.base@12.0.2/Finalizer.java:170)

   Locked ownable synchronizers:
        - None

"Signal Dispatcher" #4 daemon prio=9 os_prio=2 cpu=0.00ms elapsed=51.97s allocated=0B defined_classes=0 tid=0x00000267ffcc3000 nid=0x2228 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"Attach Listener" #5 daemon prio=5 os_prio=2 cpu=15.63ms elapsed=51.97s allocated=613K defined_classes=24 tid=0x00000267ffcc4000 nid=0x29d8 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"C2 CompilerThread0" #6 daemon prio=9 os_prio=2 cpu=62.50ms elapsed=51.97s allocated=0B defined_classes=0 tid=0x00000267ffcc7800 nid=0x4508 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

   Locked ownable synchronizers:
        - None

"C1 CompilerThread0" #9 daemon prio=9 os_prio=2 cpu=62.50ms elapsed=51.97s allocated=1248B defined_classes=0 tid=0x00000267ffcd1000 nid=0x27bc waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

   Locked ownable synchronizers:
        - None

"Sweeper thread" #10 daemon prio=9 os_prio=2 cpu=0.00ms elapsed=51.97s allocated=0B defined_classes=0 tid=0x00000267ffce5000 nid=0x4c50 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"Common-Cleaner" #11 daemon prio=8 os_prio=1 cpu=0.00ms elapsed=51.94s allocated=0B defined_classes=0 tid=0x00000267ffc89800 nid=0x790 in Object.wait()  [0x000000e1e1dff000]
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

"Monitor Ctrl-Break" #12 daemon prio=5 os_prio=0 cpu=15.63ms elapsed=51.87s allocated=90776B defined_classes=32 tid=0x000002678686c000 nid=0x6320 runnable  [0x000000e1e1ffe000]
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

"Service Thread" #13 daemon prio=9 os_prio=0 cpu=0.00ms elapsed=51.87s allocated=0B defined_classes=0 tid=0x0000026786963800 nid=0x677c runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"Thread-0" #14 prio=5 os_prio=0 cpu=31.25ms elapsed=51.86s allocated=563K defined_classes=38 tid=0x000002678696e800 nid=0x523c runnable  [0x000000e1e22fe000]
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
        at org.kayla.concurrency.conc0301.op.MyThread.run(Join.java:139)

   Locked ownable synchronizers:
        - None

"DestroyJavaVM" #15 prio=5 os_prio=0 cpu=140.63ms elapsed=7.37s allocated=488B defined_classes=0 tid=0x00000267e6f6a800 nid=0x5d38 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"VM Thread" os_prio=2 cpu=15.63ms elapsed=51.99s tid=0x00000267ffc9f000 nid=0x55cc runnable

"GC Thread#0" os_prio=2 cpu=0.00ms elapsed=52.00s tid=0x00000267e6fab000 nid=0x16a4 runnable

"G1 Main Marker" os_prio=2 cpu=0.00ms elapsed=52.00s tid=0x00000267e6fb8800 nid=0x3600 runnable

"G1 Conc#0" os_prio=2 cpu=0.00ms elapsed=52.00s tid=0x00000267e6fba000 nid=0x2ff0 runnable

"G1 Refine#0" os_prio=2 cpu=0.00ms elapsed=51.99s tid=0x00000267ffb20800 nid=0x2954 runnable

"G1 Young RemSet Sampling" os_prio=2 cpu=0.00ms elapsed=51.99s tid=0x00000267ffb21800 nid=0x2d6c runnable
"VM Periodic Task Thread" os_prio=2 cpu=15.63ms elapsed=51.87s tid=0x0000026786966000 nid=0x3310 waiting on condition

JNI global refs: 16, weak refs: 0
```