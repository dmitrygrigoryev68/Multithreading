package Introduction;

public class Printer extends Thread {
    private static final long MILLIS = 100;
    private char symb;
    private int nPrints;

    public Printer(char symb, int nPrints) {
        this.symb = symb;
        this.nPrints = nPrints;
    }

    public void run() {
        for (int i = 0; i < nPrints; i++) {
            System.out.print(symb);

            try {
                sleep(MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
