package group_chat_problem;

/**
 * Represents a message in the group chat with a sender and the message content.
 */
public class Message {
    public String sender;
    public String text;

    public Message(String sender, String text) {
        this.sender = sender;
        this.text = text;
    }
}
