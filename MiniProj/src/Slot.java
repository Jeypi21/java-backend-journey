import java.util.Random;

public class Slot {
    public String[] roll() {
        Random random = new Random();

        String[] slots = new String[3];
        String[] slots_value = {"@", "#", "$", "%", "&", "="};

        for (int i = 0; i < slots.length; i++){
            slots[i] = slots_value[random.nextInt(slots_value.length)];
        }

        return slots;
    }
    
    public double result(String[] slots, double bet){
        if (slots[0].equals(slots[1]) && slots[1].equals(slots[2])){
            return switch(slots[0]){
                case "@" -> bet * 15;
                case "#" -> bet * 13;
                case "$" -> bet * 10;
                case "%" -> bet * 8;
                case "&" -> bet * 6;
                case "=" -> bet * 4;
                default -> 0;
            };
        } else if (slots[0].equals(slots[1])){
            return switch(slots[0]){
                case "@" -> bet * 7;
                case "#" -> bet * 6;
                case "$" -> bet * 5;
                case "%" -> bet * 4;
                case "&" -> bet * 3;
                case "=" -> bet * 2;
                default -> 0;
            };
        } else if (slots[1].equals(slots[2])){
            return switch(slots[1]){
                case "@" -> bet * 7;
                case "#" -> bet * 6;
                case "$" -> bet * 5;
                case "%" -> bet * 4;
                case "&" -> bet * 3;
                case "=" -> bet * 2;
                default -> 0;
            };
        } else if (slots[0].equals(slots[2])){
            return switch(slots[0]){
                case "@" -> bet * 7;
                case "#" -> bet * 6;
                case "$" -> bet * 5;
                case "%" -> bet * 4;
                case "&" -> bet * 3;
                case "=" -> bet * 2;
                default -> 0;
            };
        } else {
            System.out.println("\nYOU LOSE THIS ROUND!");
            System.out.println("BETTER LUCK NEXT TIME!");
        }

        return 0;
    }

    public void print(String[] slots) {
        System.out.println("+---------+");
        System.out.println(" " + String.join(" | ", slots));
        System.out.println("+---------+");
    }
}



