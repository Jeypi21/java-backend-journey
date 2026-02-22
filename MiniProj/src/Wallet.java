import java.util.Scanner;
import java.util.InputMismatchException;

public class Wallet {
    Scanner input = new Scanner(System.in);

    public void topUp(Player player){
        double amount;
        boolean doneTopUp = false;

        while (!doneTopUp) {
            System.out.println("\n--------------------");
            System.out.println("       TOP-UP");
            System.out.println("--------------------");
            System.out.print("Enter amount you wanted to top-up: ");
            
            try{
                amount = input.nextDouble();
                input.nextLine();
            }

            catch(InputMismatchException error){
                System.out.println("\nInvalid Input. Input numbers only"); 
                input.nextLine();
                continue;
            }

            if (amount <= 0){
                System.out.println("\nTop-up ammount cannot be less than P1.");
            } else {
                player.addAmount(amount);
                System.out.printf("Balance: %.2f\n\n", player.getBal());
                doneTopUp = true;
            } 
        }
    }

    public void withdraw(Player player){
        double amount;
        String withdrawAgain;
        boolean withdrawing = true;
        
        if (player.getBal() <= 0){
            System.out.println("\nNo balance to withdraw.\n");
        } else {
            while (withdrawing){
                System.out.println("\n--------------------");
                System.out.println("      WITHDRAW");
                System.out.println("--------------------");
                System.out.print("Enter amount you wanted to withdraw: ");
                
                try{
                    amount = input.nextDouble();
                    input.nextLine();
                }

                catch(InputMismatchException error){
                    System.out.println("\nInvalid Input. Input numbers only"); 
                    input.nextLine();
                    continue;
                }

                if (amount > player.getBal()){
                    System.out.println("\nInsufficient funds.\n");
                    System.out.print("Retry withdraw?(yes/no): ");
                    withdrawAgain = input.nextLine().toLowerCase();

                    if (withdrawAgain.equals("no")){
                        System.out.println("\nGoing back to menu.\n");
                        withdrawing = false;
                    }
                } else if (amount <= 0){
                    System.out.println("\nWithdraw ammount cannot be negative.");
                    System.out.print("Retry withdraw?(yes/no): ");
                    withdrawAgain = input.nextLine().toLowerCase();

                    if (withdrawAgain.equals("no")){
                        System.out.println("\nGoing back to menu.");
                        withdrawing = false;
                    }
                } else {
                    player.reduceAmount(amount);
                    System.out.printf("Balance: %.2f\n\n", player.getBal());
                    withdrawing = false;
                }
            }
        }   
    }
}

