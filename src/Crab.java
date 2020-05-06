import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Crab extends Fish {

    public Crab(double x, double y, double vx, double vy) {
        super(x,y,vx,vy);
        this.largeur = 100;
        this.hauteur = 100;
        this.color = Color.LIGHTGREEN;
    }

    /** Permet de mettre a jour la position,vitesse et acceleration de la crab
     * @param dt
     */
    @Override
    public void update(double dt) {
        // Physique du personnage
        //super.update(dt);

    }




    /** permet de dessiner la plateforme avec une position initiale
     * @param context
     */
    @Override
    public void draw(GraphicsContext context) {
        super.draw(context);
    }


}
