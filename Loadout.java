public class Loadout {
    private final String[] weapons;
    private final int[] powerLevel;

    public static void main(String[] args) {
        String[] guns = {"Ace of Spades", "Gjallarhorn", "Thorn", "Outbreak", "Whisper"};
        int powers[] = {1805, 1812, 1798, 1810, 1800};
        Loadout myLoadout = new Loadout(guns, powers);
        
        System.out.println("Average: " + myLoadout.getAveragePower());
        System.out.println("Strongest: " + myLoadout.getStrongestWeapon());
        System.out.println("Above 1805: " + myLoadout.countAbove(1805));
        
        myLoadout.infuse("Thorn", 1815);
        System.out.println("New Strongest: " + myLoadout.getStrongestWeapon());

        myLoadout.printLoadout();
    }
    
    public Loadout(String[] weapons, int[] powerLevel) {
        this.weapons = weapons;
        this.powerLevel = powerLevel;
    }

    public double getAveragePower() {
        int totalPower = 0;
        int count = 0;
        for (int power : powerLevel) {
            totalPower += power;
            count++;
            }   
        return (double) totalPower / count;
    }      

    public String getStrongestWeapon() {
        String maxWeapon = weapons[0];
        int maxPower = powerLevel[0];
        for (int i = 0; i < powerLevel.length; i++) {
            if (maxPower < powerLevel[i]) {
                maxPower = powerLevel[i];
                maxWeapon = weapons[i];
            }
                
            }
       return maxWeapon;
    }
 
    public int countAbove(int threshold) {
        int count = 0;
        for (int power : powerLevel) {
            if (power > threshold) {
                count++;
            }
        }
        return count;
    }

    public void infuse(String weaponName, int newPower) {
        for (int i = 0; i < powerLevel.length; i++) {
            if (weapons[i].equals(weaponName)) {
                weapons[i] = weaponName;
                powerLevel[i] = newPower;
            }
        }
    }
    
    public void printLoadout() {
        for (int i = 0; i < powerLevel.length; i++) {
         System.out.println(weapons[i] + " - " + powerLevel[i]);
        }
    } 
}
