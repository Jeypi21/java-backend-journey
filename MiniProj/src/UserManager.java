import java.util.Scanner;
import java.util.ArrayList;

public class UserManager {
    private Scanner input = new Scanner(System.in);
    private ArrayList<Player> users = new ArrayList<>();
    private Player currentPlayer = null;

    public boolean register(){
        while(true){
            System.out.println("-----------------");
            System.out.print("Enter username: ");
            String newUser = input.nextLine();

            System.out.print("Enter password: ");
            String newPass = input.nextLine();

            if(newUser.isEmpty() || newPass.isEmpty()){
                System.out.println("Username and Password cannot be empty.\n");
                continue;
            }   
            
            boolean userTaken = false;

            for (Player p : users){
                if (p.getUsername().equals(newUser)){
                    userTaken = true;
                    break;
                }
            }

            if (userTaken){
                System.out.println("\nUsername already taken.\n");
                continue;
            }

            Player newPlayer = new Player(newUser, newPass);
            users.add(newPlayer);

            System.out.println("\nRegistration successful!\n");
            return true;
        }
    }

    public void login(){
        if (users.isEmpty()){
            System.out.println("\nNo users found. Please register first.\n");
            return;
        }

        int attempts = 0;
        int maxAttempts = 3;

        while (attempts < maxAttempts){
            boolean foundUser = false;

            System.out.println("-----------------");
            if ((maxAttempts - attempts) == 1){
                System.out.println("\nType exit if you want to cancel login\n");
            }
            System.out.print("Enter username: ");
            String inputUser = input.nextLine();

            if (inputUser.equalsIgnoreCase("exit")){
                System.out.println("\nExiting...\n");
                return;
            }

            System.out.print("Enter password: ");
            String inputPass = input.nextLine();

            for(Player p : users){
                if (p.getUsername().equals(inputUser)){
                    foundUser = true;

                    if (p.checkPassword(inputPass)){
                        currentPlayer = p;
                        System.out.println("\nLogin Successful.\n");
                        return;
                    } else {
                        attempts++;
                        System.out.println("\nUsername or Password is incorrect.\n");
                        System.out.println("Remaining attempts: " + (maxAttempts - attempts));
                    }
                    break;
                }
            }

            if(!foundUser){
                attempts++;
                System.out.println("\nInvalid username or password.\n");
                System.out.println("Remaining attempts: " + (maxAttempts - attempts));
            }
        }

        System.out.println("\nToo many failed attempts\n");
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
