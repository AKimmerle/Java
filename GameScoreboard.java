public class GameScoreboard {
    private final String [] players;
    private final int [][] roundScores;

    public GameScoreboard(String[] players, int[][] roundScores) {
        this.players = players;
        this.roundScores = roundScores;
    }

    public int getTotalScore(int playerIndex) {
        int totalScore = 0;
        for (int score : roundScores[playerIndex]) {
            totalScore += score;
        }
        return totalScore;
    }

    public String getTopScorer() {
        String topScorer = players[0];
        int highestScore = getTotalScore(0);
        for (int i = 1; i < players.length; i++) {
            if (highestScore < getTotalScore(i)) {
                highestScore = getTotalScore(i);
                topScorer = players[i];
            }
        }
        return topScorer;
    }

    public double getRoundAverage(int roundIndex) {
        int sum = 0;
        int count = 0;
        for (int p = 0; p < players.length; p++) {
            sum += roundScores[p][roundIndex];
            count++;
        }
        return (double) sum / count;
    }

    public boolean isImproving(int playerIndex) {
        for (int i = 1; i < roundScores[playerIndex].length; i++) {
            if (roundScores[playerIndex][i] <= roundScores[playerIndex][i - 1]) {
                return false;
            }
        }
        return true;
    }

    public void printScoreboard() {
        int topIndex = 0;
        for (int s = 0; s < players.length; s++) {
            if (getTotalScore(s) > getTotalScore(topIndex)) {
                topIndex = s;
            }
        }
        System.out.println("Top Scorer: " + players[topIndex] + " with a score of " + getTotalScore(topIndex) + " points.");
        System.out.println("Round 2 Average: " + getRoundAverage(1));

        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i] + " improved every round? " + isImproving(i));
    }
    }

    public static void main(String[] args) {
        String[] names = {"Alice", "Bob", "Charlie"};
        int[][] scores = {
                        {12, 8, 15}, 
                        {9, 14, 11}, 
                        {15, 10, 7}
                        };

        GameScoreboard board = new GameScoreboard(names, scores);
        board.printScoreboard(); 
    }
}