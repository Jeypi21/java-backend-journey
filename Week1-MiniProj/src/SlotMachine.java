import java.util.Scanner;

public class SlotMachine {
    static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args){
        MoneyOptions money = new MoneyOptions();
        Slot slot = new Slot();

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
                            bal = playGame(bal, slot);
                        }
                        break;
                case 2: bal += money.topUp();
                        System.out.println("\nBalance: " + bal);
                        break;
                case 3: bal -= money.withdraw(bal);
                        System.out.println("\nBalance: " + bal);
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

    static double playGame(double bal, Slot slot){
        String[] slots;
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
                } else if(bal <= 0){
                    System.out.println("YOU CURRENTLY HAVE 0 BALANCE");
                }
            } while (bet > bal);

            bal -= bet;
                
            System.out.println("\nSpinning...");
            slots = slot.roll();
            slot.print(slots);

            payout = slot.result(slots, bet);
            bal += payout;
            
            if (payout > 0){
                System.out.println("You won P" + payout);
            }

            System.out.println("\nBalance: P" + bal);

            if (bal <= 0){
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
}
//magday