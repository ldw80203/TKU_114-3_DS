import java.util.Scanner;

public class AtmMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int left = 1000;
        int option = -1;
        while(option != 0 ){
            System.out.println("===ATM===");
            System.out.println("1:查詢餘額");
            System.out.println("2:存款");
            System.out.println("3:提款");
            System.out.println("0:離開");
            System.out.println("請輸入選項:");
            option = sc.nextInt();
            switch(option){
                case 1:
                    System.out.println("餘額:"+left);
                    break;
                case 2:
                    System.out.println("請輸入存入金額:");
                    int deposite = 0;
                    deposite = sc.nextInt();
                    if (deposite > 0){
                        left += deposite;
                        System.out.println("餘額:"+left);
                        break;
                    }
                    else{
                        System.out.println("請輸入大於0的金額");
                        break;
                    }
                case 3:
                    System.out.println("請輸入提款金額:");
                    int withdraw = 0;
                    withdraw = sc.nextInt();
                    if (left >= withdraw && withdraw >0){
                        left -= withdraw;
                        System.out.println("餘額:"+left);
                        break;
                    }
                    else if (withdraw < 0) {
                        System.out.println("請輸入大於0的金額");
                        break;
                    }
                    else {
                        System.out.println("餘額不足");
                        break;
                    }
                case 0:
                    break;
                default:
                    System.out.println("請輸入1、2、3或0");
                    break;

            }
            

        }
    }
}
