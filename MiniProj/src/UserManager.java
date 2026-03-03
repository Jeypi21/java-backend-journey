import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class UserManager {
    Scanner input = new Scanner(System.in);
    ArrayList<Player> users = new ArrayList<>();
    Player currentPlayer = null;

    private String filePath = "C:\\Users\\John Paul\\OneDrive\\Desktop\\Java Program\\MiniProj\\src\\users.txt";

    public UserManager(){
        loadUsersFromFile();
    }

    public boolean register(){
        String newUser;
        String newPass;
        double bal = 0;

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
                continue;
            }

            if (newPass.length() > 12){
                System.out.println("\nPassword must not exceed 12 characters.");
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
                System.out.println("\nAccount information is already registered.");
                System.out.println("------------------------------");
                return false;
            }

            Player newPlayer = new Player(newUser, newPass, bal);
            users.add(newPlayer);

            try(FileWriter writer = new FileWriter(filePath, true)){
                writer.write(newUser + "," + newPass + "," + bal + System.lineSeparator());
            }
            catch(IOException e){
                System.out.println("\nCould not write file.");
            }
            System.out.println("\nRegistration Complete.");
            System.out.println("------------------------------");
            return true;
        }
    }

    public void saveAllUsersToFile(){
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (Player p : users) {
                writer.write(p.fileRecord() + System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            // first run: file doesn't exist yet, ok
        } catch (IOException e) {
            System.out.println("Could not read users file.");
        }
    }

    public void loadUsersFromFile(){
        users.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",", 3);
                if (parts.length < 3) continue;

                String username = parts[0].trim();
                String password = parts[1].trim();
                double bal = Double.parseDouble(parts[2].trim());

                users.add(new Player(username, password, bal));
            }
        } catch (FileNotFoundException e) {
            // first run: file doesn't exist yet, ok
        } catch (IOException e) {
            System.out.println("Could not read users file.");
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
                continue;
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
                        System.out.printf("\nInvalid Credentials.\nYou have %d attempts left\n", (maxAttempts - attempts));
                    }
                    break;
                }
            }

            if (!foundUser){
                attempts++;
                System.out.printf("\nInvalid Credentials.\nYou have %d attempts left\n", (maxAttempts - attempts));
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
        saveAllUsersToFile();
        currentPlayer = null;
    }
}
