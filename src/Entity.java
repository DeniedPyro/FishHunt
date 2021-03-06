import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Entity {

    protected double width, height;
    protected double x, y;
    protected boolean direction = true; // Set to true if looking right.

    protected double vx, vy;
    protected double ax, ay;


    protected Color color;

    /**
     * Met a jour la position et la vitesse de la balle
     *
     * @param dt Temps écoulé depuis le dernier update() en secondes
     */
    public void update(double dt) {
        vx += dt * ax;
        vy += dt * ay;
        x += dt * vx;
        y += dt * vy;
    }

    /**
     * Permet de dessiner lentite
     *
     * @param context
     */
    public abstract void draw(GraphicsContext context);
}
