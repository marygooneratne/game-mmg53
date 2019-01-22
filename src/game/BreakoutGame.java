package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;

public class BreakoutGame extends Application {
    public static final String TITLE = "Breakout";
    public static final int SIZE = 400;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.BLACK;

    public static final int LEVEL_ONE = 1;
    public static final int LEVEL_TWO = 2;
    public static final int LEVEL_THREE = 3;

    public static final String SPLASH_SCREEN_NAME = "Splash Screen";
    public static final String LEVEL_ONE_NAME = "Level 1";
    public static final String LEVEL_TWO_NAME = "Level 2";
    public static final String LEVEL_THREE_NAME = "Level 3";


    private Scene myScene;
    private Ball ball;
    private Paddle paddle;
    private BrickWall brickWall;
    private ArrayList<Brick> bricksLeft;
    @Override
    public void start(Stage stage) {
        var sceneControl = new SceneController(stage);

        var level1 = setupLevel(LEVEL_ONE, SIZE, SIZE, BACKGROUND);
        var level2 = setupLevel(LEVEL_TWO, SIZE, SIZE, BACKGROUND);
        var level3 = setupLevel(LEVEL_THREE, SIZE, SIZE, BACKGROUND);
        var splashScreen = new SplashScreen(SIZE, SIZE, sceneControl);

        sceneControl.newScene(SPLASH_SCREEN_NAME, splashScreen.getSplashScreen());
        sceneControl.newScene(LEVEL_ONE_NAME, level1);
        sceneControl.newScene(LEVEL_TWO_NAME, level2);
        sceneControl.newScene(LEVEL_THREE_NAME, level3);
        sceneControl.changeScene(SPLASH_SCREEN_NAME);
        stage.setTitle(TITLE);
        stage.show();

        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private Scene setupLevel(int level, int width, int height, Paint background){
        var root = new Group();
        var scene = new Scene(root, width, height, background);
        paddle = new Paddle(SIZE);
        ball = new Ball(paddle);
        if(level == 3){
            brickWall = new BrickWall(BrickWall.LEVEL_THREE_STRING);
        }
        else if(level == 2){
            brickWall = new BrickWall(BrickWall.LEVEL_TWO_STRING);
        }
        else{
            brickWall = new BrickWall(BrickWall.LEVEL_ONE_STRING);
        }
        bricksLeft = brickWall.getBrickList();
        root.getChildren().add(ball.getBallImage());
        root.getChildren().add(paddle.getPaddleImage());

        for(Brick thisBrick: this.bricksLeft){
            root.getChildren().add(thisBrick.getBrickImage());
        }

        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;
    }

    private void step(double elapsedTime) {
        ball.step(elapsedTime, paddle, SIZE);
        for(Brick thisBrick: this.bricksLeft){
            if(ball.hitsBrick(thisBrick)){
                thisBrick.hit();
                if(thisBrick.getIfGone()){
                    bricksLeft.remove(thisBrick);
                }
            }
        }
    }

    private void handleKeyInput(KeyCode code) {
        paddle.handleKeyInput(code);
        ball.handleKeyInput(code);

    }

    public static void main(String[] args) {launch(args);}

}