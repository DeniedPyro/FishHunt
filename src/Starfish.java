import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Starfish extends Fish {

    public Starfish(double x, double y, double vx, Image image) {
        super(x,y,vx,0,image);
        this.color = Color.LIGHTGREEN;
    }

    private void resetColor(){
        this.color = Color.LIGHTGREEN ;
    }

    /** Permet de mettre a jour la position,vitesse et acceleration de la crab
     * @param dt
     */
    @Override
    public void update(double dt) {
        // Physique du personnage
        super.update(dt);
        vy = 50.0 * Math.sin(dt);

    }
    /** permet de dessiner le startfish
     * @param context
     */
    @Override
    public void draw(GraphicsContext context) {
        super.draw(context);
    }


}
