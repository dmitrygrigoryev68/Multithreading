package RunnableCallableSum;

import java.util.List;

public class ListSumRunnable implements Runnable {
    private List<Integer> numbers;
    private int sum;

    public ListSumRunnable(List<Integer> numbers) {
        super();
        this.numbers = numbers;
    }

    @Override
    public void run() {
        sum = numbers.stream().mapToInt(i -> i).sum();
    }

    public int getSum() {
        return sum;
    }
}