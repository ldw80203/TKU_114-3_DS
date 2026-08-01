public class AlgorithmComparisonReport {
    public static void main(String[] args) {
        int[] sizes = {16, 128, 1024};
        String[] caseNames = {"sorted", "reversed", "fixed-random"};

        System.out.printf(
            "%-12s %8s %12s %12s %12s%n",
            "case",
            "size",
            "selection",
            "insertion",
            "merge"
        );

        for (int size : sizes) {
            int[][] datasets = {
                createSorted(size),
                createReversed(size),
                createFixedRandom(size)
            };
            for (int index = 0; index < datasets.length; index++) {
                int[] selectionData = datasets[index].clone();
                int[] insertionData = datasets[index].clone();
                int[] mergeData = datasets[index].clone();

                long selection = selectionSortComparisons(selectionData);
                long insertion = insertionSortComparisons(insertionData);
                long merge = mergeSortComparisons(mergeData);

                System.out.printf(
                    "%-12s %8d %12d %12d %12d%n",
                    caseNames[index],
                    size,
                    selection,
                    insertion,
                    merge
                );
            }
        }

        System.out.println();
        System.out.println("Observation: selection sort comparisons are fixed for the same n.");
        System.out.println("Observation: insertion sort is best on already sorted data.");
        System.out.println("Observation: merge sort grows close to n log n and is stable.");
        System.out.println("Observation: compare counts are better evidence than one run time.");
    }

    public static long selectionSortComparisons(int[] values) {
        long comparisons = 0;
        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;
            for (int index = start + 1; index < values.length; index++) {
                comparisons++;
                if (values[index] < values[minIndex]) {
                    minIndex = index;
                }
            }
            int temp = values[start];
            values[start] = values[minIndex];
            values[minIndex] = temp;
        }
        return comparisons;
    }

    public static long insertionSortComparisons(int[] values) {
        long comparisons = 0;
        for (int index = 1; index < values.length; index++) {
            int key = values[index];
            int position = index - 1;
            while (position >= 0) {
                comparisons++;
                if (values[position] <= key) {
                    break;
                }
                values[position + 1] = values[position];
                position--;
            }
            values[position + 1] = key;
        }
        return comparisons;
    }

    public static long mergeSortComparisons(int[] values) {
        if (values.length < 2) {
            return 0;
        }
        int[] temp = new int[values.length];
        return mergeSort(values, temp, 0, values.length - 1);
    }

    private static long mergeSort(int[] values, int[] temp, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        long comparisons = 0;
        comparisons += mergeSort(values, temp, left, mid);
        comparisons += mergeSort(values, temp, mid + 1, right);
        comparisons += merge(values, temp, left, mid, right);
        return comparisons;
    }

    private static long merge(int[] values, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        long comparisons = 0;

        while (i <= mid && j <= right) {
            comparisons++;
            if (values[i] <= values[j]) {
                temp[k++] = values[i++];
            } else {
                temp[k++] = values[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = values[i++];
        }
        while (j <= right) {
            temp[k++] = values[j++];
        }
        for (int index = left; index <= right; index++) {
            values[index] = temp[index];
        }
        return comparisons;
    }

    private static int[] createSorted(int size) {
        int[] values = new int[size];
        for (int index = 0; index < size; index++) {
            values[index] = index + 1;
        }
        return values;
    }

    private static int[] createReversed(int size) {
        int[] values = new int[size];
        for (int index = 0; index < size; index++) {
            values[index] = size - index;
        }
        return values;
    }

    private static int[] createFixedRandom(int size) {
        int[] values = createSorted(size);
        int seed = 97;
        for (int index = size - 1; index > 0; index--) {
            seed = (seed * 1103515245 + 12345) & 0x7fffffff;
            int swapIndex = seed % (index + 1);
            int temp = values[index];
            values[index] = values[swapIndex];
            values[swapIndex] = temp;
        }
        return values;
    }
}
