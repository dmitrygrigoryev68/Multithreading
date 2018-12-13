package RunnableCallableSum;

import java.util.List;
import java.util.concurrent.Callable;

public class ListSumCallable implements Callable<Integer> {
    List<Integer> numbers;

    public ListSumCallable(List<Integer> numbers) {
        super();
        this.numbers = numbers;
    }

    @Override
    public Integer call() throws Exception {
        return numbers.stream().mapToInt(i -> i).sum();
    }
}
