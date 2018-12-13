package Introduction;

import Introduction.Printer;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class PrintersTestAppl {
    private static final char SYMB1 = '*';
    private static final char SYMB2 = '&';
    private static final int N_PRINTS = 50;

    public static void main(String[] args) throws InterruptedException {
        Printer p1 = new Printer(SYMB1, N_PRINTS);
        Printer p2 = new Printer(SYMB2, N_PRINTS);

        Instant start = Instant.now();
        p1.start();
        p2.start();
        p1.join();
        p2.join();

        Instant finish = Instant.now();
        System.out.println("");
        System.out.println("Time = " + ChronoUnit.MILLIS.between(start, finish));

    }


}
