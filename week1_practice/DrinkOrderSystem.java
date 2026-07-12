import java.util.Scanner;

public class DrinkOrderSystem {
    public static void printMenu() {
        System.out.println("=== Drink Menu ===");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Milk tea   $45");
        System.out.println("4. Coffee     $50");
        System.out.println("0. Checkout");
    }

    public static int getPrice(int option) {
        switch (option) {
            case 1:
                return 30;
            case 2:
                return 35;
            case 3:
                return 45;
            case 4:
                return 50;
            default:
                return 0;
        }
    }

    public static String getItemName(int option) {
        switch (option) {
            case 1:
                return "Black tea";
            case 2:
                return "Green tea";
            case 3:
                return "Milk tea";
            case 4:
                return "Coffee";
            default:
                return "Unknown item";
        }
    }

    public static int readValidQuantity(Scanner sc) {
        while (true) {
            System.out.print("Please enter quantity: ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid quantity. Please enter an integer greater than 0.");
                sc.next();
                continue;
            }

            int quantity = sc.nextInt();
            if (quantity > 0) {
                return quantity;
            }
            System.out.println("Quantity must be greater than 0.");
        }
    }

    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    public static int calculateDiscountedTotal(int totalAmount) {
        if (totalAmount >= 300) {
            return totalAmount * 9 / 10;
        }
        return totalAmount;
    }

    public static void printReceipt(int blackTeaCount, int greenTeaCount,
            int milkTeaCount, int coffeeCount, int totalItems,
            int totalAmount, int finalAmount) {
        System.out.println("\n=== Receipt ===");
        System.out.println("Black tea: " + blackTeaCount);
        System.out.println("Green tea: " + greenTeaCount);
        System.out.println("Milk tea: " + milkTeaCount);
        System.out.println("Coffee: " + coffeeCount);
        System.out.println("Total items: " + totalItems);
        System.out.println("Original amount: " + totalAmount);
        System.out.println("Discount: " + (totalAmount >= 300 ? "Yes" : "No"));
        System.out.println("Final amount: " + finalAmount);
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
        int blackTeaCount = 0;
        int greenTeaCount = 0;
        int milkTeaCount = 0;
        int coffeeCount = 0;
        int totalItems = 0;
        int totalAmount = 0;

        while (true) {
            printMenu();
            int option = readMenuOption(sc);
            if (option == 0) {
                break;
            }

            int quantity = readValidQuantity(sc);
            int subtotal = calculateSubtotal(getPrice(option), quantity);
            totalItems += quantity;
            totalAmount += subtotal;

            switch (option) {
                case 1:
                    blackTeaCount += quantity;
                    break;
                case 2:
                    greenTeaCount += quantity;
                    break;
                case 3:
                    milkTeaCount += quantity;
                    break;
                case 4:
                    coffeeCount += quantity;
                    break;
                default:
                    break;
            }

            System.out.println(getItemName(option) + " x " + quantity);
            System.out.println("Subtotal: " + subtotal);
        }

        int finalAmount = calculateDiscountedTotal(totalAmount);
        printReceipt(blackTeaCount, greenTeaCount, milkTeaCount, coffeeCount,
                totalItems, totalAmount, finalAmount);
        sc.close();
    }
}
