import java.util.Scanner;

public class ArrayStatistics {
    public static int readCount(Scanner sc) {
        return readIntInRange(sc, "請輸入資料筆數（1～50）：", 1, 50);
    }

    public static void inputScores(Scanner sc, int[] scores) {
        for (int i = 0; i < scores.length; i++) {
            scores[i] = readIntInRange(sc, "第 " + (i + 1) + " 筆成績（0～100）：", 0, 100);
        }
    }

    public static int calculateTotal(int[] scores) {
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return total;
    }

    public static int findMax(int[] scores) {
        int max = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        return max;
    }

    public static int findMin(int[] scores) {
        int min = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) {
                min = scores[i];
            }
        }
        return min;
    }

    public static int countPass(int[] scores) {
        int passCount = 0;
        for (int score : scores) {
            if (score >= 60) {
                passCount++;
            }
        }
        return passCount;
    }

    public static int findIndex(int[] scores, int target) {
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void printScores(int[] scores) {
        System.out.print("全部成績：");
        for (int score : scores) {
            System.out.print(score + " ");
        }
        System.out.println();
    }

    public static int readTargetScore(Scanner sc) {
        return readIntInRange(sc, "請輸入要搜尋的目標成績（0～100）：", 0, 100);
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = readCount(sc);
        int[] scores = new int[count];
        inputScores(sc, scores);

        printScores(scores);

        int total = calculateTotal(scores);
        double average = (double) total / scores.length;
        int passCount = countPass(scores);
        int failCount = scores.length - passCount;

        System.out.println("總分：" + total);
        System.out.printf("平均：%.2f%n", average);
        System.out.println("最高分：" + findMax(scores));
        System.out.println("最低分：" + findMin(scores));
        System.out.println("及格人數：" + passCount);
        System.out.println("不及格人數：" + failCount);

        int target = readTargetScore(sc);
        int index = findIndex(scores, target);
        if (index >= 0) {
            System.out.println(target + " 第一次出現在索引 " + index);
        } else {
            System.out.println("找不到成績 " + target);
        }

        sc.close();
    }
}
