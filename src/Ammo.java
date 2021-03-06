import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.*;

public class Ammo extends Entity {
    private double r = 50.0;

    public Ammo(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getter du rayon de la balle
     *
     * @return boolean
     */
    public double getR() {
        return r;
    }

    /**
     * Regarde si le carré représentant le balle intersecte
     * le poisson
     *
     * @param other
     * @return boolean
     */
    public boolean intersects(Fish other) {
        return (((this.x <= other.x + other.width) && (this.x >= other.x))
                && ((this.y <= other.y + other.height) && (this.y >= other.y)));
    }

    /**
     * Permet de mettre a jour la position,vitesse et acceleration de la cible
     *
     * @param dt
     */
    @Override
    public void update(double dt) {
        r -= dt * 300.0;
    }

    /**
     * Permet de dessiner la meduse à la position initiale
     *
     * @param context
     */
    @Override
    public void draw(GraphicsContext context) {
        context.setFill(Color.rgb(0, 0, 0));
        context.fillOval(x - r, y - r, 2 * r, 2 * r);
    }
}