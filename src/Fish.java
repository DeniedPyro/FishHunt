import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

class Fish extends Entity {
    protected Image image;
    protected boolean dead = false;
    public Fish (double x, double y, double vx , double vy, Image image){
        this.x = x;
        this.y = y;
        this.vy = vy;
        this.vx = vx ;
        this.width = 100;
        this.height = 100;
        this.image = image;
        this.ay = 100;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    public double getHeight(){
        return height;
    }
    public double getWidth(){
        return width;
    }

    /** Permet de mettre a jour la position,vitesse et acceleration de la poisson
     * @param dt
     */
    @Override
    public void update(double dt) {
        // Physique du personnage
        super.update(dt);

    }
    public void isKilled(){
        dead = true;
    }
    public boolean isDead(){
        return dead;
    }
    /** permet de dessiner la plateforme avec une position initiale
     * @param context
     */
    @Override
    public void draw(GraphicsContext context) {
        context.drawImage(image, x, y, width, height);
    }
}