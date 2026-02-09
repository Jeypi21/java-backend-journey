import java.util.Scanner;

public class Player {
    Scanner input = new Scanner(System.in);

    private double bal = 0;
    private String user;
    private String pass;
        
    Player(String user, String pass){
        this.user = user;
        this.pass = pass;
    }

    public void register(){
            
    }

    public void login(){
            
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
