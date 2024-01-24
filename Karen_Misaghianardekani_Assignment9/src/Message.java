public class Message {

    String senderNumber;
    String recieverNumber;
    String message;
    boolean isRead;

    public Message(String senderNumber, String recieverNumber, String message, boolean isRead ){
        this.senderNumber = senderNumber;
        this.recieverNumber = recieverNumber;
        this.message = message;
        this.isRead = isRead;
    }
    
}
