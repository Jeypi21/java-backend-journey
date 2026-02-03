import java.util.Scanner;

public class MoneyOptions {
    Scanner input = new Scanner(System.in);

    double topUp(){
        double amount;

        do{
            System.out.print("\nEnter amount you want to top-up: ");
            amount = input.nextDouble();
            input.nextLine();

            if (amount <= 0){
                System.out.println("You cannot top-up less than P0");
            }
        } while (amount <= 0);

        return amount;
    }

    double withdraw(double bal){
        double amount;
        
        if (bal == 0){
            System.out.println("\nNO BALANCE. UNABLE TO WITHDRAW");
            return 0;
        } else {
            while(true){
                System.out.print("\nEnter amount you want to withdraw: ");
                amount = input.nextDouble();
                input.nextLine();

                if (amount <= 0){
                    System.out.println("You cannot withdraw less than P0");
                } else if (amount > bal){
                    System.out.println("You cannot withdraw more than your balance");
                } else {
                    return amount;
                }

                if(!retryWithdraw()){
                    System.out.println("Withdrawal cancelled.");
                    return 0;
                }
            }
        }
    }

    boolean retryWithdraw(){
        String again;
        boolean running = true;

        while(running){
            System.out.print("\nDo you still want to withdraw(yes/no)? ");
            again = input.nextLine().toLowerCase();

            if(again.equals("yes")){
                running = false;
                return true;
            } else if(again.equals("no")){
                running = false;
                return false;
            } else{
                System.out.println("Invalid Input. Yes or No only");
            }
        }

        return true;
    }
}


