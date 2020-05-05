public class TestMain {


    public static void main(String[] args){
        HighScoreManager high = new HighScoreManager();
        high.readHighScores();
        high.writeHighScores();
    }

}
