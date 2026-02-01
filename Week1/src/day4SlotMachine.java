import java.util.Scanner;
import java.util.Random;

public class day4SlotMachine {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        int menuChoice;
        boolean isRunning = true;
        double bal = 0;

        while (isRunning){
            do {
                System.out.println("\n-------SLOT MACHINE GAME MENU-------");
                System.out.println("1. Play");
                System.out.println("2. Top-up");
                System.out.println("3. Withdraw");
                System.out.println("4. Check Balance");
                System.out.println("5. Exit");
                System.out.print("Menu Choice: ");
                menuChoice = input.nextInt();
                input.nextLine();

                switch(menuChoice){
                case 1: if(bal <= 0){
                            System.out.println("\nINSUFFICIENT BALANCE. TOP-UP FIRST TO ABLE TO PLAY");
                        } else {
                            bal = playGame(bal);
                        }
                        break;
                case 2: bal += topUp();
                        System.out.println("\nBalance: " + bal);
                        break;
                case 3: bal -= withdraw(bal);
                        if (bal > 0){
                            System.out.println("\nBalance: " + bal);
                        }
                        break;
                case 4: System.out.println("\nBalance: " + bal);
                        break;
                case 5: System.out.println("Thank you for playing the game.");
                        isRunning = false;
                        break;
                default: System.out.println("Invalid Input. Choose from Menu Options only.");
                }
            } while (menuChoice < 1 || menuChoice > 5);
        }
        input.close();
    }

    static double playGame(double bal){
        String[] slot;
        double bet;
        double payout;
        String playAgain;
        boolean playing = true;

        System.out.println("\n-----WELCOME TO SLOT MACHINE GAME-----");
        System.out.println("Winning Symbols:");
        System.out.println("# = x3");
        System.out.println("@ = x4");
        System.out.println("$ = x5");
        System.out.println("& = x10");
        System.out.println("% = x15");

        while(playing){
            do{
                System.out.print("\nEnter your bet: ");
                bet = input.nextDouble();
                input.nextLine();

                if (bet > bal){
                    System.out.println("INSUFFICIENT BALANCE");
                } else if(bal == 0){
                    System.out.println("YOU CURRENTLY HAVE 0 BALANCE");
                }
            } while (bet > bal);

            bal -= bet;
                
            System.out.println("\nSpinning...");
            slot = slotRoll();
            printSlot(slot);

            payout = determineOutcome(slot, bet);
            bal += payout;
            
            if (payout > 0){
                System.out.println("You won P" + payout);
            }

            System.out.println("\nBalance: P" + bal);

            if (bal == 0){
                System.out.println("\nINSUFFICIENT BALANCE, TOP-UP TO BE ABLE TO PLAY AGAIN");
                playing = false;
            } else {
                do {
                    System.out.print("\nDo you still want to play the game(yes/no): ");
                    playAgain = input.nextLine().toLowerCase();

                    if (playAgain.equals("yes")){
                        continue;
                    } else if (playAgain.equals("no")){
                        playing = false;
                    } else {
                        System.out.println("Invalid Input. Yes or No only.");
                    }
                } while (!playAgain.equals("yes") && !playAgain.equals("no"));
            } 
                
        } 

        return bal;
    }

    static double topUp(){
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

    static double withdraw(double bal){
        double amount;

        if (bal == 0){
            System.out.println("\nNO BALANCE. UNABLE TO WITHDRAW");
            return 0;
        } else {
            do{
                System.out.print("\nEnter amount you want to withdraw: ");
                amount = input.nextDouble();
                input.nextLine();

                if (amount <= 0){
                    System.out.println("You cannot withdraw less than P0");
                    return 0;
                } else if (amount > bal){
                    System.out.println("You cannot withdraw more than your balance");
                    return 0;
                }
            } while (amount <= 0 || amount > bal);
        }

        return amount;
    }

    static String[] slotRoll(){
        Random random = new Random();

        String[] slot = new String[3];
        String[] slotValues = {"#", "@", "$", "&", "%"};
        
        for (int i = 0; i < 3; i++){
            slot[i] = slotValues[random.nextInt(slotValues.length)];
        }

        return slot;
    }

    static void printSlot(String[] slot){
        System.out.println("+---------+");
        System.out.println(" " + String.join(" | ", slot));
        System.out.println("+---------+");
    }

    static double determineOutcome(String[] slot, double bet){
        if (slot[0].equals(slot[1]) && slot[1].equals(slot[2])){
            return switch(slot[0]){
                case "#" -> bet * 3;
                case "@" -> bet * 4;
                case "$" -> bet * 5;
                case "&" -> bet * 10;
                case "%" -> bet * 15;
                default -> 0;
            };
        } else if (slot[0].equals(slot[1])){
            return switch(slot[0]){
                case "#" -> bet * 1.5;
                case "@" -> bet * 2;
                case "$" -> bet * 2.5;
                case "&" -> bet * 5;
                case "%" -> bet * 8;
                default -> 0;
            };
        } else if (slot[1].equals(slot[2])){
            return switch(slot[1]){
                case "#" -> bet * 1.5;
                case "@" -> bet * 2;
                case "$" -> bet * 2.5;
                case "&" -> bet * 5;
                case "%" -> bet * 8;
                default -> 0;
            };
        } else if (slot[0].equals(slot[2])){
            return switch(slot[0]){
                case "#" -> bet * 1.5;
                case "@" -> bet * 2;
                case "$" -> bet * 2.5;
                case "&" -> bet * 5;
                case "%" -> bet * 8;
                default -> 0;
            };
        } else {
            System.out.println("You lose this round");
            System.out.println("BETTER LUCK NEXT TIME BOSS!");
        }

        return 0;
    }
}
