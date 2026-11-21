import java.io.*;
import java.util.*;

public class ChestManager {
    private final String[] items;
    private final int[] quantities;
    private static final int SIZE = 27;

    public ChestManager() {
        items = new String[SIZE];
        quantities = new int[SIZE];
    }

    public boolean addItem(String item, int qty) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null && quantities[i] == 0) {
                items[i] = item;
                quantities[i] = qty;
                return true;}
            
        }
            return false;
    }

    public int getTotalItems() {
        int total = 0;
        for (int qty : quantities) {
            total += qty;
        }
        return total;
    }


    public boolean removeItem(String item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(item)) {
                items[i] = null;
                quantities[i] = 0;
                return true;
            }
        }
        return false;
    }

    public int countSlotsWith(String item) {
        int count = 0;
        for (String it : items) {
            if (it != null)
                if (it.equals(item)) {
                    count++;
        }
        
    }
    return count;
}


    public void printChest() {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                System.out.println("Slot " + i + ": " + items[i] + " x " + quantities[i]);
            }
        }
    }
    public static ChestManager loadFromFile(String filename) throws IOException { 
        Scanner scan = new Scanner(new File (filename));
        ChestManager chest = new ChestManager();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] parts = line.split(",");
            String name = parts[0].trim();
            int qty = Integer.parseInt(parts[1].trim());
            chest.addItem(name, qty);
        }
        scan.close();
        return chest;
    }
    public static void main(String[] args) throws IOException{
        ChestManager chest1 = new ChestManager();
        chest1.addItem("Gold", 10);
        chest1.addItem("Gold", 20);
        System.out.println("Gold slots: " + chest1.countSlotsWith("Gold"));

        ChestManager chest2 = ChestManager.loadFromFile("chest_data.csv");
        System.out.println("Total items: " + chest2.getTotalItems());
        System.out.println("Dirt slots: " + chest2.countSlotsWith("Stone"));
        
        chest2.removeItem("Stone");
        System.out.println("Stone slots after remove: " + chest2.countSlotsWith("Stone"));
        
        System.out.println("\n--- Chest Contents ---");
        chest2.printChest();
    }
}
