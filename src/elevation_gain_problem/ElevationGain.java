package elevation_gain_problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class ElevationGain {
    public static void main(String[] args) {
        runApplicationLoop();
    }
    /**
     * The introduction of the program
     * logic that prompts the user whether they want to continue executing program
     */
    private static void runApplicationLoop() {
        System.out.println("""
                TOTAL ELEVATION GAIN
                .
                .
                """);

        Scanner sc = new Scanner(System.in);
        boolean programState = true;

        while(programState) {
            processHikes();
            String userResponse = getUserInput(sc);

            if(userResponse.equalsIgnoreCase("n")) {
                System.out.println("\n" + "**TERMINATED PROGRAM**");
                programState = false;
            }
            else {
                System.out.println("\n" + "RESTARTING...");
            }
        }
        sc.close();
    }


    /**
     * @param sc from runApplicationLoop() method to ensure that a user enters yes or no for program state
     * @return String that is y or n (ignore casing)
     */
    private static String getUserInput(Scanner sc) {
        String userResponse;

        while(true) {
            System.out.print("\n" + "DO YOU WANT TO RUN PROGRAM AGAIN? (y/n): ");
            userResponse = sc.next();
            if(userResponse.equalsIgnoreCase("y") || userResponse.equalsIgnoreCase("n")) {
                break;
            }
            else {
                System.out.println("\n" + "***Invalid input. Please enter 'y' for yes or 'n' for no.***");
            }
        }
        return userResponse;
    }


    /**
     * Processes user input for multiple hikes and calculates benchmark indices
     * Each line of input represents a hike with a series of elevation gains
     * This function reads the input until a single -1 is encountered singling the end
     */
    private static void processHikes() {
        Scanner sc = new Scanner(System.in);

        List<List<Integer>> allHikes = new ArrayList<>();
        List<Integer> hike = new ArrayList<>();

        System.out.print("ADD A SEQUENCE OF NUMBERS (-1 to quit): ");

        while(sc.hasNext()) {
            String hikeInput = sc.nextLine();

            if(hikeInput.equals("-1")) break;
            String[] inStringSplit = hikeInput.split("\\s");

            for(String s : inStringSplit) {
                int number = Integer.parseInt(s);
                if(number != -1) {
                    hike.add(number);
                }
                else break;
            }
            allHikes.add(hike);
            hike = new ArrayList<>();
            System.out.print("ADD A SEQUENCE OF NUMBERS: ");

        }
        sc.close();
        processAndDisplayHikes(allHikes);
    }


    /**
     * @param allHikes 2-D ArrayList of integers that contain each sequence of hikes
     * calculates and displays the index of when benchmark is surpassed for each hike
     * If benchmark is never passed a -1 is placed in its spot
     */
    private static void processAndDisplayHikes(List<List<Integer>> allHikes) {
        for(List<Integer> hike : allHikes) {
            displayArray(calculateBenchmarkIndices(hike));
        }
    }


    /**
     * @param hike a sequence of integers that represent a hike
     * @return an array of length 3. Each index representing when an elevation is greater than a benchmark
     * -1 remains in index if elevation never passed benchmark of 16, 32, or 64
     */
    private static int[] calculateBenchmarkIndices(List<Integer> hike) {
        int[] benchmark_outputs = new int[3];
        Arrays.fill(benchmark_outputs, -1);

        int currentBenchmark = 0;
        int totalElevation = 0;

        for(int i = 0; i < hike.size() - 1; i++) {
            if(hike.get(i + 1) > hike.get(i)) {
                totalElevation += hike.get(i + 1) - hike.get(i);

                if(totalElevation >= 16 * Math.pow(2, currentBenchmark)) {
                    benchmark_outputs[currentBenchmark] = i + 1;
                    currentBenchmark++;
                }
            }
        }
        return benchmark_outputs;
    }


    /**
     *  displays integer arrays contents in a single spaced line
     */
    private static void displayArray(int[] array) {
        System.out.print("RESULT: ");
        for (int j : array) {
            System.out.print(j + " ");
        }
    }

}
