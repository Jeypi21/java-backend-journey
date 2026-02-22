public class Player {
    private double bal = 0;
    private String user;
    private String pass;

    Player(String username, String password){
        this.user = username;
        this.pass = password;
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

    public boolean passwordCorrect(String inputPass){
        return inputPass.equals(pass);
    }
}
