package org.kayla.concurrency.conc0302.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Count2
 * ReentrantReadWriteLock：ReadWriteLock的实现，支持与ReentrantLock类似的语义。
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/17 17:22
 **/
public class Count2 {

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void get() {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " get begin");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " get end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void put() {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " put begin");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " put end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

}
