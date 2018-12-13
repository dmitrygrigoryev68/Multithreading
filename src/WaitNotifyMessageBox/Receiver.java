package WaitNotifyMessageBox;

public class Receiver extends Thread {
    private MessageBox messageBox;

    public void setMessageBox(MessageBox messageBox) {
        this.messageBox = messageBox;
    }

    public Receiver() {
        setDaemon(true);
    }

    @Override
    public void run() {
        while(true) {
            String message = messageBox.getMessage();
            System.out.println("Thread id: " + getId() + " message: " + message);
        }
    }
}