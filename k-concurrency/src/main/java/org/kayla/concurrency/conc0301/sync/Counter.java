package org.kayla.concurrency.conc0301.sync;

/**
 * Counter
 * 比较单线程计数和多线程计数
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/12 00:07
 **/
public class Counter {

    private int sum = 0;
    private Object lock = new Object();


    // 简单使用同步方法，去掉多线程并发的干扰，带来的副作用就是，跟单线程执行是一样的;

    // 当 synchronized 加在代码块上时, 线程执行到这个代码块时（在this对象上加的同步块）;
    /*public void incr() {
        // do其他事情
        synchronized(lock) { // lock 和 this 执行效果一样
            sum = sum + 1;
        }
    }*/

    /**
     * 当 synchronized 加在方法上时
     * 调用这个方法的时候，就把这个this对象本身，它的对象头上加了锁的标志位，改为对应的锁；
     */
    public synchronized void incr() {
        sum = sum + 1;
    }

    public int getSum() {
        return sum;
    }

    public static void main(String[] args) throws InterruptedException {
        int loop = 10_0000;

        // test single thread
        Counter counter = new Counter();
        for (int i = 0; i < loop; i++) {
            counter.incr();
        }

        System.out.println("single thread: " + counter.getSum());

        // test multiple threads
        final Counter counter2 = new Counter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < loop / 2; i++) {
                counter2.incr();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < loop / 2; i++) {
                counter2.incr();
            }
        });
        t1.start();
        t2.start();
        Thread.sleep(1000);

        System.out.println("multiple threads: " + counter2.getSum());
    }

}
