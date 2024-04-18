import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class ElevationGain {

    // execute program
    public static void main(String[] args) {
        startProgram();
    }

    // terminate or restart program
    private static void startProgram() {
        System.out.println("""
                HIKING 101 GAME
                .
                .
                """);

        Scanner sc = new Scanner(System.in);
        boolean programState = true;

        while(programState) {
            program();
            System.out.println("DO YOU WANT TO RUN PROGRAM AGAIN? (y/n) ");
            String userResponse = sc.next();

            if(userResponse.equalsIgnoreCase("n")) {
                System.out.println("THANKS FOR PLAYING!");
                programState = false;
            }
            else {
                System.out.println("LETS GO AGAIN!");
            }
        }
    }

    /* receives user inputs of sequence of numbers, each input equating a "hike". Each integer represents an
       elevation while hiking. The index of the integer presents how many meters traveled.  */
    private static void program() {
        Scanner sc = new Scanner(System.in);

        List<List<Integer>> allHikes = new ArrayList<>();
        List<Integer> hike = new ArrayList<>();

        System.out.print("ADD A SEQUENCE OF NUMBERS: ");

        while(sc.hasNext()) {
            String hikeInput = sc.nextLine();

            if(hikeInput.equals("-1")) break;
            String[] inStringSplit = hikeInput.split("\\s");

            for(String s : inStringSplit) {
                int number = Integer.parseInt(s);
                if(number != -1) {
                    hike.add(number);
                }
                else {
                    allHikes.add(hike);
                    hike = new ArrayList<>();
                    System.out.print("ADD A SEQUENCE OF NUMBERS: ");
                }
            }
        }

        // parsing our result of the index when each benchmark is hit if ever hit
        for(List<Integer> input : allHikes) {
            displayArray(benchmarkChecker(input));
        }
    }

    /* iterates through our input and finds index when our elevation is greater than or equal to one of the benchmarks
      that being 16, 32, and 64. -1 is placed in the position of benchmark if never hit */
    private static int[] benchmarkChecker(List<Integer> hike) {
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

    // 
    private static void displayArray(int[] array) {
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println("\n");
    }

}
