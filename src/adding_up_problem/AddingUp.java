package adding_up_problem;
import java.util.*;

/**
 * This program calculates the length of the longest consecutive sequence of
 * integers that sums up to the given input number.
 */
public class AddingUp {

    /**
     * The main method of the program.
     *
     * @param args The command-line arguments (unused).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> inputNumbers = readInputToList(scanner);

        for (int targetNumber : inputNumbers) {
            int maxSequenceLength = 1;
            for (int start = 1; start < targetNumber; start++) {
                for (int termCount = 0; sumOfSequence(start, start + termCount) <= targetNumber; termCount++) {
                    if (sumOfSequence(start, start + termCount) == targetNumber) {
                        maxSequenceLength = Math.max(maxSequenceLength, termCount + 1);
                    }
                }
            }
            System.out.println(maxSequenceLength);
        }

        scanner.close();
    }

    /**
     * Calculates the sum of the sequence of numbers from start to stop, inclusive.
     *
     * @param start The starting number of the sequence.
     * @param stop  The ending number of the sequence.
     * @return The sum of the sequence from start to stop.
     */
    private static int sumOfSequence(int start, int stop) {
        int sumStartToStop = (stop * (stop + 1) / 2);
        int sumStartMinusOne = (start * (start - 1) / 2);
        return sumStartToStop - sumStartMinusOne;
    }

    /**
     * Reads input integers from the given Scanner and adds them to a list for
     * later processing. The input terminates when -1 is entered.
     *
     * @param scanner The Scanner object for reading input.
     * @return A list of integers read from the input.
     */
    private static List<Integer> readInputToList(Scanner scanner) {
        List<Integer> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            int inputNumber = scanner.nextInt();
            if (inputNumber == -1) {
                break;
            }
            inputList.add(inputNumber);
        }
        return inputList;
    }

}
