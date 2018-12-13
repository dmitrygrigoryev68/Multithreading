package Printers;

public class Printer extends Thread {
    String symbols;
    private long interval;

    public Printer(String symbols, int interval) {
        super();
        this.symbols = symbols;
        this.interval = interval*1000;
        setDaemon(true);
    }

    @Override
    public void run() {
        int current = 0;
        while(true) {
            System.out.print(symbols.charAt(current));
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                current++;
                if(current == symbols.length())
                    current = 0;
            }
        }
    }
}