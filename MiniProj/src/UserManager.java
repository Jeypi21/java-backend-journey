import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {
    Scanner input = new Scanner(System.in);
    ArrayList<Player> users = new ArrayList<>();
    Player currentPlayer = null;

    public boolean register(){
        String newUser;
        String newPass;

        System.out.println("\n------------------------------");
        System.out.println("           Register");
        while (true){
            System.out.println("------------------------------");
            System.out.print("Enter username: ");
            newUser = input.nextLine();

            System.out.print("Enter password (maximum of 12 characters only): ");
            newPass = input.nextLine();

            if (newUser.isEmpty() || newPass.isEmpty()){
                System.out.println("\nInput account details.");
                System.out.println("----------------------------");
                continue;
            }

            boolean userTaken = false;  
            
            for(Player p : users){
                if (p.getUsername().equals(newUser)){
                    userTaken = true;
                    break;
                }
            }

            if (userTaken){
                System.out.println("\nUsername is already taken");
                System.out.println("----------------------------");
                return false;
            }

            Player newPlayer = new Player(newUser, newPass);
            users.add(newPlayer);
            System.out.println("\nRegistration Complete.");
            System.out.println("----------------------------");
            return true;
        }
    }

    public void login(){
        String inputUser;
        String inputPass;
        int attempts = 0;
        int maxAttempts = 3;

        System.out.println("\n------------------------------");
        System.out.println("             Login");
        while (attempts < maxAttempts) {
            boolean foundUser = false;
            System.out.println("------------------------------");
            System.out.print("Enter username: ");
            inputUser = input.nextLine();

            System.out.print("Enter password: ");
            inputPass = input.nextLine();

            if (inputUser.isEmpty()|| inputPass.isEmpty()){
                System.out.println("\nInput account details.");
            }

            if (users.isEmpty()){
                System.out.println("\nAccount information not found. Register if you do not have an account.");
                break;
            }

            for(Player p : users){
                if (p.getUsername().equals(inputUser)){
                    foundUser = true;

                    if (p.passwordCorrect(inputPass)){
                        currentPlayer = p;
                        System.out.println("\nLogin Successful.");
                        System.out.println("----------------------------");
                        return;
                    } else {
                        attempts++;
                        System.out.printf("\nInvalid Credentials.\nYou have %d attempts left", (maxAttempts - attempts));
                    }
                }

                if (!foundUser){
                    attempts++;
                    System.out.printf("\nInvalid Credentials.\nYou have %d attempts left", (maxAttempts - attempts));
                }
            }
        }

        if (!users.isEmpty()){
            System.out.println("\nYou've reached the maximum attempts to login.");
        }
        System.out.println("----------------------------");
    }

    public Player getCurrentPlayer(){
        return currentPlayer; 
    }

    public boolean isLoggedIn(){
        if(currentPlayer != null) return true;
        
        return false;
    }

    public void loggedOut(){
        currentPlayer = null;
    }
}
