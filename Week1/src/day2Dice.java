import java.util.Scanner;
import java.util.Random;

public class day2Dice {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int numDie;
        int total = 0;
        
        System.out.print("Number of die you wanted to roll: ");
        numDie = scanner.nextInt();

        if(numDie > 0){
            for(int i = 0; i < numDie; i++){
                int roll = random.nextInt(1, 7);
                rollDice(roll);
                System.out.printf("You rolled: %d\n", roll);
                total += roll;
            }
        } else {    
            System.out.println("\nInvalid Input.\n");
        }

        System.out.println("Total: " + total);
        scanner.close();
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
}
