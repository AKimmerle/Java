import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EnchantmentTracker {
    private final String[] enchantNames;
    private final int[] levels;
    private final String[] itemTypes;
    private int count;

    public EnchantmentTracker(int maxSize) {
        enchantNames = new String[maxSize];
        levels = new int[maxSize];
        itemTypes = new String[maxSize];
        count = 0;
    }

    public boolean addEnchantments(String name, int level, String item) {
        for (int i = 0; i < enchantNames.length; i++) {
            if (enchantNames[i] == null && levels[i] == 0 && itemTypes[i] == null) {
                enchantNames[i] = name;
                levels[i] = level;
                itemTypes[i] = item;
                count++;
                return true;
            }
        }
        return false;
    }

    public String getEnchantmentsFor(String itemType) {
        String enchantments = "";
        for (int i = 0; i < itemTypes.length; i++) {
            if (itemTypes[i] != null && itemTypes[i].equals(itemType)) {
                if (enchantments.length() == 0) {
                    enchantments += enchantNames[i] + " " + toRoman(levels[i]);
                }
                else {
                    enchantments += ", " + enchantNames[i] + " " + toRoman(levels[i]);
                }
            }
        }
        return enchantments;
    }

    private String toRoman(int level) {
        String[] numerals = {"", "I", "II", "III", "IV", "V"};
        if (level >= 1 && level <= 5) {
            return numerals[level];
        }
        return String.valueOf(level);
    }
    
    public int getMaxLevel(String enchantName) {
        int maxLevel = 0;
        for (int i = 0; i < enchantNames.length; i++) {
            if (enchantNames[i] != null && enchantNames[i].equals(enchantName)) {
                if (levels[i] > maxLevel) {
                     maxLevel = levels[i];
                }
            }
        }
        return maxLevel;
    }
    
    public int countEnchantments(String itemType) {
        int numEnchantments = 0;
        for (String item : itemTypes) {
            if (item != null && item.equals(itemType)) {
                numEnchantments++;

            }
        }
        return numEnchantments;
    }

    public boolean hasEnchantment(String enchantName) {
        for (String enchantment : enchantNames) {
            if (enchantment != null && enchantment.equals(enchantName)) {
                return true;
            }
        }
        return false;
    }

    public String printAll() {
        String allEnchantments = "";
        for (int i = 0; i < enchantNames.length; i++) {
            if (enchantNames[i] != null && itemTypes[i] != null) {
                allEnchantments += enchantNames[i] + " " + toRoman(levels[i]) + " - " + itemTypes[i] + "\n";
            }
        }
        return allEnchantments;
    }
    
    public static EnchantmentTracker loadFromFile(String filename) throws IOException{
        EnchantmentTracker tracker = new EnchantmentTracker(100);
        Scanner scan = new Scanner(new File(filename));
        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] parts = line.split(",");
            int level = Integer.parseInt(parts[1]);
            tracker.addEnchantments(parts[0], level, parts[2]);
        }
        scan.close();
        return tracker;
    }

    public static void main(String[] args) throws IOException {
        EnchantmentTracker tracker = EnchantmentTracker.loadFromFile("enchants.csv");
        
        System.out.println("Sword enchantments: " + tracker.getEnchantmentsFor("Sword"));
        System.out.println("Max Sharpness: " + tracker.getMaxLevel("Sharpness"));
        System.out.println("Pickaxe count: " + tracker.countEnchantments("Pickaxe"));
        System.out.println("Has Mending? " + tracker.hasEnchantment("Mending"));
        System.out.println("Has Looting? " + tracker.hasEnchantment("Looting"));
    }
}