import java.util.Scanner;

public class ProductManagementSystem {
    public static final int CAPACITY = 10;

    public static void main(String[] args) {
        Product[] products = new Product[CAPACITY];
        int count = loadInitialProducts(products);
        int operationCount = 0;
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            int option = readInt(sc, "請選擇功能：");

            if (option == 0) {
                printSummary(products, count, operationCount);
                break;
            }

            operationCount++;
            switch (option) {
                case 1 -> displayProducts(products, count);
                case 2 -> searchAndPrint(sc, products, count);
                case 3 -> count = addProduct(sc, products, count);
                case 4 -> sellProduct(sc, products, count);
                case 5 -> restockProduct(sc, products, count);
                case 6 -> changePrice(sc, products, count);
                case 7 -> displayLowStock(products, count);
                case 8 -> System.out.println("全部庫存總價值："
                        + calculateTotalValue(products, count));
                default -> System.out.println("沒有這個選項，請重新選擇。");
            }
        }

        sc.close();
    }

    public static int loadInitialProducts(Product[] products) {
        products[0] = new Product("Keyboard", 890, 12);
        products[1] = new Product("Mouse", 490, 20);
        products[2] = new Product("Monitor", 5200, 5);
        products[3] = new Product("USB Cable", 250, 30);
        products[4] = new Product("Headset", 1290, 8);
        return 5;
    }

    public static void printMenu() {
        System.out.println("\n=== 物件導向商品管理系統 ===");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依完整名稱搜尋");
        System.out.println("3. 新增商品");
        System.out.println("4. 出售商品");
        System.out.println("5. 補充庫存");
        System.out.println("6. 修改商品價格");
        System.out.println("7. 顯示低庫存商品");
        System.out.println("8. 顯示全部庫存總價值");
        System.out.println("0. 結束並顯示操作摘要");
    }

    public static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("請輸入整數。");
            }
        }
    }

    public static String readNonBlank(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("內容不能是空白，請重新輸入。");
        }
    }

    public static Product findProduct(Product[] products, int count, String name) {
        if (products == null || name == null || name.trim().isEmpty()) {
            return null;
        }

        for (int i = 0; i < count; i++) {
            if (products[i] != null
                    && products[i].getName().equalsIgnoreCase(name.trim())) {
                return products[i];
            }
        }
        return null;
    }

    public static void displayProducts(Product[] products, int count) {
        if (count == 0) {
            System.out.println("目前沒有商品。");
            return;
        }

        System.out.println("\n商品清單：");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + products[i]);
        }
    }

    public static void searchAndPrint(Scanner sc, Product[] products, int count) {
        String name = readNonBlank(sc, "請輸入完整商品名稱：");
        Product product = findProduct(products, count, name);
        if (product == null) {
            System.out.println("找不到商品。");
        } else {
            System.out.println("搜尋結果：" + product);
        }
    }

    public static int addProduct(Scanner sc, Product[] products, int count) {
        if (count >= products.length) {
            System.out.println("商品陣列已滿，無法新增。");
            return count;
        }

        String name = readNonBlank(sc, "請輸入商品名稱：");
        if (findProduct(products, count, name) != null) {
            System.out.println("不可新增重複名稱。");
            return count;
        }

        int price = readInt(sc, "請輸入價格：");
        int stock = readInt(sc, "請輸入庫存：");
        if (price <= 0 || stock < 0) {
            System.out.println("新增失敗：價格必須大於 0，庫存不能小於 0。");
            return count;
        }

        products[count] = new Product(name, price, stock);
        System.out.println("新增成功：" + products[count]);
        return count + 1;
    }

    public static void sellProduct(Scanner sc, Product[] products, int count) {
        String name = readNonBlank(sc, "請輸入要出售的商品：");
        Product product = findProduct(products, count, name);
        if (product == null) {
            System.out.println("找不到商品。");
            return;
        }

        int quantity = readInt(sc, "請輸入出售數量：");
        if (product.sell(quantity)) {
            System.out.println("出售成功：" + product);
        } else {
            System.out.println("出售失敗：數量必須大於 0，且不能超過庫存。");
        }
    }

    public static void restockProduct(Scanner sc, Product[] products, int count) {
        String name = readNonBlank(sc, "請輸入要補貨的商品：");
        Product product = findProduct(products, count, name);
        if (product == null) {
            System.out.println("找不到商品。");
            return;
        }

        int quantity = readInt(sc, "請輸入補貨數量：");
        if (product.restock(quantity)) {
            System.out.println("補貨成功：" + product);
        } else {
            System.out.println("補貨失敗：數量必須大於 0。");
        }
    }

    public static void changePrice(Scanner sc, Product[] products, int count) {
        String name = readNonBlank(sc, "請輸入要修改價格的商品：");
        Product product = findProduct(products, count, name);
        if (product == null) {
            System.out.println("找不到商品。");
            return;
        }

        int price = readInt(sc, "請輸入新價格：");
        if (product.setPrice(price)) {
            System.out.println("價格修改成功：" + product);
        } else {
            System.out.println("修改失敗：價格必須大於 0。");
        }
    }

    public static void displayLowStock(Product[] products, int count) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (products[i].isLowStock()) {
                System.out.println(products[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("目前沒有低庫存商品。");
        }
    }

    public static long calculateTotalValue(Product[] products, int count) {
        long total = 0;
        for (int i = 0; i < count; i++) {
            total += products[i].getInventoryValue();
        }
        return total;
    }

    public static void printSummary(Product[] products, int count,
            int operationCount) {
        System.out.println("\n=== 操作摘要 ===");
        System.out.println("本次操作次數：" + operationCount);
        System.out.println("目前商品數量：" + count + " / " + products.length);
        System.out.println("目前庫存總價值：" + calculateTotalValue(products, count));
        System.out.println("系統已結束。");
    }

    /*
     * 測試案例：
     * 1. 選 1：應顯示初始的五項商品。
     * 2. 選 2，輸入「 mouse 」：應忽略前後空白並找到 Mouse。
     * 3. 選 2，輸入「MOUSE」：應忽略大小寫並找到 Mouse。
     * 4. 選 2，輸入「Camera」：應顯示找不到商品，資料不變。
     * 5. 選 3，輸入 Webcam、1800、6：應新增成功，數量變成六項。
     * 6. 選 3，輸入 Mouse：應拒絕重複名稱，原本 Mouse 不變。
     * 7. 選 4，輸入 Keyboard、2：應出售成功，庫存從 12 變成 10。
     * 8. 選 4，輸入 Monitor、6：應失敗，庫存不能變成負數。
     * 9. 選 5，輸入 Headset、5：應補貨成功，庫存從 8 變成 13。
     * 10. 選 6，輸入 Mouse、0：應拒絕價格，原價格維持 490。
     * 11. 選 7：應列出目前庫存低於 10 的商品。
     * 12. 選 8：應顯示全部庫存總價值，使用 long 計算。
     * 13. 連續新增五項商品後再選 3：應顯示陣列已滿，不能新增。
     * 14. 選 9：應顯示沒有這個選項，程式繼續執行。
     * 15. 選 0：應顯示操作次數、商品數量與庫存總價值。
     */
}
