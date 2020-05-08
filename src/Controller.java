import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Controller {

    Game game;
    Stage stage;
    Scene hs;

    public Controller() {
        game = new Game();
    }

    /**
     * Permet de set la variable lock
     *
     * @param bool
     */
    public void setLock(boolean bool) {
        game.setLock(bool);
    }

    /**
     * Permet de set le highscore
     *
     * @param hs
     */
    public void setHs(Scene hs) {
        this.hs = hs;
    }

    /**
     * Permet de set le stage
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }


    /**
     * Call la methode draw de game
     *
     * @param context
     */
    void draw(GraphicsContext context) {
        game.draw(context);
    }


    /**
     * call la methode update de game puis reset le game si la meduse est morte
     *
     * @param deltaTime
     */
    void update(double deltaTime) {

        game.update(deltaTime);
        if (game.getLives() == 0) {
            if (!game.isGameOver()) {
                game.setGameOver(true);
                game.setCoolDown(3);
            }
            this.gameOver();
        }
    }


    /**
     * permet dobtenir le score de game
     */
    int getScore() {
        return game.getScore();
    }

    /**
     * permet de reset le game
     */
    void resetGame() {
        game.resetGame();
    }

    /**
     * met a jour laffichage des vies du joueur
     *
     * @param lives
     */
    void updateLives(HBox lives) {
        switch (game.getLives()) {
            case 0:
                lives.getChildren().get(2).setVisible(false);
                lives.getChildren().get(1).setVisible(false);
                lives.getChildren().get(0).setVisible(false);
                break;
            case 1:
                lives.getChildren().get(2).setVisible(false);
                lives.getChildren().get(1).setVisible(false);
                lives.getChildren().get(0).setVisible(true);
                break;
            case 2:
                lives.getChildren().get(2).setVisible(false);
                lives.getChildren().get(1).setVisible(true);
                lives.getChildren().get(0).setVisible(true);
                break;
            case 3:
                lives.getChildren().get(2).setVisible(true);
                lives.getChildren().get(1).setVisible(true);
                lives.getChildren().get(0).setVisible(true);
                break;
        }
    }

    /**
     * met a jour laffichage du score du joueur depuis
     * le menu highscore
     *
     * @param score
     */
    void updateHighScore(Text score) {
        score.setText("a fait " + getScore() + " points!");
    }


    /**
     * met a jour laffichage le score depuis le menu du jeu
     *
     * @param score
     */
    void updateScore(Text score) {
        score.setText(game.getScore() + "");
    }

    /**
     * met a jour laffichage le niveau actuelle du jeu
     *
     * @param level
     */
    void updateLevelText(Text level) {
        if (game.getCoolDown() >= 0 && game.getLives() != 0) {
            if (!level.getFill().equals(Color.rgb(255, 255, 255))) {
                level.setFill(Color.rgb(255, 255, 255));
            }
            level.setText("Level " + game.getLevel());
            level.setVisible(true);
        } else if (game.getCoolDown() > 0 && game.getLives() == 0) {
            level.setText("Game Over");
            level.setFill(Color.rgb(255, 0, 0));
            level.setVisible(true);
        } else {
            level.setVisible(false);
        }
    }

    /**
     * lance la procedure de gameover
     */
    void gameOver() {
        if (game.getCoolDown() <= 0) {
            PlayerScore score = new PlayerScore("", game.getScore());
            stage.setScene(hs);
            //timer.stop();
        }
    }

    /**
     * incremente la valeur des vies
     */
    void incrementLives() {
        game.incrementLives();
    }

    /**
     * incremente la valeur du niveau
     */
    void incrementLevel() {
        game.incrementLevel();
    }

    /**
     * incremente la valeur du score
     */
    void incrementScore() {
        game.incrementScore();
    }

    /**
     * valeur la des vies a 0
     */
    void die() {
        game.die();
    }

    /**
     * permet de tirer des balles
     */
    void fire(double x, double y) {
        game.fire(x, y);
    }
}
