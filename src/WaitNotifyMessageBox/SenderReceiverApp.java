package WaitNotifyMessageBox;

public class SenderReceiverApp {

    private static final int N_RECEIVERS = 10;

    public static void main(String[] args) throws InterruptedException {
        MessageBox messageBoxOdd = new MessageBoxReadWrite();
        MessageBox messageBoxEven = new MessageBoxReadWrite();

        Sender sender = new Sender(messageBoxOdd, messageBoxEven);
        sender.start();

        for(int i = 0; i < N_RECEIVERS; i++) {
            Receiver receiver = new Receiver();
            receiver.setMessageBox( i % 2 == 0 ? messageBoxEven : messageBoxOdd);
            receiver.start();
        }
        sender.join();
    }
}
