package org.kayla.concurrency.conc0301;

/**
 * ThreadA
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/17 13:49
 **/
public class ThreadA extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("这是线程A");
    }
}
