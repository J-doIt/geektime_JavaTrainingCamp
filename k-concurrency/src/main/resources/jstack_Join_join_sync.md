```shell
2021-10-16 22:14:15
Full thread dump Java HotSpot(TM) 64-Bit Server VM (12.0.2+10 mixed mode, sharing):

Threads class SMR info:
_java_thread_list=0x0000026f4821b850, length=12, elements={
0x0000026f269ad000, 0x0000026f47559000, 0x0000026f4755b000, 0x0000026f47578800,
0x0000026f4757a000, 0x0000026f4757b800, 0x0000026f4757e800, 0x0000026f47588800,
0x0000026f47f61000, 0x0000026f47f60800, 0x0000026f47f5b800, 0x0000026f47f5f800
}

"main" #1 prio=5 os_prio=0 cpu=156.25ms elapsed=18.82s allocated=2616K defined_classes=75 tid=0x0000026f269ad000 nid=0xe20 in Object.wait()  [0x0000006f2eeff000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@12.0.2/Native Method)
        - waiting on <0x000000008a158b30> (a org.kayla.concurrency.conc0301.op.MyThread)
        at java.lang.Thread.join(java.base@12.0.2/Thread.java:1308)
        - locked <0x000000008a158b30> (a org.kayla.concurrency.conc0301.op.MyThread)
        at java.lang.Thread.join(java.base@12.0.2/Thread.java:1375)
        at org.kayla.concurrency.conc0301.op.Join.main(Join.java:83)
        - locked <0x000000008a157118> (a java.lang.Object)

   Locked ownable synchronizers:
        - None

"Reference Handler" #2 daemon prio=10 os_prio=2 cpu=0.00ms elapsed=18.80s allocated=0B defined_classes=0 tid=0x0000026f47559000 nid=0x507c waiting on condition  [0x0000006f2f5ff000]
   java.lang.Thread.State: RUNNABLE
        at java.lang.ref.Reference.waitForReferencePendingList(java.base@12.0.2/Native Method)
        at java.lang.ref.Reference.processPendingReferences(java.base@12.0.2/Reference.java:241)
        at java.lang.ref.Reference$ReferenceHandler.run(java.base@12.0.2/Reference.java:213)

   Locked ownable synchronizers:
        - None

"Finalizer" #3 daemon prio=8 os_prio=1 cpu=0.00ms elapsed=18.80s allocated=0B defined_classes=0 tid=0x0000026f4755b000 nid=0xf1c in Object.wait()  [0x0000006f2f6fe000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@12.0.2/Native Method)
        - waiting on <0x000000008a30af78> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@12.0.2/ReferenceQueue.java:155)
        - locked <0x000000008a30af78> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@12.0.2/ReferenceQueue.java:176)
        at java.lang.ref.Finalizer$FinalizerThread.run(java.base@12.0.2/Finalizer.java:170)

   Locked ownable synchronizers:
        - None

"Signal Dispatcher" #4 daemon prio=9 os_prio=2 cpu=0.00ms elapsed=18.79s allocated=0B defined_classes=0 tid=0x0000026f47578800 nid=0x5e44 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"Attach Listener" #5 daemon prio=5 os_prio=2 cpu=31.25ms elapsed=18.79s allocated=685K defined_classes=27 tid=0x0000026f4757a000 nid=0x2a9c waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"C2 CompilerThread0" #6 daemon prio=9 os_prio=2 cpu=62.50ms elapsed=18.79s allocated=0B defined_classes=0 tid=0x0000026f4757b800 nid=0x5384 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

   Locked ownable synchronizers:
        - None

"C1 CompilerThread0" #9 daemon prio=9 os_prio=2 cpu=125.00ms elapsed=18.79s allocated=928B defined_classes=0 tid=0x0000026f4757e800 nid=0x448c waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

   Locked ownable synchronizers:
        - None

"Sweeper thread" #10 daemon prio=9 os_prio=2 cpu=0.00ms elapsed=18.78s allocated=0B defined_classes=0 tid=0x0000026f47588800 nid=0x2ea0 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"Common-Cleaner" #11 daemon prio=8 os_prio=1 cpu=0.00ms elapsed=18.75s allocated=0B defined_classes=0 tid=0x0000026f47f61000 nid=0x5c9c in Object.wait()  [0x0000006f2fcff000]
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

"Monitor Ctrl-Break" #12 daemon prio=5 os_prio=0 cpu=15.63ms elapsed=18.64s allocated=90656B defined_classes=32 tid=0x0000026f47f60800 nid=0x3db0 runnable  [0x0000006f2fdfe000]
   java.lang.Thread.State: RUNNABLE
        at java.net.SocketInputStream.socketRead0(java.base@12.0.2/Native Method)
        at java.net.SocketInputStream.socketRead(java.base@12.0.2/SocketInputStream.java:115)
        at java.net.SocketInputStream.read(java.base@12.0.2/SocketInputStream.java:168)
        at java.net.SocketInputStream.read(java.base@12.0.2/SocketInputStream.java:140)
        at sun.nio.cs.StreamDecoder.readBytes(java.base@12.0.2/StreamDecoder.java:284)
        at sun.nio.cs.StreamDecoder.implRead(java.base@12.0.2/StreamDecoder.java:326)
        at sun.nio.cs.StreamDecoder.read(java.base@12.0.2/StreamDecoder.java:178)
        - locked <0x000000008a19c090> (a java.io.InputStreamReader)
        at java.io.InputStreamReader.read(java.base@12.0.2/InputStreamReader.java:185)
        at java.io.BufferedReader.fill(java.base@12.0.2/BufferedReader.java:161)
        at java.io.BufferedReader.readLine(java.base@12.0.2/BufferedReader.java:326)
        - locked <0x000000008a19c090> (a java.io.InputStreamReader)
        at java.io.BufferedReader.readLine(java.base@12.0.2/BufferedReader.java:392)
        at com.intellij.rt.execution.application.AppMainV2$1.run(AppMainV2.java:49)

   Locked ownable synchronizers:
        - None

"Service Thread" #13 daemon prio=9 os_prio=0 cpu=0.00ms elapsed=18.64s allocated=0B defined_classes=0 tid=0x0000026f47f5b800 nid=0x5ca8 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"Thread-0" #14 prio=5 os_prio=0 cpu=0.00ms elapsed=18.64s allocated=0B defined_classes=0 tid=0x0000026f47f5f800 nid=0x1fcc waiting for monitor entry  [0x0000006f300ff000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at org.kayla.concurrency.conc0301.op.MyThread.run(Join.java:119)
        - waiting to lock <0x000000008a157118> (a java.lang.Object)

   Locked ownable synchronizers:
        - None

"VM Thread" os_prio=2 cpu=0.00ms elapsed=18.81s tid=0x0000026f47555800 nid=0x37a8 runnable

"GC Thread#0" os_prio=2 cpu=0.00ms elapsed=18.82s tid=0x0000026f269ea800 nid=0x63b0 runnable

"G1 Main Marker" os_prio=2 cpu=0.00ms elapsed=18.82s tid=0x0000026f269f8000 nid=0x44cc runnable

"G1 Conc#0" os_prio=2 cpu=0.00ms elapsed=18.82s tid=0x0000026f269f9800 nid=0x4350 runnable

"G1 Refine#0" os_prio=2 cpu=0.00ms elapsed=18.81s tid=0x0000026f473d0800 nid=0x2bf8 runnable

"G1 Young RemSet Sampling" os_prio=2 cpu=0.00ms elapsed=18.81s tid=0x0000026f473d3000 nid=0x48e4 runnable
"VM Periodic Task Thread" os_prio=2 cpu=0.00ms elapsed=18.64s tid=0x0000026f480b7000 nid=0x1fd8 waiting on condition

JNI global refs: 16, weak refs: 0
```