public class Player {
    private double bal = 0;
    private String user;
    private String pass;

    Player(String username, String password, double balance){
        this.user = username;
        this.pass = password;
        this.bal = balance;
    }

    public double getBal(){
        return bal;
    }

    public void addAmount(double amount){
        bal += amount;
    }

    public boolean reduceAmount(double amount){
        if (amount > bal || amount <= 0) return false;
        bal -= amount;
        return true;
    }

    public String getUsername(){
        return user;
    }

    public String getPassword(){
        return pass;
    }

    public boolean passwordCorrect(String inputPass){
        return inputPass.equals(pass);
    }

    public String fileRecord() {
        return user + "," + pass + "," + bal;
    }
}
