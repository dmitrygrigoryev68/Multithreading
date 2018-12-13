package ReentrantReadWhriteLock;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ListNumbersLock extends Thread {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 500;

    static List<Integer> numbers;
    static ReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock readLock = lock.readLock();
    static Lock writeLock = lock.writeLock();

    private int nRuns;
    private int writeProbability;

    public ListNumbersLock(int nRuns, int writeProbability) {
        super();
        this.nRuns = nRuns;
        this.writeProbability = writeProbability;
    }

    public static void setNumbers(List<Integer> numbers) {
        ListNumbersLock.numbers = numbers;
    }

    @Override
    public void run() {
        for (int i = 0; i < nRuns; i++) {
            if (getRandomNumber(0, 100) <= writeProbability) {
                try {
                    insertNumber();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    getNumber();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Integer getNumber() throws InterruptedException {
        boolean fLock = readLock.tryLock(300, TimeUnit.MILLISECONDS);
        if (!fLock) {
            System.out.println("Read lock is blocked");
            return null;
        }
        try {
            int size = numbers.size();
            int index = getRandomNumber(0, size);
            Integer res = numbers.get(index);
            return res;
        } finally {
            readLock.unlock();
        }
    }

    private void insertNumber() throws InterruptedException {
        boolean fLock = writeLock.tryLock(300, TimeUnit.MILLISECONDS);
        if (!fLock) {
            System.out.println("Write lock is blocked");
            return;
        }
        try {
            int size = numbers.size();
            int index = getRandomNumber(0, size);
            int number = getRandomNumber(MIN_NUMBER, MAX_NUMBER);
            numbers.add(index, number);
        } finally {
            writeLock.unlock();
        }
    }

    private int getRandomNumber(int min, int max) {
        return min + new Random().nextInt(max - min);
    }

}