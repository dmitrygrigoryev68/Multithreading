package Introduction;

import java.time.LocalTime;

public class Timer extends Thread {
    private int sleepingTime = 1000;

    public Timer() {
        setDaemon(true);

    }

    public void setSleepingTime(int sleepingTime) {
        this.sleepingTime = sleepingTime;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(LocalTime.now());
            try {
                sleep(sleepingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
