package ReentrantReadWhriteLock;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Thread.sleep;

public class Printer {

    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Map<String, String> map = new HashMap<>();
        ReadWriteLock lock = new ReentrantReadWriteLock();

        Instant start = Instant.now();

        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
                lock.writeLock().lock();
                try {
                    sleep(1000);
                    map.put("thread", "some data");
                    System.out.println("write lock time: " + ChronoUnit.MILLIS.between(start, Instant.now()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.writeLock().unlock();
                }
            }
        };

        Runnable readTask = new Runnable() {
            @Override
            public void run() {
                lock.readLock().lock();
                try {
                    sleep(1000);
                    System.out.println(map.get("thread"));
                    System.out.println("read lock time: " + ChronoUnit.MILLIS.between(start, Instant.now()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.readLock().unlock();
                }
            }
        };

        executor.submit(writeTask);
        executor.submit(writeTask);
        executor.submit(readTask);
        executor.submit(readTask);

        executor.shutdown();
    }
}