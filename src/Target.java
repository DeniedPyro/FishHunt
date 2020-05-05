import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.awt.*;

public class Target extends Entity{
    private Image[][] frames;
    private Image image;
    private double frameRate = 8; // 8 frames par sec
    private double tempsTotal = 0;
    public boolean isAlive = true;
    private boolean parterre;
    private boolean moved;

    public Target(double x, double y) {
        this.x = x;
        this.y = y;
        this.largeur = 50;
        this.hauteur = 50;
        this.ay = 1200;
        this.ax = 0;
        this.vx = 0;
        this.vy = 0;
        this.parterre = true;
        this.image = new Image("");
    }

    /** Permet de mettre a jour la position,vitesse et acceleration de la ciblr
     * @param dt
     */
    @Override
    public void update(double dt) {
        super.update(dt);

    }



    /** Regarde si le carré représentant meduse intersecte
     * une plateforme
     * @param other
     * @return boolean
     */
    public boolean intersects(Entity other) {
        return !( // Un des carrés est à gauche de l’autre
                x + largeur < other.x
                        || other.x + other.largeur < this.x
                        // Un des carrés est en haut de l’autre
                        || y + hauteur < other.y
                        || other.y + other.hauteur < this.y
        );
    }



    /** Permet de dessiner la meduse à la position initiale
     * @param context
     */
    @Override
    public void draw(GraphicsContext context) {

        context.drawImage(image, x, y, largeur, hauteur);

    }


}
