package group_chat_problem;

import java.util.ArrayList;
import java.util.Scanner;

public class GroupChat {
    public static void main(String[] args) {

        /* SUCCESSFULLY PARSED INPUTS

        INPUT:
        1
        2
        alice;5;F
        bob;4;C
        alice: hello world!
        bob: java better fr
        alice: bye

        OUTPUT (DEBUGGING):
        Group:
        Members: [alice;5;F, bob;4;C]
        Messages: [hello world!, Java better fr]

        An ArrayList is used to store each Group object
        Group is constructed out of array list of Member and String for messages
        members is an arraylist of type member
        messages is an array list of strings */

        Scanner sc = new Scanner(System.in);
        ArrayList<Group> groups = new ArrayList<>();

        // we know for sure # of cases is first and not be repeated thus:
        int numberOfTestCases = sc.nextInt();
        sc.nextLine();

        // next we have number of members, note we will have different members for each test case

        for(int i = 0; i < numberOfTestCases; i++) {
            Group group = new Group();

            int numberOfMembers = sc.nextInt();
            sc.nextLine();

            // now we actually start receiving members
            for(int j = 0; j < numberOfMembers; j++) {

                String memberInput = sc.nextLine();
                String[] parsedMemberData = memberInput.split(";");

                String name = parsedMemberData[0];
                int timezone = Integer.parseInt(parsedMemberData[1]);
                String tempUnit = parsedMemberData[2];

                Member member = new Member(name, timezone, tempUnit);
                group.addMemberToGroup(member);

            }

            //parsing messages
            while(sc.hasNext()) {
                String messageInput = sc.nextLine();
                String[] parsedMessageData = messageInput.split(": ");
                String parsedMessage = parsedMessageData[1];


                if(parsedMessage.equals("bye")) {
                    break;
                }

                group.addMessageToGroup(parsedMessage);
            }

            group.displayGroup();
        }
    }

}
