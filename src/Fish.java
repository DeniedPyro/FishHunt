import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

class Fish extends Entity {

    protected Image image;
    protected boolean dead = false;

    public Fish(double x, double y, double vx, double vy, Image image) {
        this.x = x;
        this.y = y;
        this.vy = vy;
        this.vx = vx;
        this.width = 130;
        this.height = 130;
        this.image = image;
        this.ay = 100;
    }

    /**
     * getter de la position x
     *
     * @return double
     */
    public double getX() {
        return x;
    }

    /**
     * getter de la position x
     *
     * @return double
     */
    public double getY() {
        return y;
    }

    /**
     * getter de la hauteur
     *
     * @return double
     */
    public double getHeight() {
        return height;
    }

    /**
     * getter de la largeur
     *
     * @return double
     */
    public double getWidth() {
        return width;
    }

    /**
     * Permet de mettre a jour la position,vitesse et acceleration du poisson
     *
     * @param dt
     */
    @Override
    public void update(double dt) {
        // Physique du personnage
        super.update(dt);

    }

    /**
     * permet de signaler que le poisson est mort
     */
    public void isKilled() {
        dead = true;
    }

    public boolean isDead() {
        return dead;
    }

    /**
     * permet de dessiner le poisson
     *
     * @param context
     */
    @Override
    public void draw(GraphicsContext context) {
        context.drawImage(image, x, y, width, height);
    }
}