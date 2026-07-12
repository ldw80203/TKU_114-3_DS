import java.util.Scanner;

public class PatternGenerator {
    public static void printMenu() {
        System.out.println("\n=== Pattern Menu ===");
        System.out.println("1. Multiplication table");
        System.out.println("2. Right triangle");
        System.out.println("3. Reverse triangle");
        System.out.println("4. Number grid");
        System.out.println("0. Exit");
    }

    public static int readPositiveInt(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer greater than 0.");
                sc.next();
                continue;
            }

            int value = sc.nextInt();
            if (value > 0) {
                return value;
            }
            System.out.println("Value must be greater than 0.");
        }
    }

    public static void printMultiplicationTable() {
        System.out.println("\n=== Multiplication Table ===");
        for (int row = 1; row <= 9; row++) {
            for (int column = 1; column <= 9; column++) {
                System.out.printf("%d x %d = %2d", row, column, row * column);
                if (column < 9) {
                    System.out.print("    ");
                }
            }
            System.out.println();
        }
    }

    public static void printTriangle(int height) {
        for (int row = 1; row <= height; row++) {
            for (int star = 1; star <= row; star++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printReverseTriangle(int height) {
        for (int row = height; row >= 1; row--) {
            for (int star = 1; star <= row; star++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printNumberGrid(int rows, int cols) {
        for (int row = 1; row <= rows; row++) {
            for (int column = 1; column <= cols; column++) {
                if (column > 1) {
                    System.out.print(" ");
                }
                System.out.print(column);
            }
            System.out.println();
        }
    }

    private static int readMenuOption(Scanner sc) {
        while (true) {
            System.out.print("Please enter an option: ");
            if (sc.hasNextInt()) {
                int option = sc.nextInt();
                if (option >= 0 && option <= 4) {
                    return option;
                }
            } else {
                sc.next();
            }
            System.out.println("Invalid option. Please choose 0 to 4.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            int option = readMenuOption(sc);

            switch (option) {
                case 1:
                    printMultiplicationTable();
                    break;
                case 2: {
                    int height = readPositiveInt(sc, "Please enter triangle height: ");
                    printTriangle(height);
                    break;
                }
                case 3: {
                    int height = readPositiveInt(sc, "Please enter reverse triangle height: ");
                    printReverseTriangle(height);
                    break;
                }
                case 4: {
                    int rows = readPositiveInt(sc, "Please enter number of rows: ");
                    int cols = readPositiveInt(sc, "Please enter number of columns: ");
                    printNumberGrid(rows, cols);
                    break;
                }
                case 0:
                    sc.close();
                    return;
                default:
                    break;
            }
        }
    }
}
