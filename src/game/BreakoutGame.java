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
    public static final String TITLE = "Example JavaFX";
    public static final int SIZE = 400;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.BLACK;


    private Scene myScene;
    private Ball ball;
    private Paddle paddle;
    private BrickWall brickWall;
    private ArrayList<Brick> bricksLeft;
    @Override
    public void start(Stage stage) {
        myScene = setupLevel1(SIZE, SIZE, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();

        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private Scene setupLevel1(int width, int height, Paint background){
        var root = new Group();
        var scene = new Scene(root, width, height, background);
        paddle = new Paddle(SIZE);
        ball = new Ball(paddle);
        brickWall = new BrickWall(BrickWall.LEVEL_THREE_STRING);
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