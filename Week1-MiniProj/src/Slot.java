import java.util.Random;

public class Slot {
    String[] roll(){
        Random random = new Random();

        String[] slot = new String[3];
        String[] slotValues = {"#", "@", "$", "&", "%"};
        
        for (int i = 0; i < 3; i++){
            slot[i] = slotValues[random.nextInt(slotValues.length)];
        }

        return slot;
    }

    void print(String[] slot){
        System.out.println("+---------+");
        System.out.println(" " + String.join(" | ", slot));
        System.out.println("+---------+");
    }

    double result(String[] slot, double bet){
        if (slot[0].equals(slot[1]) && slot[1].equals(slot[2])){
            return switch(slot[0]){
                case "#" -> bet * 3;
                case "@" -> bet * 4;
                case "$" -> bet * 5;
                case "&" -> bet * 10;
                case "%" -> bet * 15;
                default -> 0;
            };
        } else if (slot[0].equals(slot[1])){
            return switch(slot[0]){
                case "#" -> bet * 1.5;
                case "@" -> bet * 2;
                case "$" -> bet * 2.5;
                case "&" -> bet * 5;
                case "%" -> bet * 8;
                default -> 0;
            };
        } else if (slot[1].equals(slot[2])){
            return switch(slot[1]){
                case "#" -> bet * 1.5;
                case "@" -> bet * 2;
                case "$" -> bet * 2.5;
                case "&" -> bet * 5;
                case "%" -> bet * 8;
                default -> 0;
            };
        } else if (slot[0].equals(slot[2])){
            return switch(slot[0]){
                case "#" -> bet * 1.5;
                case "@" -> bet * 2;
                case "$" -> bet * 2.5;
                case "&" -> bet * 5;
                case "%" -> bet * 8;
                default -> 0;
            };
        } else {
            System.out.println("You lose this round");
            System.out.println("BETTER LUCK NEXT TIME BOSS!");
        }

        return 0;
    }
}
