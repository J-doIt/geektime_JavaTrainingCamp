package org.kayla.concurrency.conc0302.atomic;

/**
 * AtomicMain
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/17 16:55
 **/
public class AtomicMain {

    public static void main(String[] args) {
        final SyncCount count = new SyncCount();
//        final Count count = new Count();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10_000; j++) {
                    count.add();
                }
            }).start();
        }

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("num = " + count.getNum());
    }
}
