package org.kayla.concurrency.conc0302.lock;

/**
 * ThreadB
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/17 17:23
 **/
public class ThreadB extends Thread {
    private Count3 count3;

    public ThreadB(Count3 count3) {
        this.count3 = count3;
    }

    public void run() {
        count3.lockMethod();
    }
}
