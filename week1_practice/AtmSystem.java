import java.util.Scanner;

public class AtmSystem {
    public static void printMenu() {
        System.out.println("\n=== ATM Menu ===");
        System.out.println("1. Check balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Show summary");
        System.out.println("0. Exit");
    }

    public static int readPositiveAmount(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            if (!sc.hasNextInt()) {
                System.out.println("Invalid amount. Please enter an integer greater than 0.");
                sc.next();
                continue;
            }

            int amount = sc.nextInt();
            if (amount > 0) {
                return amount;
            }
            System.out.println("Amount must be greater than 0.");
        }
    }

    public static int deposit(int balance, int amount) {
        return balance + amount;
    }

    public static int withdraw(int balance, int amount) {
        return balance - amount;
    }

    public static boolean canWithdraw(int balance, int amount) {
        return amount > 0 && amount <= balance;
    }

    public static void printBalance(int balance) {
        System.out.println("Balance: " + balance);
    }

    public static void printSummary(int balance, int depositCount,
            int withdrawCount, int totalDeposit, int totalWithdraw) {
        System.out.println("\n=== ATM Summary ===");
        System.out.println("Final balance: " + balance);
        System.out.println("Deposit count: " + depositCount);
        System.out.println("Withdraw count: " + withdrawCount);
        System.out.println("Total deposit: " + totalDeposit);
        System.out.println("Total withdraw: " + totalWithdraw);
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
        int balance = 1000;
        int depositCount = 0;
        int withdrawCount = 0;
        int totalDeposit = 0;
        int totalWithdraw = 0;

        while (true) {
            printMenu();
            int option = readMenuOption(sc);

            switch (option) {
                case 1:
                    printBalance(balance);
                    break;
                case 2: {
                    int amount = readPositiveAmount(sc, "Please enter deposit amount: ");
                    balance = deposit(balance, amount);
                    depositCount++;
                    totalDeposit += amount;
                    printBalance(balance);
                    break;
                }
                case 3: {
                    int amount = readPositiveAmount(sc, "Please enter withdrawal amount: ");
                    if (canWithdraw(balance, amount)) {
                        balance = withdraw(balance, amount);
                        withdrawCount++;
                        totalWithdraw += amount;
                        printBalance(balance);
                    } else {
                        System.out.println("Withdrawal failed: amount exceeds current balance.");
                    }
                    break;
                }
                case 4:
                    printSummary(balance, depositCount, withdrawCount,
                            totalDeposit, totalWithdraw);
                    break;
                case 0:
                    printSummary(balance, depositCount, withdrawCount,
                            totalDeposit, totalWithdraw);
                    sc.close();
                    return;
                default:
                    break;
            }
        }
    }
}
