import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
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

    public static void iniMainMenu(VBox vBox,Button gameButton, Button scoreButton){
        vBox.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        Image logo = new Image("/Image/logo.png");
        ImageView logoView = new ImageView(logo);
        vBox.getChildren().add(logoView);
        vBox.getChildren().add(gameButton);
        vBox.getChildren().add(scoreButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
    }
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
        VBox bruh = new VBox();
        VBox mainMenu = new VBox();

        Scene menu = new Scene(mainMenu, WIDTH, HEIGHT);

        Scene highScore = new Scene(bruh, WIDTH, HEIGHT);

        Scene game = new Scene(root, WIDTH, HEIGHT);


        Button gameButton = new Button("Nouvelle Partie!");
        Button scoreButton = new Button("Meilleurs scores");

        gameButton.setOnMouseClicked(mouseEvent -> {
            primaryStage.setScene(game);
        });
        scoreButton.setOnMouseClicked(mouseEvent -> {
            primaryStage.setScene(highScore);
        });

        iniMainMenu(mainMenu, gameButton, scoreButton);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);

        GraphicsContext context = canvas.getGraphicsContext2D();
        //creation du controller
        Controller controller = new Controller();
        game.setOnKeyPressed((value) -> {

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
        game.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY){
                System.out.println("bruh");
            }
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
        primaryStage.setScene(menu);
        primaryStage.setTitle("Fish Hunt");
        primaryStage.show();
        primaryStage.setResizable(false);
        //primaryStage.getIcons().add(new Image("/images/jellyfish1.png"));

    }
}