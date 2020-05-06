import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Starfish extends Fish {
    private double timeTracker = 0.0;
    private double posY;
    public Starfish(double x, double y, double vx, Image image) {
        super(x,y,vx,0,image);
        this.color = Color.LIGHTGREEN;
        posY = y;
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
        timeTracker += dt;
        y = 50*Math.sin(2*Math.PI/timeTracker) + posY;

    }
    /** permet de dessiner le startfish
     * @param context
     */
    @Override
    public void draw(GraphicsContext context) {
        super.draw(context);
    }


}
