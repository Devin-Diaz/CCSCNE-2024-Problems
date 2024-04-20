package elevation_gain_problem;/*
    TOTAL ELEVATION GAIN PROGRAM:

    Given multiple inputs of a sequence of integers. Each sequence of integers represents a hike. Each integer in the
    sequence represents an elevation as the ike progresses. The index of each integer represents how many meters
    the hiker has traveled. The goal is to find how many meters the hiker has traveled when he is greater than or
    equal to a certain elevation. These certain elevations are benchmarks of 16, 32, and 64. You can only pass a
    benchmark once. Sequence of integers will always be positive integers and you can never lose elevation. If
    a hiker never passes a certain benchmark, a -1 is returned for that position in the output. Note that -1 at the
    end of each sequence of numbers denotes the end of a hike. -1 on its own means termination of input.

    EXAMPLE:

    INPUT:
    0 3 6 3 6 9 6 9 12 15 18 18 15 12 18 21 24 -1
    0 -1
   -1

    OUTPUT:
    8 16 -1
   -1 -1 -1

*/
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
