import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.*;

public class Crab extends Fish {
    private double bruhTimer = 0.0;
    private boolean bruh = true;
    public Crab(double x, double y, double vx,Image image) {
        super(x,y,vx,0,image);
        this.ay = 0;
    }

    /** Permet de mettre a jour la position,vitesse et acceleration de la crab
     * @param dt
     */
    @Override
    public void update(double dt) {
        // Physique du personnage
        super.update(dt);
        bruhTimer += dt;
        if(bruhTimer > 0.5){
            vx *= -1;
            if (bruh) {
                bruhTimer = 0.25;
                bruh = !bruh;
            }
            else {
                bruhTimer = 0.0;
                bruh = !bruh;
            }
        }
    }

    /** permet de dessiner la plateforme avec une position initiale
     * @param context
     */
    @Override
    public void draw(GraphicsContext context) {
        super.draw(context);
    }
}
