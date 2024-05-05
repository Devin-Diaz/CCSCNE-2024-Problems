package group_chat_problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a group chat, maintaining lists of members and their messages.
 */
public class Group {
    public List<Member> allMembers;
    public List<Message> allMessages;

    public Group() {
        allMembers = new ArrayList<>();
        allMessages = new ArrayList<>();
    }

    public void addMemberToGroup(Member member) {
        allMembers.add(member);
    }
    public void addMessageToGroup(Message message) {
        allMessages.add(message);
    }

}
