import javafx.scene.canvas.GraphicsContext;

class Fish extends Entity {

    public Fish (double x, double y, double vx , double vy){
        this.x = x;
        this.y = y ;
        this.vy = vy;
        this.vx = vx ;
        this.largeur = 100;
        this.hauteur = 100;
    }

    /** Permet de mettre a jour la position,vitesse et acceleration de la poisson
     * @param dt
     */
    @Override
    public void update(double dt) {
        // Physique du personnage
        super.update(dt);

    }


    /** permet de dessiner la plateforme avec une position initiale
     * @param context
     */
    @Override
    public void draw(GraphicsContext context) {
        context.setFill(color);
        context.fillRect(x, y, largeur, hauteur);
    }

}