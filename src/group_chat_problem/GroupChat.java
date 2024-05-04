package group_chat_problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GroupChat {
    public static void main(String[] args) {

        /* SUCCESSFULLY PARSED INPUTS
        1
        3
        alice;-4;F
        bob;-8;C
        charlie;3;C
        bob: brrr, it is only 5 degrees
        charlie: it was that cold here at 3:30
        bob: well it is 5:00 here
        alice: bye


         [ Groups:

            [   Group:

                Members:
                [ alice;-4;F , bob;-8;C , charlie;3;C ]

                Messages:
                [ bob: brrr, it is only 5 degrees, charlie: it was that cold here at 3:30,
                  bob: well it is 5:00 here, alice: bye
                ]

            ]

         ]

        */


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
                Message message = new Message(parsedMessageData[0], parsedMessageData[1]);


                if(message.text.equals("bye")) {
                    group.addMessageToGroup(message);
                    break;
                }

                group.addMessageToGroup(message);
            }
            groups.add(group);
        }


        for(Group group : groups) {
            System.out.println(doingConverts(group.members, group.messages));
        }

    }

    public static List<String> doingConverts(List<Member> members, List<Message> messages) {
        List<String> output = new ArrayList<>();

        int timezoneDictator = members.get(0).timezone;
        String tempDictator = members.get(0).temperatureUnit;

        for(Message message : messages) {
            String[] parsedMessage = message.text.split("\\s");

            for(int i = 0; i < parsedMessage.length; i++) {

                String[] furtherParsedMessage = parsedMessage[i].split(":");

                if(furtherParsedMessage.length == 2) {
                    int time = Integer.parseInt(furtherParsedMessage[0]);

                    int newTime = time + timezoneDictator;
                    if(newTime <= 0) newTime += 12;

                    String confirmedNewTime = String.valueOf(newTime);

                    furtherParsedMessage[0] = confirmedNewTime;
                    parsedMessage[i] = furtherParsedMessage[0];

                    String str = String.join(", ", parsedMessage);
                    output.add(str);
                    break;

                }

                if(parsedMessage[i].equals("degrees")) {
                    if(tempDictator.equals(verifyTempInformation(message.sender, members))) {
                        output.add(message.text);
                        break;
                    }

                    if(tempDictator.equals("F")) {
                        int temp = Integer.parseInt(parsedMessage[i - 1]);
                        double answer = (double) 9 / 5 * temp + 32;
                        String newTemp = Double.toString(answer);
                        parsedMessage[i - 1] = newTemp;
                        String str = String.join(", ", parsedMessage);

                        output.add(str);
                    }
                    else {
                        int temp = Integer.parseInt(parsedMessage[i - 1]);
                        double answer = (double) 5 / 9 * (temp - 32);
                        String newTemp = Double.toString(answer);
                        parsedMessage[i - 1] = newTemp;
                        String str = String.join(", ", parsedMessage);

                        output.add(str);
                    }
                }

                //if(parsedMessage[i].equals())




            }

        }

        return output;
    }

    public static String verifyTempInformation(String sender, List<Member> members) {
        String data = "";

        for(Member member : members) {
            if(member.name.equals(sender)) {
                data = member.temperatureUnit;
            }
        }
        return data;
    }



}
