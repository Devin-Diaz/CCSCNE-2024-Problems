package group_chat_problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GroupChat {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Group> groups = new ArrayList<>();

        int numberOfTestCases = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < numberOfTestCases; i++) {
            Group group = new Group();

            int numberOfMembers = sc.nextInt();
            sc.nextLine();

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
            displayOutput(doingConverts(group.members, group.messages));
        }

    }

    public static List<String> doingConverts(List<Member> members, List<Message> messages) {
        List<String> output = new ArrayList<>();

        int timezoneDictator = members.getFirst().timezone;
        String tempDictator = members.getFirst().temperatureUnit;

        for(Message message : messages) {
            String[] parsedMessage = message.text.split("\\s");

            for(int i = 0; i < parsedMessage.length; i++) {
                String[] furtherParsedMessage = parsedMessage[i].split(":");

                if(furtherParsedMessage.length == 2 && furtherParsedMessage[1].matches("\\d+")) {
                    StringBuilder sb = new StringBuilder();
                    int time = Integer.parseInt(furtherParsedMessage[0]);
                    int newTime = time + timezoneDictator;
                    if(newTime <= 0) newTime += 12;

                    sb.append(newTime).append(":").append(furtherParsedMessage[1]);
                    parsedMessage[i] = sb.toString();
                }

                if(parsedMessage[i].equals("degrees")) {
                    int temp = Integer.parseInt(parsedMessage[i - 1]);
                    double convertedTemp;

                    if(tempDictator.equals("F") && !verifyTempInformation(message.sender, members).equals("F")) {
                        convertedTemp = (double) 9 / 5 * temp + 32;
                    }
                    else if(tempDictator.equals("C") && !verifyTempInformation(message.sender, members).equals("C")) {
                        convertedTemp = (double) 5 / 9 * (temp - 32);
                    }
                    else {
                        String newText = message.sender + message.text;
                        output.add(newText);
                        break;
                    }
                    int finalTemp = (int)convertedTemp;
                    parsedMessage[i - 1] = String.valueOf(finalTemp);
                }
            }

            String str = String.join(" ", parsedMessage);

            String newText = message.sender + ": " + str;
            output.add(newText);
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

    public static void displayOutput(List<String> output) {
        for(String s : output) {
            System.out.println(s);
        }
    }

}
