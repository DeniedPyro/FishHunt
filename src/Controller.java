
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Text;


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

    void updateLevelText(Text level){

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
