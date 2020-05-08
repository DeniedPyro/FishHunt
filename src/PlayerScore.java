import java.io.Serializable;

public class PlayerScore implements Serializable {
    public String playerName;
    public int score;

    public PlayerScore(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    /**
     * getter du score du joueur
     *
     * @return int
     */
    public int getScore() {
        return score;
    }

    /**
     * getter du nom du joueur
     *
     * @return String
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * setter du nom du joueur
     *
     * @param playerName
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * setter du score du joueur
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * formatage en string du lobjet playerscore
     *
     * @return int
     */
    @Override
    public String toString() {
        return "PlayerScore [playerName=" + playerName + ", score=" + score + "]";
    }

}
