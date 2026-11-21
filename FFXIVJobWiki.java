import java.util.Scanner;

public class FFXIVJobWiki {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
        System.out.println("Welcome to the FFXIV Job Wiki!");

        while (true) {
            System.out.println("\nWhat are you looking for? Role or specific job (or type exit to quit): ");
            String input = scan.nextLine().toLowerCase();
            
            if (input.equals("exit")) {
                System.out.println("Goodbye!");
                break;
            } 
            else if (input.equals("role")) {
                System.out.println("Which role? (tank, healer, melee dps, physical ranged dps, magical ranged dps): ");
                String roleInput = scan.nextLine().toLowerCase();
                System.out.println (printJobsByRole(roleInput));
            } 
            else if (input.equals("job")) {
                System.out.println("Which job?: ");
                String jobInput = scan.nextLine().trim();
                int index = findJobIndex(jobInput);
                if (index == -1) {
                    System.out.println("Job does not exist, please try again.");
                } else {
                    printJobInfo(index);
                }
            } 
            else {
                System.out.println("Looks like you spelt something wrong. Please try again.");
            }
        }
    }
    }
    
    static String[] jobs = {"Paladin", "Warrior", "Dark Knight", "Gunbreaker", 
    "White Mage", "Scholar", "Astrologian", "Sage", 
    "Monk", "Dragoon", "Ninja", "Samurai", "Reaper", "Viper",
    "Bard", "Machinist", "Dancer",
    "Black Mage", "Summoner", "Red Mage", "Pictomancer"};

    static String[] weapons = {"sword and a shield", "axe", "greatsword", "gunblade", 
    "two-handed staff/one-handed staff with a shield", "grimoire", "planisphere", "noulith", 
    "knuckleduster/claw/pata/tonfa", "lance/spear", "dual dagger", "katana", "scythe", "dual-wielded sword", 
    "bow", "firearm", "chakram",
    "two-handed staves/rods", "grimoires", "rapier combined with a magical crystal focus", "paintbrush"};

    static String[] classes = {"tank", "tank", "tank", "tank", 
    "healer", "healer", "healer", "healer", 
    "melee dps", "melee dps", "melee dps", "melee dps", "melee dps", "melee dps",
    "physical ranged dps", "physical ranged dps", "physical ranged dps",
    "magical ranged dps", "magical ranged dps", "magical ranged dps", "magical ranged dps"};

    static String[] jobDescriptions = {
    // Tanks
    "a sword-and-shield knight that protects allies with holy magic alongside its defense.",
    "a berserker who channels inner rage to both deal damage and heal themselves.",
    "a brooding tank that weaponizes darkness and sacrifices HP to fuel its power.",
    "a fast, aggressive tank that fires magical cartridges through its gunblade.",
    // Healers
    "a pure healer with powerful restoration spells and hard-hitting nature magic.",
    "a tactician who deploys a fairy companion to assist with shielding and healing.",
    "a celestial healer who draws cards to buff allies and adapt to any situation.",
    "a futuristic healer who fires energy projectiles that simultaneously deal damage and heal.",
    // Melee DPS
    "a martial artist who chains together chakra-fueled combo strikes at rapid speed.",
    "a lancer who leaps through the air and channels the power of dragons.",
    "an agile fighter who weaves hand signs to cast powerful ninjutsu abilities.",
    "a damage-focused swordsman who builds and spends Sen resources for devastating strikes.",
    "a scythe-wielder who makes a pact with a void avatar to amplify their destruction.",
    "a dual-blade hunter who alternates between two swords with rapid, fluid combos.",
    // Physical Ranged DPS
    "an archer who sings battle songs to buff the party while firing on the move.",
    "an engineer who deploys turrets and automata to unleash a flurry of firepower.",
    "a graceful fighter who performs dance steps to buff allies and chain flashy attacks.",
    // Magical Ranged DPS
    "a powerhouse spellcaster who plants himself in place to unleash catastrophic spells.",
    "a mage who channels the power of primals like Ifrit, Titan, and Garuda.",
    "a versatile duelist who balances black and white magic before finishing with a sword combo.",
    "an artist who paints creatures and landscapes into reality to attack enemies."
};

    static String[] baseClasses = {
    // Tanks
    "Gladiator",    // Paladin
    "Marauder",     // Warrior
    "None",         // Dark Knight
    "None",         // Gunbreaker
    // Healers
    "Conjurer",     // White Mage
    "Arcanist",     // Scholar
    "None",         // Astrologian
    "None",         // Sage
    // Melee DPS
    "Pugilist",     // Monk
    "Lancer",       // Dragoon
    "Rogue",        // Ninja
    "None",         // Samurai
    "None",         // Reaper
    "None",         // Viper
    // Physical Ranged DPS
    "Archer",       // Bard
    "None",         // Machinist
    "None",         // Dancer
    // Magical Ranged DPS
    "Thaumaturge",  // Black Mage
    "Arcanist",     // Summoner
    "None",         // Red Mage
    "None"          // Pictomancer
    };

    static String[] unlockLocations = {
    "Ul'dah",       // Paladin
    "Limsa Lominsa", // Warrior
    "Ishgard",      // Dark Knight
    "Gridania",     // Gunbreaker
    "Gridania",     // White Mage
    "Limsa Lominsa", // Scholar
    "Ishgard",      // Astrologian
    "Limsa Lominsa", // Sage
    "Ul'dah",       // Monk
    "Gridania",     // Dragoon
    "Limsa Lominsa", // Ninja
    "Ul'dah",       // Samurai
    "Ul'dah",       // Reaper
    "Ul'dah",          // Viper
    "Gridania",     // Bard
    "Ishgard",      // Machinist
    "Limsa Lominsa", // Dancer
    "Ul'dah",       // Black Mage
    "Limsa Lominsa", // Summoner
    "Ul'dah",       // Red Mage
    "Gridania"           // Pictomancer
};
    static int[] starterLevel = {30, 30, 30, 60,
        30, 30, 30, 70, 
        30, 30, 30, 50, 70, 80, 
        30, 50, 60, 
        30, 30, 50, 80
    };
    
    public static int findJobIndex(String job) {
        for (int i = 0; i < jobs.length; i++) {
            if (jobs[i].equalsIgnoreCase(job)) {
                return i;
            }
        }
        return -1;
    }

   public static String printJobsByRole(String role) {
    String roleJob = "";
    if (role.equalsIgnoreCase("tank")) {
        roleJob = "The jobs that associate with tank are ";
        roleJob += jobs[0] + ", ";
        roleJob += jobs[1] + ", ";
        roleJob += jobs[2] + ", and ";
        roleJob += jobs[3];
        
    }
    else if (role.equalsIgnoreCase("healer")) {
        roleJob = "The jobs that associate with healer are ";
        roleJob += jobs[4] + ", ";
        roleJob += jobs[5] + ", ";
        roleJob += jobs[6] + ", and ";
        roleJob += jobs[7];
    }
    else if (role.equalsIgnoreCase("melee dps")) {
        roleJob = "The jobs that associate with melee dps are ";
        roleJob += jobs[8] + ", ";
        roleJob += jobs[9] + ", ";
        roleJob += jobs[10] + ", ";
        roleJob += jobs[11] + ", ";
        roleJob += jobs[12] + ", and ";
        roleJob += jobs[13];
    }
    else if (role.equalsIgnoreCase("physical ranged dps")) {
        roleJob = "The jobs that associate with physical ranged dps are ";
        roleJob += jobs[14] + ", ";
        roleJob += jobs[15] + ", and ";
        roleJob += jobs[16];
    }
    else if (role.equalsIgnoreCase("magical ranged dps")) {
        roleJob = "The jobs that associate with magical ranged dps are ";
        roleJob += jobs[17] + ", ";
        roleJob += jobs[18] + ", ";
        roleJob += jobs[19] + ", and ";
        roleJob += jobs[20];
    }
    return roleJob;
   }
    
    public static void printJobInfo(int index) {
       System.out.println(jobs[index] + "s are " + jobDescriptions[index] + " " + jobs[index] + " uses a " + weapons[index] + ", starts at level " + starterLevel[index] + ", and is a " + classes[index] + ".");
       if (baseClasses[index].equals("None")) {
        System.out.println(jobs[index] + " has no base class.");
        } 
        else {
            System.out.println(jobs[index] + " is unlocked from " + baseClasses[index] + " at " + unlockLocations[index] + ".");
        }
    }
}