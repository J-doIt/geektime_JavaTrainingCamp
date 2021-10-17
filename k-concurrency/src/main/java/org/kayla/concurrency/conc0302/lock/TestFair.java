package org.kayla.concurrency.conc0302.lock;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TestFair
 *
 * TestFair.md
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/17 17:22
 **/
public class TestFair {

    public static volatile int race = 0;

    public static ReentrantLock lock = new ReentrantLock(false); // 改成false会好100倍

    public static void increase() {
        lock.lock();
        race++;    //变量自增操作
        lock.unlock();
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {

        int count = Thread.activeCount();
        long now = System.currentTimeMillis();
        System.out.println(count);

        AtomicReference<Thread> sign = new AtomicReference<>();
        Thread[] threads = new Thread[THREADS_COUNT];  //定义20个线程

        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100_000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }//等待所有累加线程都结束

        while (Thread.activeCount() > count) {
            Thread.yield();
        }
        System.out.println(lock.getClass().getName() + " ts = " + (System.currentTimeMillis() - now));
    }
}
