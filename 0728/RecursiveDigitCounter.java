public class RecursiveDigitCounter {
    public static void main(String[] args) {
        printResult(5729, 7);
        printResult(707070, 7);
        printResult(1005, 0);
        printResult(0, 0);
        printResult(2468, 9);
        printResult(-1221, 1);
    }

    public static int countDigit(int number, int target) {
        if (target < 0 || target > 9) {
            throw new IllegalArgumentException("target 必須介於 0 到 9");
        }

        long value = Math.abs((long) number);
        if (value == 0) {
            return target == 0 ? 1 : 0;
        }
        return countDigitRecursive(value, target);
    }

    private static int countDigitRecursive(long number, int target) {
        if (number == 0) {
            return 0;
        }

        int currentDigit = (int) (number % 10);
        int currentCount = currentDigit == target ? 1 : 0;
        return currentCount + countDigitRecursive(number / 10, target);
    }

    private static void printResult(int number, int target) {
        System.out.println(number + " 中的 " + target + " 出現 " +
            countDigit(number, target) + " 次");
    }
}
