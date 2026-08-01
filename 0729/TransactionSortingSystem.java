import java.util.Arrays;

public class TransactionSortingSystem {
    public static void main(String[] args) {
        Transaction[] transactions = {
            new Transaction("T001", "A100", 5000, 3),
            new Transaction("T002", "A200", 7200, 1),
            new Transaction("T003", "A100", 5000, 2),
            new Transaction("T004", "A300", 9900, 4),
            new Transaction("T005", "A200", 7200, 5)
        };

        System.out.println("Before:");
        printTransactions(transactions);

        insertionSort(transactions);

        System.out.println();
        System.out.println("After amount descending, timeOrder ascending:");
        printTransactions(transactions);
        System.out.println("Raw array=" + Arrays.toString(transactions));
    }

    public static void insertionSort(Transaction[] transactions) {
        for (int index = 1; index < transactions.length; index++) {
            Transaction key = transactions[index];
            int position = index - 1;

            while (position >= 0 && shouldComeAfter(transactions[position], key)) {
                transactions[position + 1] = transactions[position];
                position--;
            }
            transactions[position + 1] = key;
        }
    }

    private static boolean shouldComeAfter(Transaction current, Transaction key) {
        if (current.getAmount() != key.getAmount()) {
            return current.getAmount() < key.getAmount();
        }
        return current.getTimeOrder() > key.getTimeOrder();
    }

    private static void printTransactions(Transaction[] transactions) {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}
