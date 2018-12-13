package ThreadsCountersAtomic;

import java.util.concurrent.atomic.AtomicLong;

public class Counters extends Thread {
    private static AtomicLong count1 = new AtomicLong(0);
    private static AtomicLong count2 = new AtomicLong(0);
    private int nRuns;

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

    private static void putCount1(int number){
        count1.getAndAdd(number);
    }

    private static void putCount2(int number){
        count2.getAndAdd(number);
    }

    public static long getCount1() {
        return count1.get();
    }

    public static long getCount2() {
        return count2.get();
    }
}
