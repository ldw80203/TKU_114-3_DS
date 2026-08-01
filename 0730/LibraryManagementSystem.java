import java.util.ArrayList;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<Book>();

        System.out.println("Empty binary search: "
            + BookAlgorithms.binarySearchById(books, "B001"));

        addBook(books, new Book("B003", "Java Basics", "Programming", 15));
        addBook(books, new Book("B001", "Data Structures", "Programming", 28));
        addBook(books, new Book("B004", "World History", "History", 8));
        addBook(books, new Book("B002", "Algorithms", "Programming", 36));
        addBook(books, new Book("B002", "Duplicate Algorithms", "Programming", 20));

        System.out.println();
        System.out.println("Sort by id ascending:");
        BookAlgorithms.mergeSortById(books);
        printBooks(books);

        System.out.println();
        System.out.println("Binary search B003: "
            + BookAlgorithms.binarySearchById(books, "B003"));
        System.out.println("Binary search B999: "
            + BookAlgorithms.binarySearchById(books, "B999"));

        System.out.println();
        System.out.println("Sequential search category Programming:");
        printBooks(BookAlgorithms.sequentialSearchByCategory(books, "Programming"));

        System.out.println();
        System.out.println("Sort by borrow count descending:");
        BookAlgorithms.mergeSortByBorrowCountDesc(books);
        printBooks(books);
    }

    private static void addBook(ArrayList<Book> books, Book book) {
        if (BookAlgorithms.sequentialSearchById(books, book.getId()) != null) {
            System.out.println("Reject duplicate id: " + book.getId());
            return;
        }
        books.add(book);
    }

    private static void printBooks(ArrayList<Book> books) {
        if (books.isEmpty()) {
            System.out.println("(empty)");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
