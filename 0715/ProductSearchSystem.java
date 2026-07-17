import java.util.Locale;
import java.util.Scanner;

public class ProductSearchSystem {
    private static final String[] NAMES = {
        "Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"
    };
    private static final int[] PRICES = {890, 490, 5200, 250, 1290};
    private static final int[] STOCKS = {12, 20, 5, 30, 8};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            int option = readInt(sc, "請選擇功能：");

            switch (option) {
                case 1 -> printAllProducts();
                case 2 -> searchExact(sc);
                case 3 -> searchPartial(sc);
                case 4 -> printLongestProduct();
                case 5 -> printFirstKeywordPositions(sc);
                case 0 -> {
                    System.out.println("系統結束。");
                    running = false;
                }
                default -> System.out.println("沒有這個選項，請重新選擇。");
            }
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("\n=== 商品名稱搜尋系統 ===");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 完整名稱搜尋");
        System.out.println("3. 部分名稱搜尋");
        System.out.println("4. 顯示名稱最長的商品");
        System.out.println("5. 顯示名稱與關鍵字第一次出現的位置");
        System.out.println("0. 結束");
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

    public static void printAllProducts() {
        for (int i = 0; i < NAMES.length; i++) {
            System.out.printf("%s，價格：%d，庫存：%d%n",
                    NAMES[i], PRICES[i], STOCKS[i]);
        }
    }

    public static int findExact(String keyword) {
        String target = keyword.trim();
        for (int i = 0; i < NAMES.length; i++) {
            if (NAMES[i].equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }

    public static void searchExact(Scanner sc) {
        System.out.print("請輸入完整商品名稱：");
        int index = findExact(sc.nextLine());
        if (index == -1) {
            System.out.println("找不到商品。");
        } else {
            System.out.printf("找到：%s，價格：%d，庫存：%d%n",
                    NAMES[index], PRICES[index], STOCKS[index]);
        }
    }

    public static void searchPartial(Scanner sc) {
        System.out.print("請輸入部分名稱：");
        String keyword = sc.nextLine().trim().toLowerCase(Locale.ROOT);
        if (keyword.isEmpty()) {
            System.out.println("搜尋文字不能是空白。");
            return;
        }

        boolean found = false;
        for (int i = 0; i < NAMES.length; i++) {
            if (NAMES[i].toLowerCase(Locale.ROOT).contains(keyword)) {
                System.out.printf("%s，價格：%d，庫存：%d%n",
                        NAMES[i], PRICES[i], STOCKS[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("找不到相符商品。");
        }
    }

    public static void printLongestProduct() {
        int longestIndex = 0;
        for (int i = 1; i < NAMES.length; i++) {
            if (NAMES[i].length() > NAMES[longestIndex].length()) {
                longestIndex = i;
            }
        }
        System.out.println("名稱最長的商品：" + NAMES[longestIndex]);
    }

    public static void printFirstKeywordPositions(Scanner sc) {
        System.out.print("請輸入搜尋關鍵字：");
        String keyword = sc.nextLine().trim().toLowerCase(Locale.ROOT);
        if (keyword.isEmpty()) {
            System.out.println("搜尋文字不能是空白。");
            return;
        }

        for (String name : NAMES) {
            int position = name.toLowerCase(Locale.ROOT).indexOf(keyword);
            System.out.printf("%s：第一次出現位置 %d%n", name, position);
        }
    }

    /*
     * 測試案例：
     * 1. 選 1：應列出五項商品與價格、庫存。
     * 2. 選 2，輸入「 mouse 」：應找到 Mouse。
     * 3. 選 2，輸入「MOUSE」：應忽略大小寫並找到 Mouse。
     * 4. 選 3，輸入「o」：應列出多筆包含 o 的商品。
     * 5. 選 3，輸入空白：應提示搜尋文字不能是空白。
     * 6. 選 2，輸入「Camera」：應顯示找不到商品。
     * 7. 選 5，輸入「o」：應顯示各商品第一次出現的位置。
     * 8. 選 9：應提示沒有這個選項，不讓程式中止。
     */
}
