import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    public static final int WIDTH = 640, HEIGHT = 480;
    private Image[] images = {new Image("Image/fish/00.png"),
            new Image("Image/fish/01.png"),
            new Image("Image/fish/02.png"),
            new Image("Image/fish/03.png"),
            new Image("Image/fish/04.png"),
            new Image("Image/fish/05.png"),
            new Image("Image/fish/06.png"),
            new Image("Image/fish/07.png")};
    private Image[] specialImages = {new Image("Image/crabe.png"),
            new Image("Image/star.png")};
    private ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
    private ArrayList<Ammo> ammo = new ArrayList<Ammo>();
    private ArrayList<Fish> fish = new ArrayList<Fish>();
    private Random R = new Random();
    private double bubbleTimeIntervalTrack = 0.0;
    private double fishTimeIntervalTrack = 0.0;
    private double specialFishTimeIntervalTrack = 0.0;
    private boolean lock = true;
    private int level = 1;
    private int lives = 3;
    private int score = 0;
    private double cooldown = 3;
    private int levelScore = 0;
    private boolean allowSpecialFish = false;
    private boolean gameOver = false;
    private PlayerScore playerScore;

    public Game() {
    }

    public void incrementLives() {
        if (this.lives < 3) {
            this.lives += 1;
        }
    }

    public int getScore() {
        return this.score;
    }

    public void setLock(boolean bool) {
        this.lock = bool;
    }

    public double getCoolDown() {
        return this.cooldown;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setPlayerScore(PlayerScore playerScore) {
        this.playerScore = playerScore;
    }

    public void setGameOver(boolean bool) {
        gameOver = bool;
    }

    public void setCoolDown(double cooldown) {
        this.cooldown = cooldown;
    }

    public int getLives() {
        return this.lives;
    }

    public int getLevel() {
        return this.level;
    }

    public void incrementLevel() {
        this.level += 1;
    }

    public void incrementScore() {
        this.score += 1;
        this.levelScore += 1;
    }

    public void die() {
        this.lives = 0;
    }


    /**
     * Genere un nombre entre min et max
     *
     * @param min
     * @param max
     * @return int
     */
    private int generateNumBetween(int min, int max) {
        return R.nextInt((max - min) + 1) + min;
    }


    /**
     * Ajoute un poisson special a la liste
     *
     * @param f
     */
    private void addSpecialFish(ArrayList<Fish> f) {
        int basex = generateNumBetween(0, 1);
        int imageIndex = generateNumBetween(0, 1);
        Image fishImage = specialImages[imageIndex];
        int x = -130;
        double vx = 100 * Math.cbrt(level) + 200;
        if (basex == 0) {
            x = WIDTH;
            vx *= -1;
            fishImage = ImageHelpers.flop(fishImage);
        }
        int y = generateNumBetween(96, 384);
        if (imageIndex == 0) {
            f.add(new Crab(x, y, 1.3 * vx, fishImage));
        } else {
            f.add(new Starfish(x, y, vx, fishImage));
        }


    }

    /**
     * Ajoute un poisson a la liste
     *
     * @param f
     */
    private void addFish(ArrayList<Fish> f) {
        int basex = generateNumBetween(0, 1);
        int imageIndex = generateNumBetween(0, 7);
        Image fishImage = images[imageIndex];
        int x = -130;
        double vx = 100 * Math.cbrt(level) + 200;
        if (basex == 0) {
            x = WIDTH;
            vx *= -1;
            fishImage = ImageHelpers.flop(fishImage);
        }
        int r = generateNumBetween(0, 255);
        int g = generateNumBetween(0, 255);
        int b = generateNumBetween(0, 255);
        Color color = Color.rgb(r, g, b);
        fishImage = ImageHelpers.colorize(fishImage, color);

        int vy = generateNumBetween(100, 200);
        int y = generateNumBetween(96, 384);
        f.add(new Fish(x, y, vx, -vy, fishImage));
    }

    /**
     * Ajoute un groupe de bulle a la liste
     *
     * @param b
     */
    private void addBubbleGroup(ArrayList<Bubble> b) {
        Bubble[] balle = new Bubble[5];
        int basex = generateNumBetween(0, WIDTH);
        for (int i = 0; i < 5; i++) {
            int v = generateNumBetween(350, 450);
            int r = generateNumBetween(5, 20);
            int x = generateNumBetween(0, 40) - 20;
            int y = generateNumBetween(0, 20);
            b.add(new Bubble(basex - x, HEIGHT - y, r, -v));
        }
    }

    /**
     * fait la mise a jour du jeu
     *
     * @param dt
     */
    public void update(double dt) {
        /**
         *Cree les bulles a chaque 3 secondes
         */
        if (this.cooldown > 0 && !lock) {
            this.cooldown -= dt;
        }
        if (this.cooldown <= 0) {
            if (allowSpecialFish) {
                this.specialFishTimeIntervalTrack += dt;
                if (this.specialFishTimeIntervalTrack > 5.0) {
                    this.addSpecialFish(fish);
                    this.specialFishTimeIntervalTrack = 0;
                }
            }
            this.fishTimeIntervalTrack += dt;
            if (this.fishTimeIntervalTrack > 3.0) {
                this.addFish(fish);
                this.fishTimeIntervalTrack = 0;
            }

            for (int i = 0; i < this.ammo.size(); i++) {
                Ammo a = this.ammo.get(i);
                for (int j = 0; j < this.fish.size(); j++) {
                    Fish f = this.fish.get(j);
                    if (a.getR() <= 0) {
                        if (a.intersects(f)) {
                            f.isKilled();
                            incrementScore();
                        }
                        this.ammo.remove(a);
                    }
                }
            }
            if (levelScore >= 5) {
                levelScore -= 5;
                incrementLevel();
                this.setCoolDown(3);
            }
            if (level >= 2 && !allowSpecialFish) {
                allowSpecialFish = true;
            }
        }

        if (!this.ammo.isEmpty()) {
            for (int i = 0; i < this.ammo.size(); i++) {
                Ammo a = this.ammo.get(i);
                a.update(dt);
            }
        }

        this.bubbleTimeIntervalTrack += dt;
        if (this.bubbleTimeIntervalTrack > 3.0 && this.bubbles.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                addBubbleGroup(bubbles);
            }
            this.bubbleTimeIntervalTrack = 0;
        }
        if (!this.bubbles.isEmpty()) {
            for (int i = 0; i < this.bubbles.size(); i++) {
                Bubble b = this.bubbles.get(i);
                b.update(dt);
            }
        }
        if (!this.fish.isEmpty()) {
            for (int i = 0; i < this.fish.size(); i++) {
                Fish f = this.fish.get(i);
                f.update(dt);
            }
        }
    }

    /**
     * Permet de dessiner la le contenue du jeu
     *
     * @param context
     */
    public void draw(GraphicsContext context) {
        context.setFill(Color.DARKBLUE);
        context.fillRect(0, 0, WIDTH, HEIGHT);
        for (int i = 0; i < this.bubbles.size(); i++) {
            Bubble bubble = this.bubbles.get(i);
            if (bubble.getY() > -HEIGHT) {
                bubble.draw(context);
            } else {
                context.clearRect(bubble.getX(), bubble.getY(), bubble.getW(), bubble.getH());
                this.bubbles.remove(i); // Retire l'élément zéro
            }
        }
        for (int i = 0; i < this.ammo.size(); i++) {
            Ammo ammo = this.ammo.get(i);
            ammo.draw(context);
        }
        for (int i = 0; i < this.fish.size(); i++) {
            Fish fish = this.fish.get(i);
            if (fish.isDead()) {
                context.clearRect(fish.getX(), fish.getY(), fish.getWidth(), fish.getHeight());
                this.fish.remove(i);
            } else if (fish.getX() >= -130 && fish.getX() <= WIDTH) {
                fish.draw(context);
            } else {
                context.clearRect(fish.getX(), fish.getY(), fish.getWidth(), fish.getHeight());
                this.fish.remove(i);
                if (cooldown < 0 && lives > 0) {
                    lives -= 1;
                }
            }
        }

    }


    /**
     * reintialise game
     */
    public void resetGame() {
        this.bubbles.clear();
        this.ammo.clear();
        this.fish.clear();
        this.bubbleTimeIntervalTrack = 0.0;
        this.fishTimeIntervalTrack = 0.0;
        this.specialFishTimeIntervalTrack = 0.0;
        this.level = 1;
        this.lives = 3;
        this.score = 0;
        this.lock = true;
        this.cooldown = 3.0;
        this.levelScore = 0;
        this.allowSpecialFish = false;
        this.setGameOver(false);
    }

    /**
     * permet d'ajouter ammo a liste pour evaluer les collisions
     */
    public void fire(double x, double y) {
        Ammo fired = new Ammo(x, y);
        this.ammo.add(fired);
    }
}
