import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

public class Main {

    private static final int ASCENDING_ORDER = 1;

    private static void radixSort(int[] nums, boolean ascending) {
        int maxDigits = 0;

        for (int num : nums) {
            int numLen = RadixSortUtils.numLength(num);
            if (numLen > maxDigits) {
                maxDigits = numLen;
            }
        }

        for (int digitPlace = 0; digitPlace < maxDigits; digitPlace++) {
            ArrayList<Deque<Integer>> buckets = new ArrayList<>(10);
            for (int i = 0; i < 10; i++) {
                buckets.add(new ArrayDeque<>());
            }

            for (int num : nums) {
                int digit = RadixSortUtils.getDigitAtIndexFromEnd(num, digitPlace);
                buckets.get(digit).add(num);
            }

            int currentIndex = 0;
            for (Deque<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    nums[currentIndex++] = bucket.poll();
                }
            }
        }

        if (!ascending) {
            for (int i = 0; i < nums.length / 2; i++) {
                int temp = nums[i];
                nums[i] = nums[nums.length - i - 1];
                nums[nums.length - i - 1] = temp;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int order = scanner.nextInt();
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = scanner.nextInt();
        }

        boolean ascending = (order == ASCENDING_ORDER ? true : false);

        radixSort(nums, ascending);

        System.out.println(Arrays.toString(nums));
    }
}
