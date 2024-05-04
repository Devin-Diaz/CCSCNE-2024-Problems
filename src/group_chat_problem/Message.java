package group_chat_problem;

public class Message {
    public String sender;
    public String text;

    public Message(String sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public String toString() {
        return sender + ": " + text;
    }


}
