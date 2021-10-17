package org.kayla.concurrency.conc0301;

/**
 * Runner1
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/17 13:49
 **/
public class Runner1 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("进入Runner1运行状态——————————" + i);
        }
    }
}
