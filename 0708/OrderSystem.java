import java.util.Scanner;

public class OrderSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1 = 30;
        int n2 = 35;
        int n3 = 50;
        int total = 0;
        int number = 0;
        int totalNum = 0;
        int option = -1;
        int subtotal = 0;
        String optionName = "";
        while(option != 0){
            System.out.println("===  Menu  ===");
            System.out.println("1:Black tea 30");
            System.out.println("2:Green tea 35");
            System.out.println("3:Coffee    50");
            System.out.println("0:Checkout    ");
            System.out.println("請輸入選項:");
            option = sc.nextInt();
            if(option > 0){
                System.out.println("請輸入數量:");
                number = sc.nextInt();}
            switch (option) {
                case 1:
                    total += n1*number;
                    totalNum += number;
                    subtotal += n1*number;
                    System.out.println("小計:"+subtotal);
                    break;
                case 2:
                    total += n2*number;
                    totalNum += number;
                    subtotal += n2*number;
                    System.out.println("小計:"+subtotal);
                    break;
                case 3:
                    total += n3*number;
                    totalNum += number;
                    subtotal += n3*number;
                    System.out.println("小計:"+subtotal);
                    break;
                case 0:
                    if(totalNum == 0 && total ==0){
                        System.out.println("你沒有點任何餐點");
                        break;
                    }
                    else{
                    System.out.println("總數量:"+totalNum);
                    System.out.println("總金額:"+total);
                    break;
                    }                
                default:
                    System.out.println("請輸入大於0的數字");
                    break;
            }
        }
    }
}
