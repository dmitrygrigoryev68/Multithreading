package WaitNotifyMessageBox;

public class MessageBoxReadWrite implements MessageBox {
    private String message;
    private Object senderMonitor = new Object();


    @Override
    public void putMessage(String message) {
        synchronized (senderMonitor) {
            while (this.message != null) {
                try {
                    senderMonitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.message = message;
        }
        synchronized (this) {
            this.notify();
        }
    }

    @Override
    public String getMessage() {
        String message = null;

        synchronized (this) {
            while (this.message == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            message = this.message;
            this.message = null;
        }
        synchronized (senderMonitor) {
            senderMonitor.notify();
        }

        return message;
    }
}
