import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<CartItem> cart = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = readInt("Choose: ");

            if (choice == 1) {
                addItem();
            } else if (choice == 2) {
                updateQuantity();
            } else if (choice == 3) {
                removeItem();
            } else if (choice == 4) {
                printCart();
            } else if (choice == 5) {
                printTotal();
            } else if (choice != 0) {
                System.out.println("Invalid option.");
            }
        } while (choice != 0);

        System.out.println("Goodbye.");
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("=== Shopping Cart System ===");
        System.out.println("1. Add item");
        System.out.println("2. Update quantity");
        System.out.println("3. Remove item");
        System.out.println("4. List cart");
        System.out.println("5. Show total");
        System.out.println("0. Exit");
    }

    public static CartItem findByCode(String code) {
        for (CartItem item : cart) {
            if (item.getCode().equalsIgnoreCase(code)) {
                return item;
            }
        }
        return null;
    }

    public static void addItem() {
        String code = readRequiredText("Code: ");
        int quantity = readPositiveInt("Quantity: ");
        CartItem found = findByCode(code);

        if (found != null) {
            found.addQuantity(quantity);
            System.out.println("Same code found. Quantity increased.");
            return;
        }

        String name = readRequiredText("Name: ");
        double price = readNonNegativeDouble("Unit price: ");
        cart.add(new CartItem(code, name, price, quantity));
        System.out.println("Item added.");
    }

    public static void updateQuantity() {
        String code = readRequiredText("Code to update: ");
        CartItem found = findByCode(code);

        if (found == null) {
            System.out.println("Item not found.");
            return;
        }

        int quantity = readInt("New quantity: ");
        if (found.setQuantity(quantity)) {
            System.out.println("Quantity updated.");
        } else {
            System.out.println("Quantity must be greater than 0.");
        }
    }

    public static void removeItem() {
        String code = readRequiredText("Code to remove: ");
        CartItem found = findByCode(code);

        if (found == null) {
            System.out.println("Item not found.");
            return;
        }

        cart.remove(found);
        System.out.println("Item removed.");
    }

    public static void printCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        for (CartItem item : cart) {
            System.out.println(item);
        }
    }

    public static double calculateTotal() {
        double total = 0;
        for (CartItem item : cart) {
            total += item.getSubtotal();
        }
        return total;
    }

    public static void printTotal() {
        System.out.println("Total: " + calculateTotal());
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static String readRequiredText(String prompt) {
        String value;
        do {
            value = readText(prompt);
            if (value.isEmpty()) {
                System.out.println("This field cannot be blank.");
            }
        } while (value.isEmpty());
        return value;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private static int readPositiveInt(String prompt) {
        int value;
        do {
            value = readInt(prompt);
            if (value <= 0) {
                System.out.println("Please enter a number greater than 0.");
            }
        } while (value <= 0);
        return value;
    }

    private static double readNonNegativeDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value >= 0) {
                    return value;
                }
                System.out.println("Please enter 0 or a positive number.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }
}
