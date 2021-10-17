package org.kayla.concurrency.conc0301.sync;

/**
 * TestSetGet
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/17 13:11
 **/
public class TestSetGet {

    public static void main(String[] args) throws InterruptedException {

        final SetGet s = new SetGet();
        Thread t = new Thread(() -> {
            try {
                int i = s.get();
                System.out.println("s.get(): " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        long start = System.currentTimeMillis();
        s.set(10);
        System.out.println(" ... " + ( System.currentTimeMillis() - start));
    }

    public static class SetGet {

        int a = 0;

        public synchronized void set(int v) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " setting " + v);
            Thread.sleep(1000);
            a = v;
            System.out.println(Thread.currentThread().getName() + " set " + v);
        }

        public synchronized int get() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " getting ");
            Thread.sleep(10_000);
            System.out.println(Thread.currentThread().getName() + " get ");
            return a;
        }
    }

}
