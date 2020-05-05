public class TestMain {


    public static void main(String[] args){
        PlayerScore score1 =  new PlayerScore("a",1);
        PlayerScore score2 =  new PlayerScore("a",2);
        PlayerScore score3 =  new PlayerScore("a",3);
        PlayerScore score4 =  new PlayerScore("a",4);
        PlayerScore score5 =  new PlayerScore("a",5);
        PlayerScore score6 =  new PlayerScore("a",6);
        PlayerScore score7 =  new PlayerScore("a",7);
        PlayerScore score8 =  new PlayerScore("a",8);
        PlayerScore score9 =  new PlayerScore("a",8);
        PlayerScore score10 =  new PlayerScore("a",9);
        PlayerScore score11 =  new PlayerScore("a",10);
        HighScoreManager high = new HighScoreManager();

        high.addScore(score1);
        high.addScore(score2);
        high.addScore(score3);
        high.addScore(score4);
        high.addScore(score5);
        high.addScore(score6);
        high.addScore(score7);
        high.addScore(score8);
        high.addScore(score9);
        high.addScore(score10);
        high.addScore(score11);

        System.out.println(high.getScores());
    }

}
