import java.util.Scanner;

public class day1BankProg {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        double bal = 0;
        int choice;
        boolean isRunning = true;
        
        while (isRunning){
            System.out.println("\nBANK MENU PROGRAM");
            System.out.println("1. Show Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Menu choice: ");
            choice = scanner.nextInt();

            switch(choice){
                case 1:
                    showBal(bal);
                    break;
                case 2: 
                    bal += deposit();
                    System.out.printf("\nYour balance is P%.2f\n", bal);
                    break;
                case 3:
                    bal -= withdraw(bal);
                    System.out.printf("\nYour balance is P%.2f\n", bal);
                    break;
                case 4: 
                    System.out.println("\nExiting...");
                    isRunning = false;
                        break;
                default: 
                    System.out.println("\n--Invalid Input! Choose from 1-4 only--\n");
                        break;
            }
        }

        scanner.close();
    }   

    static void showBal(double bal){
        System.out.printf("\nYour balance is P%.2f\n", bal);
    }

    static double deposit(){
        double amount;
        System.out.print("Enter the amount you will deposit: ");
        amount = scanner.nextDouble();

        if (amount < 0){
            System.out.println("\nAmount cannot be negative");
            return 0;
        } else {
            System.out.printf("\nYou deposited P%.2f", amount);
            return amount;
        }
    }

    static double withdraw(double bal) {
        double amount;

        System.out.print("Enter the amount you will withdraw: ");
        amount = scanner.nextDouble();

        if (amount > bal){
            System.out.println("\nInsufficient Balance");
            return 0;
        } else if (amount < 0){
            System.out.println("\nAmount cannot be negative");
            return 0;
        }else {
            System.out.printf("\nYou withdrew P%.2f", amount);
            return amount;
        }
    }
}