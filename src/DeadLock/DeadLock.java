package DeadLock;

public class DeadLock {
    public static Object res1 = new Object();
    public static Object res2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread1();
        Thread t2 = new Thread2();
        t1.start();
        t2.start();
    }

    private static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (res1) {
                System.out.println("Thread1: Holds res1");
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread1: Waits for res2 ");
                synchronized (res2) {
                    System.out.println("Thread1: Holds res2");
                }
            }
        }
    }

    private static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (res1) {
                System.out.println("Thread2: Holds res2");
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread2: Waits for res1 ");
                synchronized (res2) {
                    System.out.println("Thread2: Holds res1");
                }
            }
        }
    }
}