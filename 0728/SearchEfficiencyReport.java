public class SearchEfficiencyReport {
    public static void main(String[] args) {
        int[] sizes = {16, 128, 1024};

        for (int size : sizes) {
            int[] values = createSortedData(size);
            System.out.println("\n資料筆數：" + size);
            printComparison(values, values[0], "第一筆");
            printComparison(values, values[values.length - 1], "最後一筆");
            printComparison(values, -1, "不存在資料");
        }

        System.out.println("\n觀察：Sequential Search 最差會逐筆檢查，資料越多比較次數越高。");
        System.out.println("Binary Search 每次排除一半範圍，在已排序資料上比較次數成長較慢。");
    }

    public static int[] createSortedData(int size) {
        int[] values = new int[size];
        for (int index = 0; index < values.length; index++) {
            values[index] = index + 1;
        }
        return values;
    }

    public static int sequentialChecks(int[] values, int target) {
        int checks = 0;
        for (int value : values) {
            checks++;
            if (value == target) {
                return checks;
            }
        }
        return checks;
    }

    public static int binaryChecks(int[] values, int target) {
        int low = 0;
        int high = values.length - 1;
        int checks = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            checks++;
            if (values[mid] == target) {
                return checks;
            } else if (values[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return checks;
    }

    private static void printComparison(int[] values, int target, String label) {
        System.out.println(label + "（目標 " + target + "）：Sequential=" +
            sequentialChecks(values, target) + " 次，Binary=" +
            binaryChecks(values, target) + " 次");
    }
}
