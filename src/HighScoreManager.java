import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static java.lang.StrictMath.E;

public class HighScoreManager {
    private String filepath;
    private ArrayList<Score> scores;

    public HighScoreManager(){
        this.filepath = "src/HighScores.txt";
    }

    public  void addScore(Score score){
        scores.add(score);
    }

    public void writeHighScores() {

        try {

            FileOutputStream fileOut = new FileOutputStream(this.filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this.scores);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object readHighScores() {

        try {

            FileInputStream fileIn = new FileInputStream(this.filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();
            System.out.println(obj);
            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;

        } catch (Exception ex) {
            scores = new ArrayList<Score>();
            return null;
        }
    }





}
