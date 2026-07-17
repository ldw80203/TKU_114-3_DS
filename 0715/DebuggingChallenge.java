import java.util.Scanner;

/*
 * 原始故障程式的錯誤紀錄：
 * 1. 錯誤位置：System.out.println 那一行最後少分號。
 *    錯誤類型：編譯錯誤。修正：補上分號。
 * 2. 錯誤位置：for (int i = 0; i <= scores.length; i++)。
 *    錯誤類型：陣列越界執行錯誤。修正：改成 i < scores.length。
 * 3. 錯誤位置：if (command == "exit")。
 *    錯誤類型：字串比較邏輯錯誤。修正：使用 "exit".equals(command)。
 * 4. 錯誤位置：double average = total / scores.length。
 *    錯誤類型：整數除法邏輯錯誤。修正：先把 total 轉成 double。
 * 5. 錯誤位置：nextInt() 後直接呼叫 nextLine()。
 *    錯誤類型：Scanner 輸入錯誤。修正：先用 nextLine() 讀走換行。
 *
 * VS Code Debugger 文字紀錄：
 * - 在 total += scores[i] 設 breakpoint。
 * - i 依序是 0、1、2，total 依序變成 80、155、247。
 * - 修正後迴圈不會執行 i = 3，因此不會發生陣列越界。
 * - average 應為 82.333...，輸出到小數第二位是 82.33。
 */
public class DebuggingChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] scores = {80, 75, 92};
        int total = 0;

        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }

        double average = (double) total / scores.length;
        System.out.printf("平均：%.2f%n", average);

        System.out.print("請輸入年齡：");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("請輸入指令：");
        String command = sc.nextLine().trim();

        if ("exit".equalsIgnoreCase(command)) {
            System.out.println("系統結束，年齡：" + age);
        } else {
            System.out.println("收到指令：" + command + "，年齡：" + age);
        }

        sc.close();
    }
}
