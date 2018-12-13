package ThreadsCountersAtomic;

import ThreadsCountersAtomic.Counters;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class CountersApp {

    private static final int N_COUNTERS = 1000;
    private static final int N_RUNS = 100000;

    public static void main(String[] args) throws InterruptedException {
        Counters[] counters = new Counters[N_COUNTERS];
        Instant start = Instant.now();
        for(int i = 0; i < counters.length; i++) {
            counters[i] = new Counters(N_RUNS);
            counters[i].start();
        }
        for(int i = 0; i < counters.length; i++) {
            counters[i].join();
        }
        Instant finish = Instant.now();
        System.out.println(String.format("Counter1=%d, counter2=%d", Counters.getCount1(), Counters.getCount2()));
        System.out.println(String.format("Running time (millis) = %d", ChronoUnit.MILLIS.between(start, finish)));
    }

}