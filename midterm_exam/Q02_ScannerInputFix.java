import java.util.Scanner;

public class Q02_ScannerInputFix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入數量：");
        int quantity = sc.nextInt();
        sc.nextLine();

        System.out.print("請輸入課程名稱：");
        String courseName = sc.nextLine();

        System.out.print("請輸入單價：");
        int price = sc.nextInt();
        sc.nextLine();

        System.out.print("請輸入備註：");
        String note = sc.nextLine();

        int total = quantity * price;

        System.out.println("=== 課程訂購結果 ===");
        System.out.println(courseName);
        System.out.println(quantity);
        System.out.println(total);
        System.out.println(note);

        sc.close();
    }
}
