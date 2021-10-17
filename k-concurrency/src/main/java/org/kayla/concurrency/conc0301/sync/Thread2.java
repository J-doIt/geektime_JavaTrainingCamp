package org.kayla.concurrency.conc0301.sync;

/**
 * Thread2
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/17 13:27
 **/
public class Thread2 {

    public void m4t1() {
//        synchronized (this) {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//        }
    }

    public /*synchronized*/ void m4t2() {
        int i = 5;
        while (i-- > 0) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final Thread2 myt2 = new Thread2();
        Thread t1 = new Thread(() -> {
            myt2.m4t1();
        }, "t1");
        Thread t2 = new Thread(() -> {
            myt2.m4t2();
        }, "t2");
        t2.start();
        t1.start();
    }
}
