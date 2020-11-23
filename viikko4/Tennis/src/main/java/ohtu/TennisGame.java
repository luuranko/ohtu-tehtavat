package ohtu;

public class TennisGame {
    
    private int p1_score = 0;
    private int p2_score = 0;
    private String player1Name;
    private String player2Name;
    private String[] scoreName = {"Love", "Fifteen", "Thirty", "Forty"};

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            p1_score++;
        } else {
            p2_score++;
        }
    }

    public String getScore() {
        String score = "";
        if (p1_score==p2_score) {
            if (p1_score == 4) {
                score = "Deuce";
            } else {
                score = scoreName[p1_score] + "-All";
            }
        } else if (p1_score >= 4 || p2_score >= 4) {
            int difference = p1_score - p2_score;
            if (difference == 1) {
                score = "Advantage " + player1Name;   
            } else if (difference == -1) {
                score = "Advantage " + player2Name;
            } else if (difference >= 2) {
                score = "Win for " + player1Name;
            } else {
                score = "Win for " + player2Name;
            }
        } else {
            score += scoreName[p1_score] + "-" + scoreName[p2_score];
        }
        return score;
    }
}