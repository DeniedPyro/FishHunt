import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
import java.util.ArrayList;

public class FishHunt extends Application {
    /**
     * @author Fahirah Diarra 20034025
     * @author Hamdi Ghannem 20151932
     */
    public static final int WIDTH = 640, HEIGHT = 480;


    public static void statusView(){

        VBox main = new VBox(8);

        //
        Text killedFishCount = new Text("");


        //Initializing lives view
        HBox lives = new HBox(8);
        Image fishLife = new Image("Image/fish/00.png");
        ImageView fishLife1 = new ImageView(fishLife);
        ImageView fishLife2 = new ImageView(fishLife);
        ImageView fishLife3 = new ImageView(fishLife);
        lives.getChildren().addAll(fishLife1,fishLife2,fishLife3);

        Text LevelCount = new Text("");

    }

    public static void iniMainMenu(VBox vBox,Button gameButton, Button scoreButton){
        vBox.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        Image logo = new Image("/Image/logo.png");
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(538 * 4/5);
        logoView.setFitHeight(367 * 4/5);
        vBox.getChildren().addAll(logoView,gameButton,scoreButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(40,50,50,50));
    }
    public static void iniHighScore(VBox vBox, Text titleHighScore, ListView<String> listScores, Button menuButton){
        titleHighScore.setFont(Font.font("serif", 25));
        vBox.getChildren().addAll(titleHighScore,listScores,menuButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(20,30,20,30));
    }

    public static void iniGamePane(Pane pane,ImageView imageView,VBox gameStatus){
        pane.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        pane.getChildren().addAll(imageView,gameStatus);
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

        Pane gamePane = new Pane();
        VBox highScoreVBox = new VBox();
        VBox mainMenu = new VBox();

        Scene menu = new Scene(mainMenu, WIDTH, HEIGHT);

        Scene highScore = new Scene(highScoreVBox, WIDTH, HEIGHT);
        Scene game = new Scene(gamePane, WIDTH, HEIGHT);

        //Initialisation de la scene Game
        Image target = new Image("/Image/cible.png");
        ImageView targetView = new ImageView(target);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext context = canvas.getGraphicsContext2D();
        gamePane.getChildren().add(canvas);

        ///Game status view containing the nb of killed fish
        //etc.
        VBox gameStatusView = new VBox(8);
        Text killedFishCount = new Text("1");
        //Initializing lives view
        HBox lives = new HBox(8);
        Image fishLife = new Image("Image/fish/00.png");
        ImageView fishLife1 = new ImageView(fishLife);
        fishLife1.setFitHeight(20);
        fishLife1.setFitWidth(20);
        ImageView fishLife2 = new ImageView(fishLife);
        fishLife2.setFitHeight(20);
        fishLife2.setFitWidth(20);
        ImageView fishLife3 = new ImageView(fishLife);
        fishLife3.setFitHeight(20);
        fishLife3.setFitWidth(20);
        lives.getChildren().addAll(fishLife1,fishLife2,fishLife3);
        Text levelCount = new Text("Level 1");
        gameStatusView.getChildren().addAll(killedFishCount,lives ,levelCount);
        gameStatusView.setAlignment(Pos.CENTER);

        iniGamePane(gamePane, targetView,gameStatusView);

        //Initialisation de la scene HighScore
        Text titreHighScore = new Text("Meilleurs scores");
        HighScoreManager scoreManager = new HighScoreManager();
        ArrayList<String> scores = scoreManager.getScores();
        ListView<String> scoresView = new ListView<String>();
        scoresView.getItems().setAll(scores);
        Button menuButton = new Button("Menu");
        menuButton.setOnMouseClicked(mouseEvent -> {
            primaryStage.setScene(menu);
        });
        iniHighScore(highScoreVBox,titreHighScore,scoresView,menuButton);

        //creation du controller
        Controller controller = new Controller();
        game.setOnMouseMoved((event) -> {
            double w = targetView.getBoundsInLocal().getWidth();
            double h = targetView.getBoundsInLocal().getHeight();
            double x = event.getX() - w / 2;
            double y = event.getY() - h / 2;
            targetView.setX(x);
            targetView.setY(y);
        });

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
                controller.fire(mouseEvent.getX(),mouseEvent.getY());
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
                //controller.updateLevelText();
                controller.update(deltaTime);
                controller.draw(context);
                lastTime = now;
            }
        };

        //Initialisation de la scene MainMenu

        Button gameButton = new Button("Nouvelle Partie!");
        Button scoreButton = new Button("Meilleurs scores");

        gameButton.setOnMouseClicked(mouseEvent -> {
            primaryStage.setScene(game);
            timer.stop();
            timer.start();
        });
        scoreButton.setOnMouseClicked(mouseEvent -> {
            primaryStage.setScene(highScore);
        });

        iniMainMenu(mainMenu, gameButton, scoreButton);

        primaryStage.setScene(menu);
        primaryStage.setTitle("Fish Hunt");
        primaryStage.show();
        primaryStage.setResizable(false);
        //primaryStage.getIcons().add(new Image("/images/jellyfish1.png"));

    }
}