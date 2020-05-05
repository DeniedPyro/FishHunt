import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Starfish extends Fish {

    public Starfish(int largeur, int x, double y) {

        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = 10;

        this.color = Color.LIGHTGREEN;
    }

    private void resetColor(){
        this.color = Color.LIGHTGREEN ;
    }

    
    /** permet de dessiner la plateforme
     * @param context
     */
    @Override
    public void draw(GraphicsContext context) {
        super.draw(context);
    }


}
