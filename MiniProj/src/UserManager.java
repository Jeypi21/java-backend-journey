import java.util.Scanner;
import java.util.ArrayList;

public class UserManager {
    private Scanner input = new Scanner(System.in);
    private ArrayList<Player> users = new ArrayList<>();
    private Player currentPlayer = null;

    public boolean register(){
        System.out.print("\nEnter username: ");
        String newUser = input.nextLine();

        System.out.print("Enter password: ");
        String newPass = input.nextLine();

        if(newUser.isEmpty() || newPass.isEmpty()){
            System.out.println("Username and Password cannot be empty.\n");
            return false;
        }

        for(Player p: users){
            if (p.getUsername().equals(newUser)){
                System.out.println("Username already taken.\n");
                return false;
            }
        }

        Player newPlayer = new Player(newUser, newPass);
        users.add(newPlayer);

        System.out.println("\nRegistration successful!\n");
        return true;
    }

    public boolean login(){
        if (users.isEmpty()){
            System.out.println("\nNo users found. Please register first.\n");
            return false;
        }

        int attempts = 0;
        int maxAttempts = 3;
        int remainingAttempts;

        while (attempts < maxAttempts){
            System.out.print("\nEnter username: ");
            String inputUser = input.nextLine();

            System.out.print("Enter password: ");
            String inputPass = input.nextLine();

            for (Player p : users){
                if (p.getUsername().equals(inputUser)){
                    if (p.checkPassword(inputPass)){
                        currentPlayer = p;
                        System.out.println("\nLogin Successful.\n");
                        return true;
                    } else {
                        attempts++;
                        System.out.println("\nIncorrect Password.\n");
                        remainingAttempts = maxAttempts - attempts;
                        System.out.println("Remaining attempts: " + remainingAttempts);
                    }
                }
            }
            
        }
        System.out.println("\nToo many failed attempts\n");
        return false;
    }

    public void logout(){
        currentPlayer = null;
        System.out.println("\nLogged out.\n");
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public boolean isLoggedIn(){
        return currentPlayer != null;
    }
}
