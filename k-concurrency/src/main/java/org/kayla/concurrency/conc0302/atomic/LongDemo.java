package org.kayla.concurrency.conc0302.atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * LongDemo
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/17 16:56
 **/
public class LongDemo {

    public static void main(String[] args) {
        final AtomicLong atomicLong = new AtomicLong();
        final LongAdder longAdder = new LongAdder();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10_000; j++) {
                    atomicLong.getAndIncrement();
                    longAdder.increment();
                }
            }).start();
        }

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("atomicLong = " + atomicLong.get());
        System.out.println("longAdder = " + longAdder.sum());
    }
}
