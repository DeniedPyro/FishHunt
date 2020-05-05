import java.io.Serializable;

public class PlayerScore implements Serializable {
    public String playerName;
    public int score ;

    public PlayerScore(String playerName , int score) {
        this.playerName = playerName ;
        this.score = score;
    }

    @Override
    public String toString() {
        return "PlayerScore [playerName=" + playerName + ", score=" + score + "]";
    }

}
