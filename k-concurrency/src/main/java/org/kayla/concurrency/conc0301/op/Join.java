package org.kayla.concurrency.conc0301.op;

import org.kayla.gateway.util.Print;

/**
 *
 * <b>这几个状态具体是怎么改变的，跟锁的关系是什么?</b>
 *
 * <pre>
 *
 * Monitor 监视器
 * EntrySet 进入区
 * Owner 拥有者
 * WaitSet 等待区
 *
 * synchronized (内部锁 Intrinsic Lock / 监视锁 Monitor)
 *   任何一个对象都有唯一与之关联的锁;
 *   一种排它锁;
 *   作为锁句柄的变量通常采用final修饰，因为锁句柄变量的值一旦改变，会导致执行同一个同步块的多个线程实际上使用不同的锁，从而导致竞态。
 *   同步静态方法：以"this"为引导锁的同步块
 *   同步实例方法：以当前类对象为引导锁的同步块
 *
 * TODO: 不建议在 Thread 实例上使用wait、notify、notifyAll，why?
 *
 * 1. obj.wait()
 * 作用：
 *   让当前线程阻塞并等待被唤醒
 * 核心原理：
 *   (0) 当前线程 main 线程，通过 synchronized() 方法，成为 obj 对象锁的监视器的 Owner;
 *          ( 也是 wait 和 notify 必须在 synchronized 同步块的内部使用的原因
 *          < 线程间的通信需要借助同步对象（Object）的监视器来完成 > )
 *   (1) 当前线程 main 线程，调用 obj 同步锁对象的 wait() 方法时, JVM 会将当前线程
 *          main 线程加入到 obj 对象监视器(Object Monitor)的 WaitSet(等待集),
 *          等待被其他线程唤醒;
 *   (2) 当前线程 main 线程，会释放 obj 对象监视器的 Owner 权利，让其他线程可以抢夺 obj 对象的监视;
 *   (3) 让当前线程 main 线程等待，其状态变为 WAITING；
 *   (4) 如果 main 线程收到通知后被唤醒，main 线程会进入 obj 对象锁的监视器的 EntryList,
 *          并具备了排队抢夺 obj 监视器 Owner 权利的资格，其状态变成 BLOCKED;
 *   (5) 抢到 obj 对象锁后，main 拥有 obj 监视器的 Owner, 状态变为 Runnable;
 * 被唤醒的方式：
 *   obj.notify()、obj.notifyAll()、Thread.interrupt()、等待超时.
 *
 * * main() 的 synchronized(obj) 中的 thread1.join() ，会释放 thread1 这个对象（不是 thread1 线程）上的同步锁；
 *
 * 2. obj.notify()
 * 作用：
 *   唤醒在等待的线程 - 唤醒 obj 监视器等待集(WaitSet)中的单个等待线程
 * 核心原理：
 *   (0) 当前线程 thread1 线程，通过 synchronized() 方法，成为 obj 对象锁的监视器的 Owner；
 *   (1) 当前线程 thread1 线程，调用 obj 同步锁对象的 notify() 方法，JVM 会唤醒 obj 监视器 WaitSet(等待集) 中的一条等待线程到 EntryList；
 *   (3) 1.(4)
 * 注意：
 *   notify() 方法执行完后并不是立即释放 obj 的 Monitor 的 Owner, 而是，而是退出同步块后释放的。
 *
 *
 * 3. thread1.join()
 * 作用：
 *   调用线程(合并线程) main 调用 被调用线程(被合并线程) thread1 的 wait()方法，
 *   main 需要在*合并点*等待，一直等到 thread1 执行完成或等待超时(或被唤醒)。
 * 核心原理：
 *   (1) main 线程调用 thread1.join(), main 线程进入 WAITING 状态
 *   (2) main 线程不会释放已经持有的对象锁, thread1 对象锁会被释放掉,
 *
 * </pre>
 *
 * 说明：
 *   Print.consoleInput();//从屏幕读取输入，目的阻塞通知线程，方便使用jstack查看线程状态。
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/14 12:30
 **/
