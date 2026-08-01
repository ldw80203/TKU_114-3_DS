import java.util.Arrays;

public class SortingDebugReport {
    public static void main(String[] args) {
        testInnerRangeError();
        testKeyNotSavedError();
        testWrongDirectionError();
    }

    private static void testInnerRangeError() {
        int[] broken = {1, 3, 2};
        int[] fixed = broken.clone();

        brokenSelectionInnerRange(broken);
        fixedSelectionSort(fixed);

        System.out.println();
        System.out.println("Error 1: inner loop range error");
        System.out.println("Broken result=" + Arrays.toString(broken));
        System.out.println("Fixed result =" + Arrays.toString(fixed));
    }

    private static void testKeyNotSavedError() {
        int[] broken = {30, 10, 20};
        int[] fixed = broken.clone();

        brokenInsertionWithoutSavedKey(broken);
        fixedInsertionSort(fixed);

        System.out.println();
        System.out.println("Error 2: key not saved before shifting");
        System.out.println("Broken result=" + Arrays.toString(broken));
        System.out.println("Fixed result =" + Arrays.toString(fixed));
    }

    private static void testWrongDirectionError() {
        int[] broken = {4, 1, 3, 2};
        int[] fixed = broken.clone();

        brokenInsertionWrongDirection(broken);
        fixedInsertionSort(fixed);

        System.out.println();
        System.out.println("Error 3: comparison direction error");
        System.out.println("Broken result=" + Arrays.toString(broken));
        System.out.println("Fixed result =" + Arrays.toString(fixed));
    }

    public static void brokenSelectionInnerRange(int[] values) {
        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;
            // Error: the inner loop starts at 0, so the sorted area is processed again.
            for (int index = 0; index < values.length; index++) {
                if (values[index] < values[minIndex]) {
                    minIndex = index;
                }
            }
            int temp = values[start];
            values[start] = values[minIndex];
            values[minIndex] = temp;
        }
    }

    public static void brokenInsertionWithoutSavedKey(int[] values) {
        for (int index = 1; index < values.length; index++) {
            int position = index - 1;
            // Error: values[index] is used as key after shifting may overwrite it.
            while (position >= 0 && values[position] > values[index]) {
                values[position + 1] = values[position];
                position--;
            }
            values[position + 1] = values[index];
        }
    }

    public static void brokenInsertionWrongDirection(int[] values) {
        for (int index = 1; index < values.length; index++) {
            int key = values[index];
            int position = index - 1;
            // Error: this sorts descending, but the expected result is ascending.
            while (position >= 0 && values[position] < key) {
                values[position + 1] = values[position];
                position--;
            }
            values[position + 1] = key;
        }
    }

    public static void fixedSelectionSort(int[] values) {
        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;
            for (int index = start + 1; index < values.length; index++) {
                if (values[index] < values[minIndex]) {
                    minIndex = index;
                }
            }
            int temp = values[start];
            values[start] = values[minIndex];
            values[minIndex] = temp;
        }
    }

    public static void fixedInsertionSort(int[] values) {
        for (int index = 1; index < values.length; index++) {
            int key = values[index];
            int position = index - 1;
            while (position >= 0 && values[position] > key) {
                values[position + 1] = values[position];
                position--;
            }
            values[position + 1] = key;
        }
    }
}
