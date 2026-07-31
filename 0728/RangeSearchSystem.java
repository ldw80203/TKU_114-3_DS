public class RangeSearchSystem {
    public static void main(String[] args) {
        int[] values = {5, 10, 10, 10, 18, 25, 25, 25, 40, 50};

        printRange(values, 10);
        printRange(values, 25);
        printRange(values, 5);
        printRange(values, 50);
        printRange(values, 99);
    }

    public static int findFirst(int[] values, int target) {
        int low = 0;
        int high = values.length - 1;
        int answer = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (values[mid] == target) {
                answer = mid;
                high = mid - 1;
            } else if (values[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return answer;
    }

    public static int findLast(int[] values, int target) {
        int low = 0;
        int high = values.length - 1;
        int answer = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (values[mid] == target) {
                answer = mid;
                low = mid + 1;
            } else if (values[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return answer;
    }

    public static void printRange(int[] values, int target) {
        int first = findFirst(values, target);
        int last = findLast(values, target);
        int count = first == -1 ? 0 : last - first + 1;

        System.out.println(target + " 的索引範圍：[" + first + ", " + last +
            "], 出現次數：" + count);
    }
}
