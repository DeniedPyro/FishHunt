import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class FishHunt extends Application {
    /**
     * @author Fahirah Diarra 20034025
     * @author Hamdi Ghannem 20151932
     */
    public static final int WIDTH = 640, HEIGHT = 480;

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }


    /**
     * creation du stage
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);

        GraphicsContext context = canvas.getGraphicsContext2D();
        //creation du controller
        Controller controller = new Controller();
        scene.setOnKeyPressed((value) -> {

            if (value.getCode() == KeyCode.ESCAPE) {
                Platform.exit();
            }

            if (value.getCode() == KeyCode.H) {
                controller.incrementLevel();
            }

            if (value.getCode() == KeyCode.J) {
                controller.incrementScore();
            }

            if (value.getCode() == KeyCode.K) {
                controller.incrementLives();
            }

            if (value.getCode() == KeyCode.L) {
                controller.die();
            }
        });
        scene.setOnKeyReleased((value) -> {

        });

        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = 0;
            private final double maxDt = 0.01;

            @Override
            public void handle(long now) {
                if (lastTime == 0) {
                    lastTime = now;
                    return;
                }

                double deltaTime = (now - lastTime) * 1e-9;
                while (deltaTime > maxDt) {
                    controller.update(maxDt);
                    deltaTime -= maxDt;
                }
                controller.update(deltaTime);
                controller.draw(context);
                lastTime = now;
            }
        };
        timer.start();
        //settings de la scene
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fish Hunt");
        primaryStage.show();
        primaryStage.setResizable(false);
        //primaryStage.getIcons().add(new Image("/images/jellyfish1.png"));
    }
}