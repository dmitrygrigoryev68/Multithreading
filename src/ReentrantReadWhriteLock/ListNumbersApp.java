package ReentrantReadWhriteLock;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ListNumbersApp {
    private static final int N_THREADS = 1000;
    private static final int N_RUNS = 1000;
    private static final int WRITE_PROBABILITY = 30;
    private static final int N_NUMBERS = 1000;

    public static void main(String[] args) throws InterruptedException {
        Instant start = Instant.now();
        ListNumbersLock[] listNumbersLocks = new ListNumbersLock[N_THREADS];

        List<Integer> numbers = new Random().ints(N_NUMBERS)
                .boxed()
                .collect(Collectors.toList());

        ListNumbersLock.setNumbers(numbers);

        for(int i = 0; i < N_THREADS; i++) {
            listNumbersLocks[i] = new ListNumbersLock(N_RUNS, WRITE_PROBABILITY);
            listNumbersLocks[i].start();
        }
        for(int i = 0; i < N_THREADS; i++) {
            listNumbersLocks[i].join();
        }

        System.out.println("Running time was: " + ChronoUnit.MILLIS.between(start, Instant.now()));
    }
}
