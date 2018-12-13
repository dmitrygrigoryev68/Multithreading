package ReetrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class Counters extends Thread {
    private static long count1 = 0;
    private static long count2 = 0;
    private int nRuns;

    static ReentrantLock lock = new ReentrantLock();

    public Counters(int nRuns) {
        super();
        this.nRuns = nRuns;
    }

    @Override
    public void run() {
        for (int i = 0; i < nRuns; i++) {
            putCount1(1);
            putCount2(1);
        }
    }

    private void putCount1(int number){
        lock.lock();
        try {
            count1 += number;
        } finally {
            lock.unlock();
        }
    }

    private void putCount2(int number){
        lock.lock();
        try {
            count2 += number;
        } finally {
            lock.unlock();
        }
    }

    public static long getCount1() {
        return count1;
    }

    public static long getCount2() {
        return count2;
    }
}