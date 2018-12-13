package WaitNotifyMessageBox;

public class Sender extends Thread {
    private static final int D_N_MESSAGES = 200;
    private MessageBox messageBoxOdd;
    private MessageBox messageBoxEven;
    private int nMessages = D_N_MESSAGES;

    public Sender(MessageBox messageBoxOdd, MessageBox messageBoxEven) {
        super();
        this.messageBoxOdd = messageBoxOdd;
        this.messageBoxEven = messageBoxEven;
    }

    @Override
    public void run() {
        for (int i = 0; i <= nMessages; i++) {
            if (i % 2 == 0) {
                messageBoxEven.putMessage("message " + i);
            }
            else {
                messageBoxOdd.putMessage("message " + i);
            }
        }
    }

    public int getnMessages() {
        return nMessages;
    }

    public void setnMessages(int nMessages) {
        this.nMessages = nMessages;
    }
}
