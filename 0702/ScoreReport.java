import java.util.Scanner;//匯入Scanner類別，讓程式可以讀取使用者輸入

public class ScoreReport {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);//建立Scanner物件，命名為sc 

        System.out.print("請輸入姓名: ");
        String name = sc.nextLine();//讀取使用者的輸入姓名，並用字串定義

        System.out.print("請輸入 Java 成績: ");
        int javaScore = sc.nextInt();

        System.out.print("請輸入 English 成績: ");
        int englishScore = sc.nextInt();

        System.out.print("請輸入 Math 成績: ");
        int mathScore = sc.nextInt();

        double average = (javaScore + englishScore + mathScore) / 3.0;//用雙精度浮點數定義平均數，因為除法會有小數點
        
        System.out.println("\n===成績報表===");
        System.out.println("姓名: " + name);
        System.out.println("Java : " + javaScore);
        System.out.println("English : " + englishScore);
        System.out.println("Math : " + mathScore);
        System.out.println("平均: " + average);
    }
}
