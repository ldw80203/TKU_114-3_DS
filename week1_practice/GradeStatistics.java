import java.util.Scanner;

public class GradeStatistics {
    public static boolean isValidScore(int score){
        int a = -1;
        boolean isvalid = true;
        while(a != 0){
            if(score>0&&score<=100){
                isvalid = true;
                break;
            }
            else {
                isvalid = false;
                break;
            }
        }
        return isvalid;
    }
    public static boolean isPassing(int score){
        int a = -1;
        boolean isPassing = true;
        while(a != 0){
            if(score>=60){
                isPassing = true;
                break;
            }
            else {
                isPassing = false;
                break;
            }
        }
        return isPassing;
    }
    public static String getGrade(double average){
        if(average>=90){
            return "A";
        }
        else if(average>=80 && average<=89){
            return "B";
        }
        else if(average>=70 && average<=79){
            return "C";
        }
        else if(average>=60&&average<=69){
            return "D";
        }
        else{
            return "F";
        }
    }    
    public static void printSummary(int count, int total, double average, int max, int min, int passCount, int failCount, String grade){
        System.out.println("=== Grade Summary ===");
        System.out.println("Count: "+count);
        System.out.println("Total: "+total);
        System.out.println("Average: "+average);
        System.out.println("Max: "+max);
        System.out.println("Min: "+min);
        System.out.println("Pass count: "+passCount);
        System.out.println("Fail count: "+failCount);
        System.out.println("Average grade: "+grade);
    }
    public static int getScore(Scanner sc,String message){
        int a = -1;
        System.out.print(message);
        int score = sc.nextInt();
        int b = 0;
        while(a != 0){
            if (isValidScore(score)){
                b = score;
                break;
            }
            else if (score == -1){
                b = -1;
                break;
            }
            else {
                System.out.print("請輸入0到100: ");
                score = sc.nextInt();
            }
        }
        return b;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int count = 0;
        int total = 0;
        double average = 0;
        int max = 0;
        int min = 100;
        int passcount = 0;
        int failcount = 0;
        String grade = "";
        
        while (true) { //重複得成績
            int a = 0;
            a = getScore(sc, "請輸入成績（輸入 -1 結束）：");
            if(a == -1 && count == 0){
                System.out.println("No scores entered.");
                break;
            }
            else if(isValidScore(a)){
                if (a > max){
                    max = a;
                }
                if(a < min ){
                    min = a;
                }
                count += 1;
                total += a;
                if(isPassing(a)){
                    passcount += 1;
                }
                else{
                    failcount += 1;
                }
            }
            else if(a == -1){
                average = total/count;
                grade = getGrade(average);
                printSummary(count,total,average,max,min,passcount,failcount,grade);
                break;
            }
        }   
    }
}