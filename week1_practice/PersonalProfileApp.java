import java.util.Scanner;

public class PersonalProfileApp {
    public static int readPositiveInt(Scanner sc,String message){
        int a = -1;
        System.out.print(message);
        int b = 0;
        int c = 0;
        while(a != 0){
            b = sc.nextInt();
            if(b>0){
                c = b;
                break;
            }
            else{
                System.out.print("請輸入大於0的整數: ");
            }
        }
       return c;
    }
    public static double readPositiveDouble(Scanner sc, String message){
        int a = -1;
        System.out.print(message);
        double b = 0.0;
        double c = 0.0;
        while(a != 0){
            b = sc.nextDouble();
            if(b>0){
                c = b;
                break;
            }
            else{
                System.out.print("請輸入大於0的浮點數: ");
            }
        }
       return c;
    }
    
    public static double calculateBmi(double height, double weight){
        double bmi = weight/(height*height);
        return bmi;
    }
    public static String getBmiLevel(double bmi){
        if (bmi <18.5){
            return "Underweight";
        }
        else if(18.5<=bmi&&bmi<24){
            return "Normal";
        }
        else if(24<=bmi&&bmi<27){
            return "Overweight";
        }
        else{
            return "Obese";
        }
    }
    public static boolean isAdult(int age){
        if (age>=18){
            return true;
        }
        else {
            return false;
        }
    }
    public static void printReport(String name, int age, boolean adult, double height, double weight, double bmi, String level){
        System.out.println("=== Personal Health Report ===");
        System.out.println("Name:"+name);
        System.out.println("Age:"+age);
        System.out.println("Adult:"+adult);
        System.out.println("Height:"+height);
        System.out.println("Weight:"+weight);
        System.out.println("bmi:"+bmi);
        System.out.println("Level:"+level);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入姓名: ");
        String name = sc.nextLine();
        int age = readPositiveInt(sc,"請輸入年齡: ");
        double height = readPositiveDouble(sc,"請輸入身高（公尺）： ");
        double weight = readPositiveDouble(sc,"請輸入體重（公斤）： ");
        double bmi = calculateBmi(height, weight);
        boolean adult = isAdult(age);
        printReport(name, age, adult, height, weight, bmi, getBmiLevel(bmi));
    }
}
    
