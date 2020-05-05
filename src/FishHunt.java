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
    public static final int WIDTH = 350, HEIGHT = 480;

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

        //Creation du texte du mode Debug
        Text position = new Text("Position =  ");
        position.setFill(Color.rgb(255, 255, 255));
        position.setFont(Font.font(10));
        Text v = new Text("v =  ");
        v.setFill(Color.rgb(255, 255, 255));
        v.setFont(Font.font(15));
        Text a = new Text("a =  ");
        a.setFont(Font.font(15));
        a.setFill(Color.rgb(255, 255, 255));
        position.setFont(Font.font(15));
        Text ground = new Text("Touche le sol : ");
        ground.setFont(Font.font(15));
        ground.setFill(Color.rgb(255, 255, 255));
        VBox vbox = new VBox(position, v, a, ground);
        v.setX(166);
        //creation du texte de la distance parcourue
        Text distance = new Text("0 m");
        distance.setFill(Color.rgb(255, 255, 255));
        distance.setX((WIDTH / 2) - 30);
        distance.setFont(Font.font(30));
        distance.setY(40);
        VBox vbox2 = new VBox(distance);
        vbox2.setAlignment(Pos.CENTER);
        root.getChildren().addAll(canvas, distance, vbox, vbox2);

        GraphicsContext context = canvas.getGraphicsContext2D();
        //creation du controller
        Controller controller = new Controller();
        scene.setOnKeyPressed((value) -> {

            if (value.getCode() == KeyCode.ESCAPE) {
                Platform.exit();
            }

            if (value.getCode() == KeyCode.H) {
                Controller.incrementLevel();
            }

            if (value.getCode() == KeyCode.J) {
                Controller.incrementScore();
            }

            if (value.getCode() == KeyCode.K) {
                Controller.incrementLives();
            }

            if (value.getCode() == KeyCode.L) {
                Controller.die();
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
                controller.updateDistance(distance);

                //Debug trigger
                if (controller.getDebug()) {
                    ;
                } else if (!controller.getDebug() && vbox.isVisible()) {
                    vbox.setVisible(false);
                }

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