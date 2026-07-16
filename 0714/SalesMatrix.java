import java.util.Scanner;

public class SalesMatrix {
    public static void inputSales(Scanner sc, int[][] sales) {
        for (int product = 0; product < sales.length; product++) {
            for (int day = 0; day < sales[product].length; day++) {
                sales[product][day] = readNonNegativeInt(sc,
                        "商品 " + (product + 1) + " 第 " + (day + 1) + " 天銷售量：");
            }
        }
    }

    public static int readNonNegativeInt(Scanner sc, String prompt) {
        int value;

        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value >= 0) {
                    return value;
                }
                System.out.println("銷售量不得小於 0。");
            } else {
                System.out.println("請輸入整數。");
                sc.next();
            }
        }
    }

    public static void printSalesTable(int[][] sales) {
        System.out.println();
        System.out.println("銷售矩陣報表");
        System.out.printf("%8s", "商品");
        for (int day = 0; day < sales[0].length; day++) {
            System.out.printf("%8s", "第" + (day + 1) + "天");
        }
        System.out.println();

        for (int product = 0; product < sales.length; product++) {
            System.out.printf("%8s", "商品" + (product + 1));
            for (int day = 0; day < sales[product].length; day++) {
                System.out.printf("%8d", sales[product][day]);
            }
            System.out.println();
        }
    }

    public static int calculateProductTotal(int[][] sales, int productIndex) {
        int productTotal = 0;
        for (int day = 0; day < sales[productIndex].length; day++) {
            productTotal += sales[productIndex][day];
        }
        return productTotal;
    }

    public static int calculateDayTotal(int[][] sales, int dayIndex) {
        int dayTotal = 0;
        for (int product = 0; product < sales.length; product++) {
            dayTotal += sales[product][dayIndex];
        }
        return dayTotal;
    }

    public static void printProductTotals(int[][] sales) {
        System.out.println();
        for (int product = 0; product < sales.length; product++) {
            System.out.println("商品 " + (product + 1) + " 總銷售量：" + calculateProductTotal(sales, product));
        }
    }

    public static void printDayTotals(int[][] sales) {
        System.out.println();
        for (int day = 0; day < sales[0].length; day++) {
            System.out.println("第 " + (day + 1) + " 天總銷售量：" + calculateDayTotal(sales, day));
        }
    }

    public static int findBestProductIndex(int[][] sales) {
        int bestIndex = 0;
        int bestTotal = calculateProductTotal(sales, 0);

        for (int product = 1; product < sales.length; product++) {
            int productTotal = calculateProductTotal(sales, product);
            if (productTotal > bestTotal) {
                bestTotal = productTotal;
                bestIndex = product;
            }
        }
        return bestIndex;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] sales = new int[3][4];

        inputSales(sc, sales);
        printSalesTable(sales);
        printProductTotals(sales);
        printDayTotals(sales);

        int bestProductIndex = findBestProductIndex(sales);
        System.out.println();
        System.out.println("總銷售量最高的商品：商品 " + (bestProductIndex + 1)
                + "，總銷售量：" + calculateProductTotal(sales, bestProductIndex));

        sc.close();
    }
}
