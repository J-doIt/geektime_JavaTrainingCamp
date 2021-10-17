package org.kayla.concurrency.conc0302.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicCount 原子计数
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/17 16:54
 **/
public class AtomicCount {

    private AtomicInteger num = new AtomicInteger();

    public int add() {
        return num.getAndIncrement();
    }

    public int getNum() {
        return num.get();
    }
}
