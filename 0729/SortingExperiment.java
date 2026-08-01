import java.util.Arrays;

public class SortingExperiment {
    public static void main(String[] args) {
        int[][] testCases = {
            {1, 2, 3, 4, 5, 6},
            {6, 5, 4, 3, 2, 1},
            {4, 1, 6, 2, 5, 3}
        };
        String[] names = {"sorted", "reversed", "random"};

        for (int index = 0; index < testCases.length; index++) {
            int[] selectionData = testCases[index].clone();
            int[] insertionData = testCases[index].clone();

            Counts selectionCounts = selectionSort(selectionData);
            Counts insertionCounts = insertionSort(insertionData);

            System.out.println();
            System.out.println("Case: " + names[index]
                + " input=" + Arrays.toString(testCases[index]));
            System.out.println("Selection result=" + Arrays.toString(selectionData)
                + " " + selectionCounts);
            System.out.println("Insertion result=" + Arrays.toString(insertionData)
                + " " + insertionCounts);
            printObservation(names[index], selectionCounts, insertionCounts);
        }
    }

    public static Counts selectionSort(int[] values) {
        Counts counts = new Counts();
        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;
            for (int index = start + 1; index < values.length; index++) {
                counts.comparisons++;
                if (values[index] < values[minIndex]) {
                    minIndex = index;
                }
            }
            if (minIndex != start) {
                int temp = values[start];
                values[start] = values[minIndex];
                values[minIndex] = temp;
                counts.swaps++;
            }
        }
        return counts;
    }

    public static Counts insertionSort(int[] values) {
        Counts counts = new Counts();
        for (int index = 1; index < values.length; index++) {
            int key = values[index];
            int position = index - 1;
            while (position >= 0) {
                counts.comparisons++;
                if (values[position] <= key) {
                    break;
                }
                values[position + 1] = values[position];
                counts.moves++;
                position--;
            }
            values[position + 1] = key;
        }
        return counts;
    }

    private static void printObservation(
        String caseName,
        Counts selection,
        Counts insertion
    ) {
        if ("sorted".equals(caseName)) {
            System.out.println("Observation: insertion sort stops early on sorted data.");
        } else if ("reversed".equals(caseName)) {
            System.out.println("Observation: insertion sort has many moves on reversed data.");
        } else {
            System.out.println("Observation: selection sort comparisons stay fixed.");
        }
    }

    static class Counts {
        int comparisons;
        int swaps;
        int moves;

        @Override
        public String toString() {
            return "comparisons=" + comparisons
                + " swaps=" + swaps
                + " moves=" + moves;
        }
    }
}
