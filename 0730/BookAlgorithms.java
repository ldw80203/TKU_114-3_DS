import java.util.ArrayList;

public class BookAlgorithms {
    public static void mergeSortById(ArrayList<Book> books) {
        if (books == null || books.size() < 2) {
            return;
        }
        ArrayList<Book> temp = new ArrayList<Book>(books);
        mergeSortById(books, temp, 0, books.size() - 1);
    }

    public static void mergeSortByBorrowCountDesc(ArrayList<Book> books) {
        if (books == null || books.size() < 2) {
            return;
        }
        ArrayList<Book> temp = new ArrayList<Book>(books);
        mergeSortByBorrowCountDesc(books, temp, 0, books.size() - 1);
    }

    public static Book binarySearchById(ArrayList<Book> sortedBooks, String targetId) {
        if (sortedBooks == null || targetId == null) {
            return null;
        }
        int left = 0;
        int right = sortedBooks.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = sortedBooks.get(mid).getId().compareTo(targetId);
            if (comparison == 0) {
                return sortedBooks.get(mid);
            }
            if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static ArrayList<Book> sequentialSearchByCategory(
        ArrayList<Book> books,
        String category
    ) {
        ArrayList<Book> result = new ArrayList<Book>();
        if (books == null || category == null) {
            return result;
        }
        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                result.add(book);
            }
        }
        return result;
    }

    public static Book sequentialSearchById(ArrayList<Book> books, String id) {
        if (books == null || id == null) {
            return null;
        }
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    private static void mergeSortById(
        ArrayList<Book> books,
        ArrayList<Book> temp,
        int left,
        int right
    ) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSortById(books, temp, left, mid);
        mergeSortById(books, temp, mid + 1, right);
        mergeById(books, temp, left, mid, right);
    }

    private static void mergeSortByBorrowCountDesc(
        ArrayList<Book> books,
        ArrayList<Book> temp,
        int left,
        int right
    ) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSortByBorrowCountDesc(books, temp, left, mid);
        mergeSortByBorrowCountDesc(books, temp, mid + 1, right);
        mergeByBorrowCountDesc(books, temp, left, mid, right);
    }

    private static void mergeById(
        ArrayList<Book> books,
        ArrayList<Book> temp,
        int left,
        int mid,
        int right
    ) {
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (books.get(i).getId().compareTo(books.get(j).getId()) <= 0) {
                temp.set(k++, books.get(i++));
            } else {
                temp.set(k++, books.get(j++));
            }
        }
        copyRemaining(books, temp, i, mid, k);
        k += Math.max(0, mid - i + 1);
        copyRemaining(books, temp, j, right, k);
        copyBack(books, temp, left, right);
    }

    private static void mergeByBorrowCountDesc(
        ArrayList<Book> books,
        ArrayList<Book> temp,
        int left,
        int mid,
        int right
    ) {
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (books.get(i).getBorrowCount() >= books.get(j).getBorrowCount()) {
                temp.set(k++, books.get(i++));
            } else {
                temp.set(k++, books.get(j++));
            }
        }
        copyRemaining(books, temp, i, mid, k);
        k += Math.max(0, mid - i + 1);
        copyRemaining(books, temp, j, right, k);
        copyBack(books, temp, left, right);
    }

    private static void copyRemaining(
        ArrayList<Book> books,
        ArrayList<Book> temp,
        int from,
        int to,
        int target
    ) {
        for (int index = from; index <= to; index++) {
            temp.set(target++, books.get(index));
        }
    }

    private static void copyBack(
        ArrayList<Book> books,
        ArrayList<Book> temp,
        int left,
        int right
    ) {
        for (int index = left; index <= right; index++) {
            books.set(index, temp.get(index));
        }
    }
}
