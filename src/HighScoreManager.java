import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class HighScoreManager {
    private String filepath;
    private ArrayList<PlayerScore> scores;

    public HighScoreManager() {
        this.filepath = "src/HighScores.txt";
    }


    /**
     * Retourne les scores bien formatte sous forme dArrayList
     */
    public ArrayList<String> getScores() {
        if (scores == null) {
            this.scores = readHighScores();
        }
        ArrayList<String> fScores = new ArrayList<String>();
        int index = 0;
        for (PlayerScore playerScore : this.scores) {
            fScores.add("#" + (++index) + "-" + playerScore.playerName + "-" + playerScore.score);
        }
        return fScores;
    }

    /**
     * Ajoute le score
     *
     * @param playerscore
     */
    public void addScore(PlayerScore playerscore) {
        this.scores = this.readHighScores();

        int i = 0;
        while (i < this.scores.size() && playerscore.score < scores.get(i).score) {
            i++;
        }
        scores.add(i, playerscore);

        if (scores.size() > 10) {
            scores.remove(10);
        }
        this.writeHighScores();

    }

    /**
     * ecrit les highscore dans le fichier externe
     */
    private void writeHighScores() {
        try {
            FileOutputStream outputFile = new FileOutputStream(this.filepath);
            ObjectOutputStream output = new ObjectOutputStream(outputFile);
            output.writeObject(this.scores);
            output.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * ecrit les highscore dans le fichier externe
     *
     * @return ArrayList <PlayerScore>
     */
    private ArrayList<PlayerScore> readHighScores() {

        try {
            FileInputStream inputFile = new FileInputStream(this.filepath);
            ObjectInputStream input = new ObjectInputStream(inputFile);
            Object obj = input.readObject();
            input.close();

            if (obj == null) {
                return new ArrayList<PlayerScore>();
            } else {
                return (ArrayList<PlayerScore>) obj;
            }
        } catch (Exception ex) {
            // if file is completly empty
            return new ArrayList<PlayerScore>();

        }
    }

}
