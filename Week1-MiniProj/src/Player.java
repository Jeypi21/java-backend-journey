public class Player {
    private double bal = 0;

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
