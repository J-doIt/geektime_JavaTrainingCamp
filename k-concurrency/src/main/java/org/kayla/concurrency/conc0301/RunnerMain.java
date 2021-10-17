package org.kayla.concurrency.conc0301;

/**
 * RunnerMain
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/17 13:49
 **/
public class RunnerMain {

    public static void main(String[] args) {
        Runner1 runner1 = new Runner1();
        Thread thread1 = new Thread(runner1);

        Runner2 runner2 = new Runner2();
        Thread thread2 = new Thread(runner2);

        thread1.start();
        thread2.start();

        thread2.interrupt();  // i = true

        System.out.println("当前线程的线程组及其子组中活动线程数的估计值: " + Thread.activeCount());

        Thread.currentThread().getThreadGroup().list();
        System.out.println("当前线程的线程组的父级组及其子组中活动组数的估计值: " + Thread.currentThread().getThreadGroup().getParent().activeGroupCount());
        Thread.currentThread().getThreadGroup().getParent().list();
    }
}
