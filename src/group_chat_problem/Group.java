package group_chat_problem;

import java.util.ArrayList;
import java.util.List;

public class Group {

    public List<Member> members;
    public List<String> messages;

    public Group() {
        members = new ArrayList<>();
        messages = new ArrayList<>();
    }

    public void addMemberToGroup(Member member) {
        members.add(member);
    }

    public void addMessageToGroup(String message) {
        messages.add(message);
    }

    public void displayGroup() {
        StringBuilder sb = new StringBuilder();

        sb.append("Group:\n");
        sb.append("Members:\n");
        for (Member member : members) {
            sb.append(member).append("\n");
        }
        sb.append("Messages:\n");
        for (String message : messages) {
            sb.append(message).append("\n");
        }

        System.out.println(sb.toString());
    }

}
