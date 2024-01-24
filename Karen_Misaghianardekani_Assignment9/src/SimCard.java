import java.util.ArrayList;

public class SimCard {

    ArrayList<Message> sentMessages;
    ArrayList<Message> recievedMessages;
    String cellNumber;

    SimCard(ArrayList<Message> sentMessages, ArrayList<Message> recievedMessages, String cellNumber) {

        this.sentMessages = setArrayList(sentMessages);
        this.recievedMessages = setArrayList(recievedMessages);
        this.cellNumber = cellNumber;
    }

    public ArrayList<Message> setArrayList(ArrayList<Message> enteredMessages) {
        ArrayList<Message> messages = new ArrayList<Message>();
        if (enteredMessages != null) {
            for (Message message : enteredMessages) {
                messages.add(message);
            }
        }

        return messages;
    }
}
