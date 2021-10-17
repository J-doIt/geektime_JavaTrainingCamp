package org.kayla.concurrency.conc0302.atomic;

/**
 * Count
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/17 16:55
 **/
public class Count {

    private int num = 0;

    public int add() {
        return num++;
    }

    public int getNum() {
        return num;
    }
}
