package org.kayla.concurrency.conc0302.atomic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * SyncCount 锁实现的同步计数
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/17 16:56
 **/
public class SyncCount {

    private int num = 0;

    private Lock lock = new ReentrantLock(true);

    public int add() {
        try {
            lock.lock();
            return num++;
        } finally {
            lock.unlock();
        }
    }

    public int getNum() {
        return num;
    }
}
