import java.util.Scanner;

public class day1MiniProj {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String item;
        double price;
        int qty;
        double total;
        char currency = '$';

        System.out.print("What item would you like to buy? ");
        item = scanner.nextLine();

        System.out.print("What is the price for each? ");
        price = scanner.nextDouble();

        System.out.print("How many would you like to buy? ");
        qty = scanner.nextInt();

        total = price * qty;

        System.out.println("You have bought " + qty + " " + item + "/s");
        System.out.println("Your total is " + currency + total);

        scanner.close();
    }
}
