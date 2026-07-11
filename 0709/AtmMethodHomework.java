import java.util.Scanner;

public class AtmMethodHomework {
    public static void printMenu(){
        System.out.println("===ATM===");
        System.out.println("1:查詢餘額");
        System.out.println("2:存款");
        System.out.println("3:提款");
        System.out.println("0:離開");
        
    }
    public static int readPositiveAmount(Scanner sc, String message){
        int amount = 0;
        while (amount <= 0 ){
            System.out.println(message);
            amount = sc.nextInt();
            if(amount <= 0){
                System.out.println("請輸入大於0的金額");
            }
        }
        return amount;

    }
    public static int deposit(int balance, int amount){
        if (amount > 0){
            balance += amount;
        }
        else{
            return balance;
        }
        return balance;
    }
    public static void printBalance(int balance){
        System.out.println("餘額:" + balance);
    }
    public static int withdraw(int balance,int amount){
        if (amount > 0 && balance >= amount){
            balance -= amount;
        }
        else if(balance < amount){
            return balance;
        }
        return balance;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int balance = 1000;
        int option = -1;
        
        while (option != 0){
            printMenu();
            option = sc.nextInt();
            switch(option){
            case 1:
                printBalance(balance);
                break;
            case 2:
                balance = deposit(balance,readPositiveAmount(sc, "請輸入存入金額:"));

                printBalance(balance);
                break;
            case 3:    
                int n1 = readPositiveAmount(sc, "請輸入取款金額");
                if (balance < n1){
                    System.out.println("餘額不足");
                    break;
                }
                else{
                    balance = withdraw(balance, n1);
                    printBalance(balance);
                    break;
                }
            case 0:
                break;
            default:
                    System.out.println("請輸入1、2、3或0");
                    break;
           } 
        }
        sc.close();
    }
}
