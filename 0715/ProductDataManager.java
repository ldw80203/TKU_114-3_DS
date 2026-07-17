import java.util.Locale;
import java.util.Scanner;

public class ProductDataManager {
    private static final int CAPACITY = 20;
    private static final int LOW_STOCK_LIMIT = 10;

    public static void main(String[] args) {
        String[] records = {
            "Keyboard,890,12",
            "Mouse,490,20",
            "Monitor,5200,5",
            "USB Cable,250,30",
            "Headset,1290,8"
        };

        String[] names = new String[CAPACITY];
        int[] prices = new int[CAPACITY];
        int[] stocks = new int[CAPACITY];
        int size = loadRecords(records, names, prices, stocks);

        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu();
            int option = readInt(sc, "請選擇功能：");

            switch (option) {
                case 1 -> printProducts(names, prices, stocks, size);
                case 2 -> searchExact(sc, names, prices, stocks, size);
                case 3 -> searchPartial(sc, names, prices, stocks, size);
                case 4 -> printLowStock(names, prices, stocks, size);
                case 5 -> printTotalValue(prices, stocks, size);
                case 6 -> {
                    size = addRecord(sc, names, prices, stocks, size);
                }
                case 0 -> {
                    System.out.println("資料管理器結束。");
                    running = false;
                }
                default -> System.out.println("沒有這個選項，請重新選擇。");
            }
        }
        sc.close();
    }

    public static void printMenu() {
        System.out.println("\n=== 商品文字資料管理器 ===");
        System.out.println("1. 顯示解析後的商品表格");
        System.out.println("2. 完整名稱搜尋");
        System.out.println("3. 部分名稱搜尋");
        System.out.println("4. 顯示低庫存商品（庫存低於 10）");
        System.out.println("5. 顯示庫存總價值");
        System.out.println("6. 新增一筆文字資料");
        System.out.println("0. 結束");
    }

    public static int loadRecords(String[] records, String[] names,
            int[] prices, int[] stocks) {
        int size = 0;
        for (String record : records) {
            ParsedRecord parsed = parseRecord(record);
            if (parsed.valid) {
                names[size] = parsed.name;
                prices[size] = parsed.price;
                stocks[size] = parsed.stock;
                size++;
            } else {
                System.out.println("忽略錯誤資料：" + record + "，原因：" + parsed.message);
            }
        }
        return size;
    }

    public static ParsedRecord parseRecord(String record) {
        if (record == null) {
            return ParsedRecord.invalid("資料不能是 null");
        }

        String[] parts = record.split(",", -1);
        if (parts.length != 3) {
            return ParsedRecord.invalid("必須有商品名稱、價格、庫存三個欄位");
        }

        String name = parts[0].trim();
        if (name.isEmpty()) {
            return ParsedRecord.invalid("商品名稱不能是空白");
        }

        try {
            int price = Integer.parseInt(parts[1].trim());
            int stock = Integer.parseInt(parts[2].trim());
            if (price < 0 || stock < 0) {
                return ParsedRecord.invalid("價格與庫存不能是負數");
            }
            return ParsedRecord.valid(name, price, stock);
        } catch (NumberFormatException e) {
            return ParsedRecord.invalid("價格與庫存必須是整數");
        }
    }

    public static void printProducts(String[] names, int[] prices,
            int[] stocks, int size) {
        System.out.printf("%-15s %8s %8s%n", "商品名稱", "價格", "庫存");
        System.out.println("--------------------------------");
        for (int i = 0; i < size; i++) {
            System.out.printf("%-15s %8d %8d%n", names[i], prices[i], stocks[i]);
        }
    }

    public static void searchExact(Scanner sc, String[] names, int[] prices,
            int[] stocks, int size) {
        System.out.print("請輸入完整商品名稱：");
        String keyword = sc.nextLine().trim();
        for (int i = 0; i < size; i++) {
            if (names[i].equalsIgnoreCase(keyword)) {
                System.out.printf("找到：%s，價格：%d，庫存：%d%n",
                        names[i], prices[i], stocks[i]);
                return;
            }
        }
        System.out.println("找不到商品。");
    }

    public static void searchPartial(Scanner sc, String[] names, int[] prices,
            int[] stocks, int size) {
        System.out.print("請輸入部分名稱：");
        String keyword = sc.nextLine().trim().toLowerCase(Locale.ROOT);
        if (keyword.isEmpty()) {
            System.out.println("搜尋文字不能是空白。");
            return;
        }

        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (names[i].toLowerCase(Locale.ROOT).contains(keyword)) {
                System.out.printf("找到：%s，價格：%d，庫存：%d%n",
                        names[i], prices[i], stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("找不到相符商品。");
        }
    }

    public static void printLowStock(String[] names, int[] prices,
            int[] stocks, int size) {
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (stocks[i] < LOW_STOCK_LIMIT) {
                System.out.printf("%s，價格：%d，庫存：%d%n",
                        names[i], prices[i], stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("目前沒有低庫存商品。");
        }
    }

    public static void printTotalValue(int[] prices, int[] stocks, int size) {
        long total = 0;
        for (int i = 0; i < size; i++) {
            total += (long) prices[i] * stocks[i];
        }
        System.out.println("庫存總價值：" + total);
    }

    public static int addRecord(Scanner sc, String[] names, int[] prices,
            int[] stocks, int size) {
        if (size == names.length) {
            System.out.println("資料已達容量上限，無法新增。");
            return size;
        }

        System.out.print("請輸入新資料（名稱,價格,庫存）：");
        String record = sc.nextLine();
        ParsedRecord parsed = parseRecord(record);
        if (!parsed.valid) {
            System.out.println("新增失敗：" + parsed.message);
            return size;
        }

        names[size] = parsed.name;
        prices[size] = parsed.price;
        stocks[size] = parsed.stock;
        System.out.println("新增成功：" + parsed.name);
        return size + 1;
    }

    public static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("請輸入整數選項。");
            }
        }
    }

    /*
     * 測試案例：
     * 1. 啟動後選 1：五筆資料應正確拆成名稱、價格、庫存。
     * 2. 選 2，輸入「 keyboard 」：應找到 Keyboard。
     * 3. 選 3，輸入「USB」：應找到 USB Cable。
     * 4. 選 3，輸入「camera」：應顯示找不到相符商品。
     * 5. 選 4：應列出 Monitor 與 Headset 兩項低庫存商品。
     * 6. 選 5：應顯示庫存總價值 64300。
     * 7. 選 6，輸入「Webcam,1800,6」：應新增成功。
     * 8. 選 6，輸入「Mouse,abc,10」：應顯示價格與庫存必須是整數，程式不中止。
     * 9. 選 6，輸入「Mouse,490」：應顯示欄位數量錯誤，程式不中止。
     * 10. 選 6，輸入「Mouse,-1,5」：應拒絕負價格。
     */

    public static class ParsedRecord {
        private final boolean valid;
        private final String name;
        private final int price;
        private final int stock;
        private final String message;

        private ParsedRecord(boolean valid, String name, int price,
                int stock, String message) {
            this.valid = valid;
            this.name = name;
            this.price = price;
            this.stock = stock;
            this.message = message;
        }

        public static ParsedRecord valid(String name, int price, int stock) {
            return new ParsedRecord(true, name, price, stock, "");
        }

        public static ParsedRecord invalid(String message) {
            return new ParsedRecord(false, "", 0, 0, message);
        }
    }
}
