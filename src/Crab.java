import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.*;

public class Crab extends Fish {
    private double crabTimer = 0.0;
    private boolean crabAdvance = true;

    public Crab(double x, double y, double vx, Image image) {
        super(x, y, vx, 0, image);
        this.ay = 0;
    }

    /**
     * Permet de mettre a jour la position,vitesse du crabe
     *
     * @param dt
     */
    @Override
    public void update(double dt) {
        // Physique du personnage
        super.update(dt);
        crabTimer += dt;
        if (crabTimer > 0.5) {
            vx *= -1;
            if (crabAdvance) {
                crabTimer = 0.25;
            } else {
                crabTimer = 0.0;
            }
            crabAdvance = !crabAdvance;
        }
    }

    /**
     * permet de dessiner le crabe
     *
     * @param context
     */
    @Override
    public void draw(GraphicsContext context) {
        super.draw(context);
    }
}
