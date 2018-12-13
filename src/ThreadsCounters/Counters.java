package ThreadsCounters;

public class Counters extends Thread {
    private static long count1 = 0;
    private static long count2 = 0;
    private static Object monitor = new Object();
    private int nRuns;

    public Counters(int nRuns) {
        super();
        this.nRuns = nRuns;
//		setDaemon(true);
    }

    @Override
    public void run() {
        for (int i = 0; i < nRuns; i++) {
            putCount1(1);
            putCount2(1);
        }
    }

    synchronized private static void putCount1(int number){
        count1 += number;
    }

    private static void putCount2(int number){
        synchronized (monitor) {
            count2 = count2 + number;
        }
    }

    public static long getCount1() {
        return count1;
    }

    public static long getCount2() {
        return count2;
    }
}