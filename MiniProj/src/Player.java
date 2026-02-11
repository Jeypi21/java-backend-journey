public class Player {
    private double bal = 0;
    private String username;
    private String password;

    public Player(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public boolean checkPassword(String inputPass){
        return password.equals(inputPass);
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