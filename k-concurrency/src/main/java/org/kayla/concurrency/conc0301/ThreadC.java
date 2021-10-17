package org.kayla.concurrency.conc0301;

import java.util.concurrent.Callable;

/**
 * ThreadC
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/17 13:50
 **/
public class ThreadC implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(500);
        System.out.println("这是线程C");
        return "线程C";
    }
}