public class Join {

    public static void main(String[] args) throws InterruptedException {

        // main 状态：RUNNABLE

        // 定义一个同步对象 obj
        Object obj = new Object();

        MyThread thread1 = new MyThread("thread1 -- ");
        //oo = thread1;
        thread1.setObj(obj);
        thread1.start();

        // main 状态：RUNNABLE || BLOCKED  (争夺 obj 对象锁时，main 线程也可能会被阻塞)

        // TODO 这里用 obj 或 thread1 / this
        // synchronized (obj) : 执行 obj.join()，synchronized(obj) 内的 thread1 将被阻塞，而 main 也将一直等待；
        // synchronized (obj) : 执行 obj.wait()，synchronized (this) 内的 thread1.notify() 将不会唤醒 main 线程；
        //      因为main线程在obj监视器的WaitSet里，而thread1.notify()唤醒的是thread1监视器的WaitSet里的一个线程；
        // synchronized (thread1) : 在局部变量上同步，多余？
        // synchronized (this) : 需要非静态方法，编译不通过；
        synchronized (obj) {

            // main 状态：RUNNABLE
            for (int i = 0; i < 100; i++) {
                if (i == 20) {
                    try {
                        obj.wait(0); // main 状态：WAITING, 释放obj对象锁  (等待被唤醒)

                        // main 状态：BLOCKED (在 EntrySet 进入区), 正在抢夺 obj 对象锁
                        // * waiting to re-lock in wait() <0x000000008a1570a8> (a java.lang.Object)

                        // main 状态：RUNNABLE (thread1 退出同步块后，释放同步锁，给在WaitSet的其中一个线程获取Owner的资格)


//                        thread1.join(); // main 状态：WAITING, 当前线程不释放已经有的对象锁，释放 thread1 对象上的同步锁  (等待thread1执行完成)

                        // main 状态：WAITING (thread1 执行 obj.notify() 后，还未退出同步块)
                        // * waiting on <0x000000008a158a60> (a org.kayla.concurrency.conc0301.op.MyThread)
                        // 检测到 thread1 线程还没有结束，会调用 wait() 来暂停当前线程 main 线程，直到目标线程终止；

                        // main 状态：RUNNABLE

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " -- " + i);
            }
        }
    }

}

class MyThread extends Thread {

    private String name;
    private Object obj;

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public MyThread(String name) {
        this.name = name;
    }

    @lombok.SneakyThrows
    @Override
    public void run() {

        System.out.println("thread1 状态：RUNNABLE");

        // thread1 状态：RUNNABLE || BLOCKED

        // TODO 这里用 obj 或 this，效果不同
        // synchronized (this): 执行 obj.wait(), Join.main() 和 MyThread.run() 都会抛出 IllegalMonitorStateException 异常;
        // synchronized (this): 执行 thread1.join(), 只有 MyThread.run() 抛出 IllegalMonitorStateException 异常;
        synchronized (obj) {

            // thread1 状态：RUNNABLE
            for (int i = 0; i < 100; i++) {
                System.out.println(name + i);
            }

            // 如果 MyThread.run() 中的 obj.notify() 先于 main 方法的 obj.wait(0),
            // 该代码中, main 线程将无限期的等待。
            // 解决方案：等待超时、中断
            obj.notify(); // 执行 thread1.join()时，有没有obj.notify()都一样；

            Print.consoleInput();
            // resources/jstack_Join_join[wait]_1.md
            // thread1 状态：RUNNABLE
            //      locked <0x000000008a1570a8> (a java.lang.Object)
            // main 状态：BLOCKED (on object monitor)
            //      waiting to re-lock in wait() <0x000000008a1570a8> (a java.lang.Object)
            // notify() 方法执行完并不是立即释放同步锁。
        }

        Print.consoleInput();
        // resources/jstack_Join_join[wait]_2.md
        // thread1 状态：RUNNABLE (释放掉了 obj 监视器的权利)

    }
}