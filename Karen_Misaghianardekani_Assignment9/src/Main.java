import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Scanner inputReader = new Scanner(System.in);

    public static ArrayList<SimCard> simCards = new ArrayList<SimCard>();

    public static void showMenue() {
        System.out.println("Welcom to our messenger app, please enter the action you want to do: ");
        System.out.println(
                "1-send a message \n2-read the whole messages \n3-read only read/unread messages \n4-search in messages \n5-create a new simcard");
    }

    public static int getUserInput() {
        int userInput = inputReader.nextInt();
        inputReader.nextLine();
        return userInput;
    }

    public static void handleActions(int actionNumber) {
        switch (actionNumber) {
            case 1:
                sendMessage();
                break;
            case 2:
                readAllMessages();
                break;
            case 3:
                readFilteredMessages();
                break;
            case 4:
                searchInMessages();
                break;
            case 5:
                createNewSimCard();

            default:
                getAndProcessUserInput();
                break;
        }
        // getAndProcessUserInput();
    }

    public static void getAndProcessUserInput() {
        showMenue();

        int actionNumber = getUserInput();

        handleActions(actionNumber);
    }

    public static void createNewSimCard() {
        System.out.println("please enter your number... ");
        String cellNumber = inputReader.nextLine();

        SimCard simCard = new SimCard(null, null, cellNumber);

        simCards.add(simCard);
        getAndProcessUserInput();
    }

    public static SimCard detectSimCard(String cellNumber) {
        SimCard selectedSimCard = null;
        for (SimCard simCard : simCards) {
            if (simCard.cellNumber.equals(cellNumber)) {
                selectedSimCard = simCard;
            }
        }
        return selectedSimCard;
    }

    public static void sendMessage() {
        System.out.println("please enter your number... ");
        String senderNumber = inputReader.nextLine();

        SimCard senderSimCard = detectSimCard(senderNumber);

        System.out.println("please enter the number you want to wright message to... ");
        String recieverNumber = inputReader.nextLine();

        SimCard recieverSimCard = detectSimCard(recieverNumber);

        if (senderSimCard != null && recieverSimCard != null) {
            System.out.println("please enter the message... ");
            String messageContent = inputReader.nextLine();

            Message message = new Message(senderNumber, recieverNumber, messageContent, false);

            // System.out.println(message.senderNumber);
            // System.out.println(message.message);

            senderSimCard.sentMessages.add(message);
            recieverSimCard.recievedMessages.add(message);
        } else if (senderSimCard == null) {
            System.out.println("invalid sender number, please try again: ");
            sendMessage();
        } else {
            System.out.println("invalid reciever number, please try again: ");
            sendMessage();
        }

        getAndProcessUserInput();
    }

    public static void readAllMessages() {
        System.out.println("please enter your number... ");
        String numberOfUser = inputReader.nextLine();

        SimCard selecteSimCard = detectSimCard(numberOfUser);

        ArrayList<Message> recievedMessages = selecteSimCard.recievedMessages;
        ArrayList<Message> sentMessages = selecteSimCard.sentMessages;

        for (Message message : recievedMessages) {
            System.out.println("sender's number: " + message.senderNumber);
            System.out.println("message: " + message.message);
            message.isRead = true;
        }
        for (Message message : sentMessages) {
            System.out.println("reciever's number: " + message.recieverNumber);
            System.out.println("message: " + message.message);
            message.isRead = true;
        }
        getAndProcessUserInput();
    }

    public static void readFilteredMessages() {
        System.out.println("please enter your number... ");
        String numberOfUser = inputReader.nextLine();

        SimCard selecteSimCard = detectSimCard(numberOfUser);

        ArrayList<Message> recievedMessages = selecteSimCard.recievedMessages;
        ArrayList<Message> sentMessages = selecteSimCard.sentMessages;

        System.out.println("if you want to see read messages enter 1, else enter 2... ");
        int answerOfUser = inputReader.nextInt();
        inputReader.nextLine();

        if (answerOfUser == 1) {
            for (Message message : recievedMessages) {
                if (message.isRead) {
                    System.out.println("sender's number: " + message.senderNumber);
                    System.out.println("message: " + message.message);
                    message.isRead = true;
                }

            }
            for (Message message : sentMessages) {
                if (message.isRead) {
                    System.out.println("reciever's number: " + message.recieverNumber);
                    System.out.println("message: " + message.message);
                    message.isRead = true;
                }

            }
        } else {
            for (Message message : recievedMessages) {
                if (!message.isRead) {
                    System.out.println("sender's number: " + message.senderNumber);
                    System.out.println("message: " + message.message);
                    message.isRead = true;
                }

            }
            for (Message message : sentMessages) {
                if (!message.isRead) {
                    System.out.println("reciever's number: " + message.recieverNumber);
                    System.out.println("message: " + message.message);
                    message.isRead = true;
                }

            }
        }

        getAndProcessUserInput();
    }

    public static void searchInMessages() {
        System.out.println("please enter your number... ");
        String numberOfUser = inputReader.nextLine();

        System.out.println("please enter the phrase you want to search ");
        String searchedItem = inputReader.nextLine();

        SimCard selecteSimCard = detectSimCard(numberOfUser);

        ArrayList<Message> recievedMessages = selecteSimCard.recievedMessages;
        ArrayList<Message> sentMessages = selecteSimCard.sentMessages;

        for (Message message : recievedMessages) {
            String messageToLowerCase = message.message.toLowerCase();
            String searchedItemToLowerCase = searchedItem.toLowerCase();
            if (messageToLowerCase.contains(searchedItemToLowerCase)) {
                System.out.println("sender's number: " + message.senderNumber);
                System.out.println("message: " + message.message);
                message.isRead = true;
            }

        }
        for (Message message : sentMessages) {
            String messageToLowerCase = message.message.toLowerCase();
            String searchedItemToLowerCase = searchedItem.toLowerCase();
            if (messageToLowerCase.contains(searchedItemToLowerCase)) {
                System.out.println("reciever's number: " + message.recieverNumber);
                System.out.println("message: " + message.message);
                message.isRead = true;
            }
            getAndProcessUserInput();

        }
    }

    public static void main(String[] args) {
        getAndProcessUserInput();

    }

}
