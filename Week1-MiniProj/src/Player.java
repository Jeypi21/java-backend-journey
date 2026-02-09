import java.util.Scanner;

public class Player {
    Scanner input = new Scanner(System.in);

    private double bal = 0;
    private String user;
    private String pass;
        
    Player(String inputUser, String inputPass){
        this.user = inputUser;
        this.pass = inputPass;
    }

    public boolean register(){
        System.out.print("\nEnter username: ");
        user = input.nextLine();

        System.out.print("\nEnter password: ");
        pass = input.nextLine();

        if(user.isEmpty()){
            System.out.println("Input Username");
        }

        if(pass.isEmpty()){
            System.out.println("Input Password");
        }

        return true;
    }

    public boolean login(){
            
        return true;
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