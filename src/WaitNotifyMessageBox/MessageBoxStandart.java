package WaitNotifyMessageBox;

public class MessageBoxStandart implements MessageBox {
    private String message;

    @Override
    public void putMessage(String message){
        synchronized(this){
            while(this.message!=null){
                try {
                    this.wait();
                } catch (InterruptedException e) {

                }
            }
            this.message=message;
            this.notify();
        }
    }

    @Override
    public String getMessage(){
        String message=null;
        synchronized(this){
            while(this.message==null){
                try {
                    this.wait();
                } catch (InterruptedException e) {

                }
            }
            message=this.message;
            this.message=null;
            this.notifyAll();
        }
        return message;
    }
}