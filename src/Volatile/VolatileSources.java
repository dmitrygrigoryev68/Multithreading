package Volatile;

import java.util.Scanner;

public class VolatileSources extends Thread {
    private volatile boolean running = true;

    public void run() {
        while (running) {
            System.out.println("Running");
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutDown() {
        running = false;
    }
}

class Main {
    public static void main(String[] args) {
        VolatileSources vl = new VolatileSources();
        vl.start();

        Scanner input = new Scanner(System.in);
        input.nextLine();
        vl.shutDown();
        System.out.println("Finished");
    }
}