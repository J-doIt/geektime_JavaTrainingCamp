package org.kayla.concurrency.conc0302.lock;

/**
 * ThreadA
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/17 17:23
 **/
public class ThreadA extends Thread {

    private Count3 count3;

    public ThreadA(Count3 count3) {
        this.count3 = count3;
    }

    public void run() {
        count3.add();
    }
}
