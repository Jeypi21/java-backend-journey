import java.util.Scanner;
import java.util.Random;

public class day2GuessGame {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        int choice;
        boolean isRunning = true;
        double bal = 0;

        while(isRunning){
            do {
                System.out.println("\n---DICE GUESSING GAME---");
                System.out.println("       ---MENU---       ");
                System.out.println("1. PLAY");
                System.out.println("2. TOP-UP");
                System.out.println("3. WITHDRAW");
                System.out.println("4. BALANCE");
                System.out.println("5. EXIT");
                System.out.print("ENTER CHOICE: ");
                choice = scanner.nextInt();

                switch(choice){
                    case 1:
                        if (bal <= 0){
                            System.out.println("\nINSUFFICIENT FUNDS. TOP-UP FIRST TO PLAY THE GAME");
                        } else{
                            bal = playGame(bal);
                        }
                            break;
                    case 2: bal += topUp();
                            System.out.printf("\nBalance: P%.2f\n", bal);
                            break;
                    case 3: 
                        if (bal <= 0){
                            System.out.println("\nINSUFFICIENT FUNDS.");
                        } else{
                            bal -= withdraw(bal);
                            System.out.printf("\nBalance: P%.2f\n", bal);
                        }
                            break;
                    case 4: showBal(bal);
                            break;
                    case 5: System.out.println("Exiting...");
                            isRunning = false;
                            break;
                    default: System.out.println("\nInvalid Input. Choose from 1-5 only.");
                            break;
                }
            }while(choice > 5 || choice < 1);
        }

        scanner.close();
    }

    static double playGame(double bal){
        Random random = new Random();
        double bet = 0;
        int guess;
        int roll;
        int oddEven;
        int again;
        boolean playing = true;

        System.out.println("                   ---MECHANICS OF THE GAME---                   ");
        System.out.println("*Place your bet and guess the number of the dice");
        System.out.println("**Correct guess of the number will result to 3x bet");
        System.out.println("**Incorrect guess but correct even or odd will result to 1.5x bet");
        System.out.println("**Incorrect in all aspect will result to a complete loss");
        System.out.println("                         ---Have Fun---                          ");

        while(playing){
            double amount = 0;
            
            do {
                System.out.print("\nPlace your bet: ");
                bet = scanner.nextDouble(); 

                if (bet <= 0 || bet > bal) {
                    System.out.println("Invalid bet amount. Insufficient funds");
                }
            } while (bet <= 0 || bet > bal);
            
            do {
                System.out.print("\nGuess the dice (1-6): ");
                guess = scanner.nextInt();

                if (guess < 1 || guess > 6) {
                    System.out.println("Dice value do not exceed 6 and not less than 1");
                }
            } while (guess < 1 || guess > 6);

            do {
                System.out.print("\nOdd or Even(1-Odd|2-Even): ");
                oddEven = scanner.nextInt();

                if(oddEven != 1 && oddEven != 2){
                    System.out.println("Invalid Input. Choose from 1 & 2 only.");
                }
            } while (oddEven != 1 && oddEven != 2);
                
            roll = random.nextInt(1, 7);
            rollDice(roll);
            System.out.println("You rolled: " + roll);

            boolean isEven = (roll % 2 == 0);
            bal -= bet;

            if (guess == roll){
                amount = bet * 3;
                bal += amount;
            } else {
                if (oddEven == 2 && isEven){
                    amount = bet * 1.5;
                    bal += amount;
                } else if(oddEven == 1 && !isEven) {
                    amount = bet * 1.5;
                    bal += amount;
                } else {
                    System.out.println("\nAwww, Nice Try!");
                }
            }

            if (amount > 0) {
                System.out.printf("\nYou won P%.2f\n", amount);
            }
            System.out.printf("\nYour balance is now P%.2f\n", bal);

            do{
                System.out.print("\nDo you still want to play the game(1-yes/2-no)? ");
                again = scanner.nextInt();

                if (again == 1){
                    continue;
                } else if (again == 2){
                    playing = false;
                } else {
                    System.out.println("Invalid Input. Choose from 1 & 2 only.");
                }
            } while(again != 1 && again != 2);
        }

        return bal;
    }

    static void rollDice(int roll){
        String dice1 = """
             ---------
            |         |
            |    O    |
            |         |
             ---------
            """;
        
        String dice2 = """
             ---------
            | O       |
            |         |
            |       O |
             ---------
            """;

        String dice3 = """
             ---------
            | O       |
            |    O    |
            |       O |
             ---------
            """;

        String dice4 = """
             ---------
            | O     O |
            |         |
            | O     O |
             ---------
            """;

        String dice5 = """
             ---------
            | O     O |
            |    O    |
            | O     O |
             ---------
            """;

        String dice6 = """
             ---------
            | O     O |
            | O     O |
            | O     O |
             ---------
            """;

        switch(roll){
            case 1 -> System.out.println(dice1);
            case 2 -> System.out.println(dice2);
            case 3 -> System.out.println(dice3);
            case 4 -> System.out.println(dice4);
            case 5 -> System.out.println(dice5);
            case 6 -> System.out.println(dice6);
        }
    }

    static double topUp(){
        double amount;

        System.out.print("Enter the amount you want to top-up: ");
        amount = scanner.nextDouble();

        if(amount < 0){
            System.out.println("Amount must no be less than P0\n");
            return 0;
        } else {
            return amount;
        }
    }

    static double withdraw(double bal){
        double amount;

        System.out.print("Enter the amount you want to withdraw: ");
        amount = scanner.nextDouble();

        if(amount > bal){
            System.out.println("INSUFFICIENT FUNDS");
            return 0;
        } else {
            return amount;
        }
    }

    static void showBal(double bal){
        System.out.println("\nBalance: " + bal);
    }
}
