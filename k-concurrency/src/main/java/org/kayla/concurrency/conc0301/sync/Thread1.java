package org.kayla.concurrency.conc0301.sync;

/**
 * Thread1
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/17 13:27
 **/
public class Thread1 implements Runnable {

    @Override
    public void run() {
//        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
            }
//        }
    }

    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread ta = new Thread(t1, "A");
        Thread tb = new Thread(t1, "B");
        ta.start();
        tb.start();
    }
}
