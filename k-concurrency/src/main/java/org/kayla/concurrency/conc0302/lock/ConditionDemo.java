package org.kayla.concurrency.conc0302.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ConditionDemo
 *
 * TODO
 * Condition将Object监视器方法（ wait 、 notify和notifyAll ）分解为不同的对象，
 * 通过将它们与任意Lock实现的使用相结合，产生每个对象具有多个等待集的效果。
 * Lock代替了synchronized方法和语句的使用，而Condition代替了对象监视器方法的使用。
 *
 * Conditions（也称为条件队列或条件变量）为一个线程提供了一种挂起执行（to "wait）的方法，
 * 直到另一个线程通知某个状态条件现在可能为真。
 * 等待条件提供的key属性是，它原子地释放关联的锁并挂起当前线程，就像Object.wait。

 * Condition实例本质上绑定到锁。
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/17 17:21
 **/
public class ConditionDemo {

    final Lock lock = new ReentrantLock();
    final Condition NOT_FULL = lock.newCondition();
    final Condition NOT_EMPTY = lock.newCondition();

    final Object[] items = new Object[20];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        try {
            lock.lock();
            while (count == items.length) {
                NOT_FULL.await();
            }
            items[putptr] = x;
            if (++putptr == items.length) {
                putptr = 0;
            }
            ++count;
            NOT_EMPTY.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        try {
            lock.lock();
            // 当count为0，进入等待，直到notEmpty通知，进行消费。
            while (count == 0)
                NOT_EMPTY.await();
            Object x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            NOT_FULL.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}
