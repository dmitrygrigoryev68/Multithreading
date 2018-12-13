package WaitNotifyMessageBox;

import java.util.*;

public class MessageBoxQueue implements MessageBox {
    private Queue<String> queue = new LinkedList<>();

    @Override
    public synchronized void putMessage(String message) {
        queue.offer(message);
        notify();
    }

    @Override
    public synchronized String getMessage() {
        String message = null;
        synchronized (this) {
            while (queue.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {

                }
            }
            message = queue.poll();
            notify();
        }
        return message;
    }

}
