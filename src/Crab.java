import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Crab extends Fish {

    public Crab(int largeur, int x, double y) {

        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = 10;
    }


    
    /** permet de dessiner la plateforme avec une position initiale
     * @param context
     */
    @Override
    public void draw(GraphicsContext context) {
        super.draw(context);
    }


}
