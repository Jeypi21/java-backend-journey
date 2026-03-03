import java.util.Scanner;
import java.util.InputMismatchException;

public class SlotMachine {
    static Scanner input = new Scanner(System.in);
    static Slot slot = new Slot();

    public static void main(String[] args){
        UserManager auth = new UserManager();
        homePage(auth);

        input.close();
    }

    static void homePage(UserManager auth) {
        boolean exit = false;

        while (!exit){
            System.out.println("\n----------------");
            System.out.println("      HOME");
            System.out.println("----------------");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            int choose;

            try{
                choose = input.nextInt();
                input.nextLine();
            }

            catch(InputMismatchException error) {
                System.out.println("\nInvalid Input. Choose from the options only"); 
                input.nextLine();
                continue;
            }

            if (choose < 1 || choose > 3){
                System.out.println("\nInvalid Input. Choose from the options only"); 
                continue;
            }

            switch (choose){
                case 1: auth.login();
                    break;
                case 2: auth.register();
                    break;
                case 3: exit = true;
                    System.out.println("\nThank you. Come Again.\n");
                    break;
                default: System.out.println("\nInvalid Input. Choose from the options only");
                    break;
            }
            
            if (auth.isLoggedIn()){
                playOptions(auth);
            }
        }
    }

    static void playOptions(UserManager auth) {
        Player player = auth.getCurrentPlayer();
        Wallet wallet = new Wallet();
        boolean loggedOut = false;

        System.out.println("\n----------------------------------");
        System.out.println("   Welcome to Slot Machine Game   ");

        while (!loggedOut){
            System.out.println("----------------------------------");
            System.out.println("1. Play Slot Machine");
            System.out.println("2. Top-Up");
            System.out.println("3. Withdraw");
            System.out.println("4. Balance");
            System.out.println("5. Logout");
            System.out.print("Choose: ");

            int choose;

            try {
                choose = input.nextInt();
                input.nextLine();
            }
            catch(InputMismatchException error){
                System.out.println("\nInvalid Input. Choose from the options only\n"); 
                input.nextLine();
                continue;
            }
            
            if (choose < 1 || choose > 5){
                System.out.println("\nInvalid Input. Choose from the options only\n"); 
                continue;
            }

            switch (choose){
                case 1:
                    if (player.getBal() <= 0) System.out.println("\nNo balance. Top-up to play the game.\n");
                    else playSlotMachine(slot, player);
                    break;
                case 2:
                    wallet.topUp(player, auth);
                    break;
                case 3:
                    wallet.withdraw(player, auth);
                    break;
                case 4:
                    System.out.printf("\nBalance: %.2f\n\n", player.getBal());  
                    break;
                case 5:
                    System.out.println("\nLogged out.");
                    auth.loggedOut();
                    loggedOut = true;
                    break;
    
                default: System.out.println("\nInvalid Input. Choose from the options only\n");
                    break;
            }
        }
    }

    public static void playSlotMachine(Slot slot, Player player) {
        String playAgain;

        System.err.println("\n-------------Rules-------------");
        System.out.println("1. Bet at your own risk.");
        System.out.println("2. Enjoy the game.");
        System.err.println("------------Goodluck-----------");
        System.out.println("\nSymbols: @  #  $  %  &  =");
        System.err.println("""
        Winning Symbol: 
            | 3@ -> x15 | 3# -> x13 | 3$ -> x10 | 3% -> x8 | 3& -> x6 | 3= -> x4 |
            | 2@ -> x7  | 2# -> x6  | 2$ -> x5  | 2% -> x4 | 2& -> x3 | 2= -> x2 |
        """);

        String[] slots;
        double bet = 0;
        double earnings = 0;

        while(true){
            if (player.getBal() <= 0){
                System.out.println("You do not have balance. Top-up to play the game again.\n");
                break;
            }

            do {
                System.out.printf("\nBalance: %.2f\n\n", player.getBal());
                System.out.print("Enter your bet: ");
                bet = input.nextDouble();
                input.nextLine();

                if (bet > player.getBal()){
                    System.out.println("\nInsufficient balance.\n");
                } else if (bet <= 0){
                    System.out.println("\nBet cannot be less than P1.\n");
                }
            } while (bet > player.getBal() || bet <= 0);

            player.reduceAmount(bet); //minus the bet to the current balance

            System.out.println("\nSpinning...\n");
            slots = slot.roll();
            slot.print(slots); //print the slots

            earnings = slot.result(slots, bet);

            if (earnings != 0){
                player.addAmount(earnings);
                System.out.printf("\n===YOU WON! P%.2f===\n", earnings);
            }
            
            System.out.printf("\nBalance: %.2f\n\n", player.getBal());
            
            if (player.getBal() > 0){
                System.out.print("Play again?(yes/no): ");
                playAgain = input.nextLine().toLowerCase();

                if (playAgain.equals("no")){
                    System.out.println("\nThank you for playing. Play again for much bigger wins!\n");
                    break;
                } 
            }   
        }
    }
}