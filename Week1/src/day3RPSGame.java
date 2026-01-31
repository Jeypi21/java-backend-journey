import java.util.Scanner;
import java.util.Random;

public class day3RPSGame {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        Random random = new Random();
        //Variables
        int compIndex;
        String[] moves = {"rock", "paper", "scissors"};
        String humanMove;
        String playAgain;
        boolean playing = true;

        //Program
        System.out.println("-----Welcome to Rock-Paper-Scissors Game!-----");
        while(playing){
            do{
                System.out.print("\nEnter your move(rock, paper, scissors): ");
                humanMove = scanner.nextLine();

                if (!humanMove.equals("rock") && !humanMove.equals("paper") && !humanMove.equals("scissors")){
                    System.out.println("Invalid Move Input!");
                }
            } while (!humanMove.equals("rock") && !humanMove.equals("paper") && !humanMove.equals("scissors"));
            
            compIndex = random.nextInt(0, 3);
            String computerMove = moves[compIndex];
            System.out.println("Computer move: " + computerMove);

            if (humanMove.equals(computerMove)){
                System.out.println("Draw!");
            } else if (humanMove.equals("rock") && computerMove.equals("scissors") ||
               humanMove.equals("paper") && computerMove.equals("rock") ||
               humanMove.equals("scissors") && computerMove.equals("paper")){
                System.out.println("You Win!");
            } else {
                System.out.println("You Lose!");
            }

            do {
                System.out.print("\nPlay Again(yes/no): ");
                playAgain = scanner.nextLine();

                if (playAgain.compareToIgnoreCase("yes") == 0){
                    continue;
                } else if (playAgain.compareToIgnoreCase("no") == 0){
                    System.out.println("\nThanks for playing! Exiting...");
                    playing = false;
                } else {
                    System.out.println("Invalid Input! yes or no only!");
                }
            } while (playAgain.compareToIgnoreCase("yes") != 0 && playAgain.compareToIgnoreCase("no") != 0);

        }
    
        scanner.close();    
    }
}
