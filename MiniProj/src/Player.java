import java.util.Scanner;

public class Player {
    Scanner input = new Scanner(System.in);

    private double bal = 0;
    private String user;
    private String pass;

    public boolean register(){
        System.out.print("\nEnter username: ");
        String newUser = input.nextLine();

        System.out.print("Enter password: ");
        String newPass = input.nextLine();

        if(newUser.isEmpty() || newPass.isEmpty()){
            System.out.println("Username and Password cannot be empty.\n");
            return false;
        }

        this.user = newUser;
        this.pass = newPass;

        System.out.println("\nRegistration successful!\n");
        return true;
    }

    public boolean login(){
        if (user == null || pass == null){
            System.out.println("\nNo registered account found. Please register first.\n");

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

            if(inputUser.equals(user) && inputPass.equals(pass)){
                System.out.println("\nLogin Successful\n");
                return true;
            } else {
                attempts++;
                System.out.println("\nInvalid Username and Password\n");
                remainingAttempts = maxAttempts - attempts;
                System.out.println("Remaining attempts: " + remainingAttempts);
            }
        }

        System.out.println("\nToo many failed attempts\n");
        return false;
    }

    public void logout(){

    }


    public double getBal(){
        return bal;
    }

    public void addBal(double amount){
        bal += amount;
    }

    public boolean withdraw(double amount){
        if (amount > bal) return false;
        bal -= amount;
        return true;
    }
}