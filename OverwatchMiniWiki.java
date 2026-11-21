import java.util.Scanner;

public class OverwatchMiniWiki {
    public static void main(String[] args) {
        System.out.println("Welcome to the Overwatch Mini Wiki!");
        System.out.println("Type a valid hero name (or type exit to quit).");
        try (Scanner scan = new Scanner(System.in)) {
            while (true) {
                System.out.print("\nHero: ");
                String input = scan.nextLine();
                
                if (input.equals("exit")) {
                    break;
                }
                
                int index = findHeroIndex(input);
                
                if (index == -1) {
                    System.out.println("Hero not found. Try again.");
                } else {
                    printHeroInfo(index);
                }
            }
        }
    }

    static String[] heros = {"D.Va","Doomfist","Domina","Hazard","JunkerQueen","Mauga","Orisa","Ramattra","Reinhardt","Roadhog","Sigma","Winston","Wrecking Ball","Zarya",
    "Anran","Ashe","Bastion","Cassidy","Echo","Emre","Freja","Genji","Hanzo","Junkrat","Mei","Pharah","Reaper","Sojourn","Soldier: 76","Sombra","Symmetra","Torbjorn","Tracer","Vendetta","Venture","WidowMaker",
    "Ana","Baptise","Brigitte","Illari","Jetpack Cat","Juno","Kiriko","Mizuki","Lifeweaver","Lucio","Mercy","Moira","Wuyang","Zenyatta"};
    
    static String[] weapons = {"Fusion Cannons/Light Gun","Hand Cannon","Photon Magnum","Bonespur","Scattergun","Incendiary Chaingun/Volatile Chaingun","Augmented Fusion Driver","Void Accelerator","Rocket Hammer","Scrap Gun","Hyperspheres","Tesla Cannon","Quad Cannons","Particle Cannon",
    "Zhuque Fans/Fan The Flames","The Viper","Configuration: Recon/Configuration: Assault","Peacekeeper","Tri-Shot","Synthetic Burst Rifle","Revdraw Crossbow","Shuriken","Storm Bow","Frag Launcher","Endothermic Blaster","Rocket Launcher","Hellfire Shotguns","Railgun","Heavy Pulse Rifle","Machine Pistol","Photon Projector","Rivet Gun/Forge Hammer","Pulse Pistols","Palatine Fang","Smart Excavator/Clobber","Widow's Kiss",
    "Biotic Rifle","Biotic Launcher","Rocket Flail","Solar Rifle","Biotic Pawjectiles","Mediblaster","Kunai/Healing Ofuda","Spirit Glaive","Thorn Volley/Healing Blossom","Sonic Amplifier","Caduceus Staff/Caduceus Blaster","Biotic Grasp","Xuanwu Staff","Orb of Destruction"};

    static String[] abilites = {"Eject!, Defense Matrix, Boosters, Micro Missiles, Self-Destruct, and Call Mech", "The Best Defense..., Rocket Punch, Seismic Slam, Power Block, and Meteor Strike","Reconstruction, Barrier Array, Sonic Repulsors, Crystal Charge, and Panopticon","Vault, Spike Guard, Violent Leap, Jagged Wall, and Downpour","Adrenaline Rush, Jagged Blade, Commanding Shout, Carnage, and Rampage","Berserker, Overrun, Cardiac Overdrive, and Cage Fight","Energy Javelin, Fortify, Javelin Spin, and Terra Surge","Void Barrier, Nemesis Form, Pummel, Block, Ravenous Vortex, and Annihilation","Barrier Field, Charge, Fire Strike, and Earthshatter","Chain Hook, Take a Breather, and Whole Hog","Experimental Barrier, Kinetic Grasp, Accretion. and Gravitic Flux","Jump Pack, Barrier Projector, and Primal Rage","Grappling Claw, Roll, Adaptive Shield, Piledriver, and Minefield","Energy, Particle Barrier, Projected Barrier, and Graviton Surge",
    "Ignition, Inferno Rush, Dancing Blaze, and Vermillion Ascent","Coach Gun, Dynamite, and B.O.B.","Ironclad, A-36 Tactical Grenade, Reconfigure, and Configuration: Artillery","Combat Roll, Flashbang, and Deadeye","Glide, Sticky Bombs, Flight, Focusing Beam, and Duplicate","Altered Vitals, Siphon Blaster, Cyber Frag, and Override Protocol","Bounty Hunting, Take Aim, Quick Dash, Updraft, and Bola Shot","Cyber-Agility, Swift Strike, Deflect, and Dragonblade","Wall Climb, Sonic Arrow, Storm Arrows, Lunge, and Dragonstrike","Total Mayhem, Concussion Mine, Steel Trap, and RIP-Tire","Cryo-Freeze, Ice Wall, and Blizzard","Hover Jets, Jet Dash, Jump Jet, Concussive Blast, and Barrage","The Reaping, Wraith Form, Shadow Step, and Death Blossom","Power Slidem, Disruptor Shot, and Overclock","Helix Rockets, Sprint, Biotic Field, and Tactical Visor","Opportunist, Hack, Virus, Translocator, and EMP","Sentry Turret, Teleporter, and Photon Barrier","Deploy Turret, Overload, and Molten Core","Blink, Recall, and Pulse Bomb","Onslaught, Warding Stance, Projected Edge, Whirlwind Dash, Soaring Slice, and Sundering Blade","Explorer's Resolve, Drill Dash, Burrow, and Tectonic Shock","Grappling Hook, Venom Mine, and Infra-Sight",
    "Sleep Dart, Biotic Grenade, and Nano Boost","Exo Boots, Regenerative Burst, Immortality Field, and Amplification Matrix","Inspire, Barrier Shield, Shield Bash, Whip Shot, Repair Pack, and Rally","Outburst, Healing Pylon, and Captive Sun","Jetpack, Frenetic Flight, Lifeline, Purr, and Catnapper","Martian Overboots, Pulsar Torpedoes, Glide Boost, Hyper Ring, and Orbital Ray","Wall Climb, Swift Step, Protection Suzu, and Kitsune Rush","Remedy Aura, Healing Kasa, Katashiro Return, Binding Chain, and Kekkai Sanctuary","Petal Platform, Rejuvenating Dash, Life Grip, and Tree of Life","Wall Ride, Soundwave, Crossfade, Amp It Up, and Sound Barrier","Swift Recovery, Angelic Descent, Guardian Angel, Resurrect, and Valkyrie","Fade, Biotic Orb, and Coalescence","Restorative Stream, Rushing Torrent, Guardian Wave, and Tidal Blast","Snap Kick, Orb of Harmony, Orb of Discord, and Transcendence"};

    static int[] health = {650, 525, 650, 650, 525, 675, 600, 525, 700, 750, 625, 625, 575, 550, 
        250, 250, 350, 250, 225, 250, 225, 250, 250, 250, 300, 225, 300, 225, 250, 225, 275, 300, 175, 275, 250, 225,
        250, 250, 250, 250, 225, 225, 225, 250, 250, 225, 225, 225, 225, 225};

    

    
    private static int findHeroIndex(String name) {
        for (int i = 0; i < heros.length; i++) {
            if (heros[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    
    private static void printHeroInfo(int index) {
        for (int i = 0; i < heros.length; i++) {
            if (i == index) {
            System.out.println(heros[i] + " uses " + weapons[i] + ". " + heros[i] + "'s abilites are " + abilites[i] + ". They have " + health[i] + " total hp");
        }
        }  
    }
    }