package group_chat_problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class simulates a test environment for a group chat system where users can send and receive messages.
 * It handles multiple chat groups, reads user input for members and messages, and processes those messages.
 */
public class GroupChatTester {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Group> allGroupChats = new ArrayList<>();

        int numberOfTestCases = sc.nextInt();
        sc.nextLine();

        // Loop to initialize each group chat based on user input.
        for(int i = 0; i < numberOfTestCases; i++) {
            Group group = new Group();

            int totalMembersInGroup = sc.nextInt();
            sc.nextLine();

            // Add members to the group.
            for(int j = 0; j < totalMembersInGroup; j++) {
                String rawMemberInput = sc.nextLine();
                String[] parsedMemberInformation = rawMemberInput.split(";");

                String name = parsedMemberInformation[0];
                int timezone = Integer.parseInt(parsedMemberInformation[1]);
                String temperatureUnit = parsedMemberInformation[2];

                Member member = new Member(name, timezone, temperatureUnit);
                group.addMemberToGroup(member);
            }

            // Read messages until "bye" is encountered.
            while(sc.hasNext()) {
                String rawMessageInput = sc.nextLine();
                String[] parsedMessageData = rawMessageInput.split(": ");
                String sender = parsedMessageData[0];
                String text = parsedMessageData[1];
                Message message = new Message(sender, text);

                if(text.equals("bye")) {
                    group.addMessageToGroup(message);
                    break;
                }
                group.addMessageToGroup(message);
            }
            allGroupChats.add(group);
        }

        translationOfGroupChatMessages(allGroupChats);
    }

    /**
     * Translates messages in a group chat by adjusting time and temperature units based on the first member's settings.
     *
     * @param allMembers List of members in the group.
     * @param allMessages List of messages to be translated.
     * @return List of translated messages as strings.
     */
    private static List<String> translateMessages(List<Member> allMembers, List<Message> allMessages) {
        List<String> allTranslatedMessages = new ArrayList<>();

        int baseTimeZone = allMembers.get(0).timezone;
        String baseTemperature = allMembers.get(0).temperatureUnit;

        for(Message message : allMessages) {
            String[] parsedText = message.text.split("\\s");

            // Convert time and temperature in each message.
            for(int i = 0; i < parsedText.length; i++) {
                String[] parsedTime = parsedText[i].split(":");

                // Adjust the time based on the base timezone.
                if(parsedTime.length == 2 && parsedTime[1].matches("\\d+")) {
                    StringBuilder sb = new StringBuilder();
                    int originalTime = Integer.parseInt(parsedTime[0]);
                    int translatedTime = originalTime + baseTimeZone;
                    if(translatedTime <= 0) translatedTime += 12;

                    sb.append(translatedTime).append(":").append(parsedTime[1]);
                    parsedText[i] = sb.toString();
                }

                // Convert temperature based on the base temperature unit.
                if(parsedText[i].equals("degrees")) {
                    int originalTemperature = Integer.parseInt(parsedText[i - 1]);
                    double convertedTemperature;

                    if(baseTemperature.equals("F") && !verifySendersTemperatureInfo(message.sender, allMembers).equals("F")) {
                        convertedTemperature = (double) 9 / 5 * originalTemperature + 32;
                    }
                    else if(baseTemperature.equals("C") && !verifySendersTemperatureInfo(message.sender, allMembers).equals("C")) {
                        convertedTemperature = (double) 5 / 9 * (originalTemperature - 32);
                    }
                    else {
                        String originalTextWithSender = message.sender + message.text;
                        allTranslatedMessages.add(originalTextWithSender);
                        break;
                    }

                    int finalConvertedTemperature = (int)convertedTemperature;
                    parsedText[i - 1] = String.valueOf(finalConvertedTemperature);
                }
            }

            String translatedText = String.join(" ", parsedText);
            String translatedTextWithSender = message.sender + ": " + translatedText;
            allTranslatedMessages.add(translatedTextWithSender);
        }

        return allTranslatedMessages;
    }

    /**
     * Verifies the sender's preferred temperature unit.
     *
     * @param sender The name of the sender.
     * @param allMembers List of all members in the group.
     * @return The temperature unit used by the sender.
     */
    private static String verifySendersTemperatureInfo(String sender, List<Member> allMembers) {
        String sendersTemperatureUnit = "";

        for(Member member : allMembers) {
            if(member.name.equals(sender)) {
                sendersTemperatureUnit = member.temperatureUnit;
            }
        }
        return sendersTemperatureUnit;
    }

    /**
     * Displays translated messages on the console.
     *
     * @param allTranslatedMessages List of translated messages to be displayed.
     */
    private static void displayTranslatedMessages(List<String> allTranslatedMessages) {
        for(String message : allTranslatedMessages) {
            System.out.println(message);
        }
    }

    /**
     * Handles the translation of all messages in all group chats.
     *
     * @param allGroupChats List of all group chats to process.
     */
    private static void translationOfGroupChatMessages(List<Group> allGroupChats) {
        System.out.println("\n");
        for(Group group : allGroupChats) {
            displayTranslatedMessages(translateMessages(group.allMembers, group.allMessages));
        }
    }
}
