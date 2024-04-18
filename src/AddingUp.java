import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddingUp {

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        List<Integer> inputs = new ArrayList<>();
//
//        while(sc.hasNextInt()) {
//            int input = sc.nextInt();
//            if(input == -1) {
//                break;
//            }
//            else {
//                inputs.add(input);
//            }
//        }
//        for(Integer n : inputs) {
//            System.out.println(addingUp(n));
//        }

        System.out.println(findSum(5, 1));
    }

    public static int addingUp (int target) {
        int maxLength = 0;

        for(int i = 0; i < target; i++) {
            int sum = 0;
            int currentLength = 0;
            for(int j = i; sum < target; j++) {
                sum += j;
                currentLength++;
                if(sum == target) {
                    maxLength = Math.max(maxLength, currentLength);
                }
            }
        }
        return maxLength;
    }

    public static int findSum(int sum, int start) {
        int maxLength = 1;
        int tempSum = start;

        while(tempSum < sum) {
            tempSum += start;
            start++;
            maxLength++;
        }

        if(tempSum == sum) {
            return maxLength;
        }
        return -1;
    }

}
