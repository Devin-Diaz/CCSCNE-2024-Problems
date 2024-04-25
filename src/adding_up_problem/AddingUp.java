package adding_up_problem;
import java.util.*;

public class AddingUp {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        List<Integer> inputs = inputToList(scnr);
        long time = System.currentTimeMillis();
        for (Integer num: inputs) {
            int max = 1;
            for (int i = 1; i < num; i++) {
                for (int j = 0; sum(i, i + j) <= num; j++) {
                    if (sum(i, i + j) == num) {
                        max = Math.max(max, j + 1);
                    }
                }
            }
            System.out.println(max);
        }
        System.out.println(System.currentTimeMillis() - time);
        scnr.close();
    }
    private static int sum(int start, int stop) {
        // Returns the sum of the sequence of numbers from start to stop, inclusive.
        int sum1 = (stop * (stop + 1) / 2);
        int sum2 = (start * (start - 1) / 2);
        return sum1 - sum2;
    }
    private static List<Integer> inputToList(Scanner scnr) {
        // Adds input integers to a list for later processing. Terminates when -1 is entered.
        List<Integer> out = new ArrayList<>();
        while (scnr.hasNext()) {
            int temp = scnr.nextInt();
            if (temp == -1) {
                break;
            }
            out.add(temp);
        }
        return out;
    }
}