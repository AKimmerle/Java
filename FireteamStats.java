import java.io.*;
import java.util.*;

public class FireteamStats {
    private String[] guardians;
    private int[] kills;
    private int[] deaths;
    static private int size;

    public FireteamStats(int maxSize) {
        guardians = new String[maxSize];
        kills = new int[maxSize];
        deaths = new int[maxSize];
        size = 0;
    }

    public static FireteamStats loadFromFile(String filename) throws IOException{
        Scanner scan = new Scanner(new File(filename));
        FireteamStats fireteam = new FireteamStats(50);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] parts = line.split(",");
            String name = parts[0].trim();
            int k = Integer.parseInt(parts[1].trim());
            int d = Integer.parseInt(parts[2].trim());
            fireteam.addGuardian(name, k, d);
        }
        scan.close();
        return fireteam;
    }

    public boolean addGuardian(String name, int k, int d) {
        if (size >= guardians.length) {
            return false;
        }
        guardians[size] = name;
        kills[size] = k;
        deaths[size] = d;
        size++;
        return true;
    }

    public int getTotalKills() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += kills[i];
        }
        return sum;
    }
     public double getAverageKD() {
        int totalKills = 0;
        int totalDeaths = 0;
        for (int i = 0; i < kills.length; i++) {
            totalKills += kills[i];
            totalDeaths += deaths[i];
        }
        return (double) totalKills / totalDeaths;
    }

    public int countAboveKD(double threshold) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            double kd;
            if (deaths[i] == 0) {
                return 0;
            } else {
                kd = (double) kills[i] / deaths[i];
            }

            if (kd > threshold) {
                count++;
            }
        }

        return count;
    }

    public String getMVP() {
        if (size == 0) {
            return "";
        }
        int maxIndex = 0;
        int maxKills = kills[0];

        for (int i = 1; i < size; i++) {
            if (kills[i] > maxKills) {
                maxKills = kills[i];
                maxIndex = i;
            }
        }
        return guardians[maxIndex];
    }

    public static void main (String[] args) throws IOException{
        FireteamStats team1 = new FireteamStats(10);
        team1.addGuardian("TestTitan", 20, 5);
        team1.addGuardian("TestHunter", 30, 10);
        System.out.println("Total kills: " + team1.getTotalKills());
        System.out.println("MVP: " + team1.getMVP());

        FireteamStats team2 = FireteamStats.loadFromFile("fireteam_data.csv");
        System.out.println("Total kills: " + team2.getTotalKills());
        System.out.println("Average K/D: " + team2.getAverageKD());
        System.out.println("MVP: " + team2.getMVP());
        System.out.println("Above 4.0 KD: " + team2.countAboveKD(4.0));
    }
}