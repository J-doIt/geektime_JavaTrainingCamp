package org.kayla.concurrency.week04;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Kayla(J - doIt)
 * @date 2021/10/17 18:30
 **/
public class UsingCallableMain {


    public static void main(String[] args) {

//        AtomicInteger result = new AtomicInteger();
        int result = 0;
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，

        ComputeThread computeThread = new ComputeThread();
        FutureTask futureTask = new FutureTask(computeThread);
        new Thread(futureTask).start();
        try {
            System.out.println("得到的返回结果是:" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);
        System.out.println("异步计算结果为 = 24157817");

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }
}

class ComputeThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return fibo(36);
    }

    private static Integer fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }
}