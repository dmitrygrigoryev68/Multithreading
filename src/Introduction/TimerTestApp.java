package Introduction;

public class TimerTestApp {
    private static final long WAITING_TIME = 5000;

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.start();
        timer.join(WAITING_TIME);
    }
}
