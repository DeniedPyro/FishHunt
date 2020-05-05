import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class HighScoreManager {
    private String filepath;
    private ArrayList<PlayerScore> scores;

    public HighScoreManager(){
        this.filepath = "src/HighScores.txt";
    }



    public ArrayList<String> getScores(){
        if (scores == null){
            this.scores = readHighScores();
        }
         ArrayList<String> fScores = new ArrayList<String>();

        for (PlayerScore playerScore : this.scores) {
            fScores.add(playerScore.playerName+"-"+playerScore.score);
        }
        return  fScores;
    }

    public  void addScore(PlayerScore playerscore){
        this.scores = this.readHighScores();

        int i = 0;
        while (i < this.scores.size() && playerscore.score < scores.get(i).score){
            i++;
        }
        scores.add(i,playerscore);

        if (scores.size() > 10){
            scores.remove(10);
        }
        this.writeHighScores();

    }

    private void writeHighScores() {

        try {
            FileOutputStream fileOut = new FileOutputStream(this.filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this.scores);
            objectOut.close();
            //System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private ArrayList <PlayerScore> readHighScores() {

        try {

            FileInputStream fileIn = new FileInputStream(this.filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();
            //System.out.println("The Object has been read from the file");
            objectIn.close();

            if (obj == null){
               return new ArrayList<PlayerScore>();
            }

            else {
                return (ArrayList<PlayerScore>) obj;
            }

        } catch (Exception ex) {
            // if file is completly empty
            return new ArrayList<PlayerScore>();

        }
    }





}
