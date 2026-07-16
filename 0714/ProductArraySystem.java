import java.util.Scanner;

public class ProductArraySystem {
    public static void printMenu() {
        System.out.println();
        System.out.println("=== 商品陣列管理系統 ===");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依商品編號查詢");
        System.out.println("3. 購買商品並扣除庫存");
        System.out.println("4. 補充商品庫存");
        System.out.println("5. 顯示低庫存商品");
        System.out.println("6. 顯示全部庫存總價值");
        System.out.println("0. 結束並顯示操作摘要");
    }

    public static int readMenuChoice(Scanner sc) {
        return readIntInRange(sc, "請選擇功能：", 0, 6);
    }

    public static int readIntInRange(Scanner sc, String prompt, int min, int max) {
        int value;

        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("輸入範圍必須是 " + min + " 到 " + max + "。");
            } else {
                System.out.println("請輸入整數。");
                sc.next();
            }
        }
    }

    public static int readPositiveInt(Scanner sc, String prompt) {
        int value;

        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value > 0) {
                    return value;
                }
                System.out.println("數量必須大於 0。");
            } else {
                System.out.println("請輸入整數。");
                sc.next();
            }
        }
    }

    public static int readProductIndex(Scanner sc, String[] names) {
        int productNumber = readIntInRange(sc, "請輸入商品編號（1～" + names.length + "）：", 1, names.length);
        return productNumber - 1;
    }

    public static void displayAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println();
        System.out.printf("%4s%16s%8s%8s%n", "編號", "商品名稱", "單價", "庫存");
        for (int i = 0; i < names.length; i++) {
            displayProduct(i, names, prices, stocks);
        }
    }

    public static void displayProduct(int index, String[] names, int[] prices, int[] stocks) {
        System.out.printf("%4d%16s%8d%8d%n", index + 1, names[index], prices[index], stocks[index]);
    }

    public static void queryProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        int index = readProductIndex(sc, names);
        System.out.println();
        System.out.println("查詢結果：");
        System.out.printf("%4s%16s%8s%8s%n", "編號", "商品名稱", "單價", "庫存");
        displayProduct(index, names, prices, stocks);
    }

    public static int buyProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        int index = readProductIndex(sc, names);
        int quantity = readPositiveInt(sc, "請輸入購買數量：");

        while (quantity > stocks[index]) {
            System.out.println("庫存不足，目前庫存為 " + stocks[index] + "。");
            quantity = readPositiveInt(sc, "請重新輸入購買數量：");
        }

        stocks[index] -= quantity;
        int subtotal = prices[index] * quantity;
        System.out.println("已購買 " + names[index] + " " + quantity + " 個，金額：" + subtotal);
        return subtotal;
    }

    public static int restockProduct(Scanner sc, String[] names, int[] stocks) {
        int index = readProductIndex(sc, names);
        int quantity = readPositiveInt(sc, "請輸入補貨數量：");

        stocks[index] += quantity;
        System.out.println("已補充 " + names[index] + " " + quantity + " 個，目前庫存：" + stocks[index]);
        return quantity;
    }

    public static void displayLowStockProducts(String[] names, int[] prices, int[] stocks) {
        boolean found = false;

        System.out.println();
        System.out.println("低庫存商品（庫存小於 10）：");
        System.out.printf("%4s%16s%8s%8s%n", "編號", "商品名稱", "單價", "庫存");
        for (int i = 0; i < stocks.length; i++) {
            if (stocks[i] < 10) {
                displayProduct(i, names, prices, stocks);
                found = true;
            }
        }

        if (!found) {
            System.out.println("目前沒有低庫存商品。");
        }
    }

    public static int calculateTotalStockValue(int[] prices, int[] stocks) {
        int totalValue = 0;
        for (int i = 0; i < prices.length; i++) {
            totalValue += prices[i] * stocks[i];
        }
        return totalValue;
    }

    public static void printSummary(int operationCount, int purchaseCount, int purchaseAmount, int restockCount) {
        System.out.println();
        System.out.println("=== 操作摘要 ===");
        System.out.println("操作次數：" + operationCount);
        System.out.println("購買次數：" + purchaseCount);
        System.out.println("購買總金額：" + purchaseAmount);
        System.out.println("補貨總數量：" + restockCount);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};

        int operationCount = 0;
        int purchaseCount = 0;
        int purchaseAmount = 0;
        int restockCount = 0;
        int choice;

        do {
            printMenu();
            choice = readMenuChoice(sc);

            if (choice != 0) {
                operationCount++;
            }

            if (choice == 1) {
                displayAllProducts(names, prices, stocks);
            } else if (choice == 2) {
                queryProduct(sc, names, prices, stocks);
            } else if (choice == 3) {
                purchaseAmount += buyProduct(sc, names, prices, stocks);
                purchaseCount++;
            } else if (choice == 4) {
                restockCount += restockProduct(sc, names, stocks);
            } else if (choice == 5) {
                displayLowStockProducts(names, prices, stocks);
            } else if (choice == 6) {
                System.out.println("全部庫存總價值：" + calculateTotalStockValue(prices, stocks));
            }
        } while (choice != 0);

        printSummary(operationCount, purchaseCount, purchaseAmount, restockCount);
        sc.close();
    }
}
