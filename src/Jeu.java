import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class Jeu {

    public static final int WIDTH = 350, HEIGHT = 480;
    private ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
    private Random R = new Random();
    private double bubbleTimeIntervalTrack = 0.0;
    private boolean lock = false;
    public static boolean debug = false;
    private Target target;
    private  int lives;

    public Jeu() {
        this.target = new Target(WIDTH / 2 - 25, HEIGHT - 50);
    }

    /**retourne la valeur du debug
     * @return boolean
     */
    public boolean getDebug() {
        return this.debug;
    }

    public int getLives(){
        return this.lives;
    }


    /**modifie la valeur du debug
     * @param debug
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**Genere un nombre entre min et max
     * @param min
     * @param max
     * @return int
     */
    private int generateNumBetween(int min, int max) {
        return R.nextInt((max - min) + 1) + min;
    }


    /**Ajoute une groupe de bulle a la liste
     * @param b
     */
    private void addBubbleGroup(ArrayList<Bubble> b) {
        Bubble[] balles = new Bubble[5];
        int basex = generateNumBetween(0, WIDTH);
        for (int i = 0; i < 5; i++) {
            int v = generateNumBetween(350, 450);
            int r = generateNumBetween(5, 20);
            int x = generateNumBetween(0, 40) - 20;
            int y = generateNumBetween(0, 20);
            b.add(new Bubble(basex - x, HEIGHT - y , r, -v));
        }
    }

    /** fait la mise a jour du jeu
     * @param dt
     */
    public void update(double dt) {
        /**
         *Cree les bulles a chaque 3 secondes
         */
        this.bubbleTimeIntervalTrack += dt;
        if (this.bubbleTimeIntervalTrack > 3.0 && this.bubbles.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                addBubbleGroup(bubbles);
            }
            this.bubbleTimeIntervalTrack -= 0;
        }

        if (!this.bubbles.isEmpty()) {
            for (int i = 0; i < this.bubbles.size(); i++) {
                Bubble b = this.bubbles.get(i);
                b.update(dt);
            }
        }
        target.update(dt);
    }


    /**Permet de dessiner la le contenue du jeu
     * @param context
     */
    public void draw(GraphicsContext context) {
        context.setFill(Color.DARKBLUE);
        context.fillRect(0, 0, WIDTH, HEIGHT);

        target.draw(context);

        for (int i = 0; i < this.bubbles.size(); i++) {
            Bubble bubble = this.bubbles.get(i);
            if (bubble.getY() >  HEIGHT) {
                bubble.draw(context);
            } else {
                context.clearRect(bubble.getX(), bubble.getY(), bubble.getW(), bubble.getH());
                this.bubbles.remove(i); // Retire l'élément zéro
            }
        }
    }

    public void stop() {
        System.out.println("kk");
    }

    /**Permet relancer le jeu
     */
    public void resetJeu() {
        System.out.println("resetting the game");
    }
}
