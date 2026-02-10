import java.util.Scanner;

public class SlotMachine {
    static Scanner input = new Scanner(System.in);
    static Player player = new Player();

    public static void main(String[] args){
        int homeChoice;
        boolean isLoggedIn = false;
        boolean exit = false;
        
        while(!isLoggedIn && !exit){
            do {
                System.out.println("------HOME------");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");
                System.out.print("Choose: ");
                homeChoice = input.nextInt();
                input.nextLine();

                switch (homeChoice) {
                    case 1:
                        isLoggedIn = player.login();
                        break;
                    case 2:
                        player.register();
                        break;
                    case 3:
                        System.out.println("Thank you. Come again.");
                        exit = true;
                        break;
                    default: System.out.println("\nInvalid Input. Choose from Menu Options only.");
                        break;
                }
            } while (homeChoice < 1 || homeChoice > 3);
        }

        if (isLoggedIn) {
            gameOptions(isLoggedIn);
        }

        input.close();
    }

    static void gameOptions(boolean isLoggedIn){
        Wallet wallet = new Wallet();
        Slot slot = new Slot();

        int menuChoice;

        while (isLoggedIn){
            do {
                System.out.println("-------SLOT MACHINE GAME MENU-------");
                System.out.println("1. Play");
                System.out.println("2. Top-up");
                System.out.println("3. Withdraw");
                System.out.println("4. Check Balance");
                System.out.println("5. Logout");
                System.out.print("Menu Choice: ");
                menuChoice = input.nextInt();
                input.nextLine();

                switch(menuChoice){
                case 1: 
                    if(player.getBal() <= 0){
                        System.out.println("\nINSUFFICIENT BALANCE. TOP-UP FIRST TO ABLE TO PLAY");
                    } else {
                        playGame(player, slot);
                    }
                    break;
                case 2: 
                    wallet.topUp(player);
                    System.out.println("\nBalance: " + player.getBal());
                    break;
                case 3: 
                    wallet.withdraw(player);
                    System.out.println("\nBalance: " + player.getBal());
                    break;
                case 4: 
                    System.out.println("\nBalance: " + player.getBal());
                    break;
                case 5: 
                    System.out.println("\nThank you for playing the game.");
                    isLoggedIn = false;
                    break;
                default: 
                    System.out.println("\nInvalid Input. Choose from Menu Options only.");
                    break;
                }
            } while (menuChoice < 1 || menuChoice > 5);
        }
    }

    static void playGame(Player player, Slot slot){
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

                if (bet > player.getBal()){
                    System.out.println("INSUFFICIENT BALANCE");
                } else if(player.getBal() == 0){
                    System.out.println("YOU CURRENTLY HAVE 0 BALANCE");
                } else if(bet <= 0){
                    System.out.println("YOU CANNOT BET LESS THAN OR EQUAL TO ZERO");
                }
            } while (bet <= 0 || bet > player.getBal());

            player.withdraw(bet);
                
            System.out.println("\nSpinning...");
            slots = slot.roll();
            slot.print(slots);

            payout = slot.result(slots, bet);
            player.addBal(payout);
            
            if (payout > 0){
                System.out.println("You won P" + payout);
            }

            System.out.println("\nBalance: P" + player.getBal());

            if (player.getBal() <= 0){
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
    }
}
//magday