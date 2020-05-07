
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Controller {

    Game game;
    Stage stage;
    Scene hs ;
    AnimationTimer timer;

    public Controller() {
        game = new Game();
    }

    public void setHs(Scene hs) {
        this.hs = hs;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    void setTimer(AnimationTimer timer){
        this.timer =timer;
    }

    /** Call la methode draw de game
     * @param context
     */
    void draw(GraphicsContext context) {
        game.draw(context);
    }


    /** call la methode update de game puis reset le game si la meduse est morte
     * @param deltaTime
     */
    void update(double deltaTime) {

        game.update(deltaTime);
        if(game.getLives() == 0){
            if(!game.isGameOver()){
                game.setGameOver(true);
                game.setCoolDown(3.0);
            }
            this.gameOver();
        }
    }


    /** call la methode stop du game
     *
     */
    void stop() {
        game.stop();
    }

    /** permet de reset le game
     *
     */

    void updateLives(HBox lives){
        switch (game.getLives()) {
            case 0:
                lives.getChildren().get(2).setVisible(false);
                lives.getChildren().get(1).setVisible(false);
                lives.getChildren().get(0).setVisible(false);
                break;
            case 1:
                lives.getChildren().get(2).setVisible(false);
                lives.getChildren().get(1).setVisible(false);
                lives.getChildren().get(0).setVisible(true);
                break;
            case 2:
                lives.getChildren().get(2).setVisible(false);
                lives.getChildren().get(1).setVisible(true);
                lives.getChildren().get(0).setVisible(true);
                break;
            case 3:
                lives.getChildren().get(2).setVisible(true);
                lives.getChildren().get(1).setVisible(true);
                lives.getChildren().get(0).setVisible(true);
                break;
        }
    }

    void updateScore(Text text){
        text.setText(game.getScore()+"");
    }

    void updateLevelText(Text level){
        if(game.getCoolDown() > 0 && game.getLives() != 0){
            if(!level.getFill().equals(Color.rgb(255,255,255))){
                level.setFill(Color.rgb(255,255,255));
            }
            level.setText("Level "+ game.getLevel());
            level.setVisible(true);
        }
        else if (game.getCoolDown() > 0 && game.getLives() == 0) {
            level.setText("Game Over");
            level.setFill(Color.rgb(255,0,0));
            level.setVisible(true);
        }
        else {
            level.setVisible(false);
        }
    }

    void gameOver(){
        if (game.getCoolDown() <=  0) {
            PlayerScore score = new PlayerScore("",game.getScore());
            game.resetGame();
            stage.setScene(hs);
        }

    }

    void incrementLives(){
        game.incrementLives();           
    }

    void incrementLevel(){
        game.incrementLevel();
    }

    void incrementScore(){
        game.incrementScore();
    }

    void die(){
        game.die();
    }

    void fire(double x, double y) { game.fire(x, y); }
}
