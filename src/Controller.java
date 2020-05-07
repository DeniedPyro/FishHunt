
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import javax.swing.text.html.ImageView;


public class Controller {

    Game game;

    public Controller() {
        game = new Game();
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
            resetJeu();
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
            case 1:
                lives.getChildren().get(2).setVisible(false);
                lives.getChildren().get(1).setVisible(false);
                lives.getChildren().get(0).setVisible(false);
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
//        if ( game.getLives() == 3){
//            lives.getChildren().get(2).setVisible(true);
//            lives.getChildren().get(1).setVisible(true);
//            lives.getChildren().get(0).setVisible(true);
//        }
//        else if (game.getLives() == 2){
//            lives.getChildren().get(2).setVisible(false);
//            lives.getChildren().get(1).setVisible(true);
//            lives.getChildren().get(0).setVisible(true);
//        }
//        else if (game.getLives() == 1){
//            lives.getChildren().get(2).setVisible(false);
//            lives.getChildren().get(1).setVisible(false);
//            lives.getChildren().get(0).setVisible(true);
//        }
//        else {
//            lives.getChildren().get(2).setVisible(false);
//            lives.getChildren().get(1).setVisible(false);
//            lives.getChildren().get(0).setVisible(false);
//        }

    }

    void updateScore(Text text){
        text.setText(game.getScore()+"");
    }

    void updateLevelText(Text level){
        level.setText("Level "+ game.getLevel());
    }

    void resetJeu(){
        game.resetJeu();
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
