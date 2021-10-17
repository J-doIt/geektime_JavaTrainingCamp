package org.kayla.concurrency.conc0302.lock;

/**
 * Count3
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/17 17:22
 **/
public class Count3 {

    private byte[] lock1 = new byte[1];
    private byte[] lock2 = new byte[1];

    public int num = 0;

    public void add() {
        synchronized (lock1) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                num += 1;
            }
            System.out.println(Thread.currentThread().getName() + "_" + num);
        }
    }

    public void lockMethod() {
        synchronized (lock2) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock1) {
                num += 1;
            }
            System.out.println(Thread.currentThread().getName() + "_" + num);
        }
    }
}
