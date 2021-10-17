package org.kayla.concurrency.conc0301;

/**
 * ThreadCount
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/17 13:50
 **/
public class ThreadCount {

    /**
     * <pre>
     *
     *     当前线程所属现线程组：java.lang.ThreadGroup[name=main,maxpri=10]
     * java.lang.ThreadGroup[name=main,maxpri=10]
     *     Thread[main,5,main]
     *     Thread[Monitor Ctrl-Break,5,main]
     * 当前线程所属现线程组的父级：java.lang.ThreadGroup[name=system,maxpri=10]
     * java.lang.ThreadGroup[name=system,maxpri=10]
     *     Thread[Reference Handler,10,system]
     *     Thread[Finalizer,8,system]
     *     Thread[Signal Dispatcher,9,system]
     *     Thread[Attach Listener,5,system]
     *     java.lang.ThreadGroup[name=main,maxpri=10]
     *         Thread[main,5,main]
     *         Thread[Monitor Ctrl-Break,5,main] ## 这个线程是IDEA用来监控Ctrl-Break中断信号的线程; IntelliJ IDEA执行用户代码的时候，实际是通过反射方式去调用;
     *     java.lang.ThreadGroup[name=InnocuousThreadGroup,maxpri=10]
     *         Thread[Common-Cleaner,8,InnocuousThreadGroup]
     *
     * </pre>
     */
    public static void main(String[] args) {
        System.out.println("当前线程所属现线程组："+Thread.currentThread().getThreadGroup());
        Thread.currentThread().getThreadGroup().list();
        System.out.println("当前线程所属现线程组的父级："+Thread.currentThread().getThreadGroup().getParent());
        Thread.currentThread().getThreadGroup().getParent().list();




    }
}
