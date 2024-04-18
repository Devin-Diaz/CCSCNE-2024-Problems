import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class ElevationGain {
    public static void main(String[] args) {
        startProgram();
    }

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
        for(List<Integer> input : allHikes) {
            displayArray(benchmarkChecker(input));
        }
    }

    private static int[] benchmarkChecker(List<Integer> hike) {
        int[] benchmarks = new int[3];
        Arrays.fill(benchmarks, -1);

        int currentBenchmark = 0;
        int totalElevation = 0;

        for(int i = 0; i < hike.size() - 1; i++) {
            if(hike.get(i + 1) > hike.get(i)) {
                totalElevation += hike.get(i + 1) - hike.get(i);

                if(totalElevation > 16 * Math.pow(2, currentBenchmark)) {
                    benchmarks[currentBenchmark] = i + 1;
                    currentBenchmark++;
                }
            }
        }
        return benchmarks;
    }

    private static void displayArray(int[] array) {
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println("\n");
    }

}
