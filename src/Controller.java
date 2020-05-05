
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Text;


public class Controller {

    Jeu jeu;

    public Controller() {
        jeu = new Jeu();
    }


    /** Call la methode draw de jeu
     * @param context
     */
    void draw(GraphicsContext context) {
        jeu.draw(context);
    }


    /** call la methode update de jeu puis reset le jeu si la meduse est morte
     * @param deltaTime
     */
    void update(double deltaTime) {
        jeu.update(deltaTime);
        if(jeu.getLives() == 0){
            resetJeu();
        }
    }


    /** call la methode stop du jeu
     *
     */
    void stop() {
        jeu.stop();
    }

    /** permet de toggle le mode Debug
     *
     */
    void toggleDebug() {
        jeu.setDebug(!jeu.getDebug());
    }




    /** permet de reset le jeu
     *
     */
    void resetJeu(){
        jeu.resetJeu();
    }

    /** call la methode getDebug du jeu
     *
     */

    boolean getDebug() {
        return jeu.getDebug();
    }

    /**Arrete le mouvement de la meduse
     */
}
