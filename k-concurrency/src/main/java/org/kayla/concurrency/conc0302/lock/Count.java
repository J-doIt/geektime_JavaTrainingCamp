package org.kayla.concurrency.conc0302.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Count
 * ReentrantLock：可重入互斥的Lock,
 *      与使用synchronized方法和语句访问的隐式监视器锁具有相同的基本行为和语义，
 *      但具有扩展功能。
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/17 17:21
 **/
public class Count {

    final ReentrantLock lock = new ReentrantLock();

    public void get() {
//        final ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "get begin");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " get end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void put() {
//        final ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " put begin");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " put end");
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
