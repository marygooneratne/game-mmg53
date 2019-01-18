package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BreakoutGame extends Application {
    public static final String TITLE = "Example JavaFX";
    public static final int SIZE = 400;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.BLACK;
    public static final Paint HIGHLIGHT = Color.OLIVEDRAB;
    public static final String BALL_IMAGE = "ball.gif";
    public static final int BALL_SPEED = 60;
    public static final int PADDLE_SPEED = 10;
    public static final String BRICK_IMAGE = "blueBrick.gif";
    public static final String PADDLE_IMAGE = "paddle.gif";


    private Scene myScene;
    private ImageView myBall;
    private ImageView brick;
    private ImageView myPaddle;

    @Override
    public void start(Stage stage) {
        myScene = setupGame(SIZE, SIZE, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();

        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private Scene setupGame(int width, int height, Paint background){
        var root = new Group();
        var scene = new Scene(root, width, height, background);
        var ballImage = new Image(this.getClass().getClassLoader().getResourceAsStream(BALL_IMAGE));
        var brickImage = new Image(this.getClass().getClassLoader().getResourceAsStream(BRICK_IMAGE));
        var paddleImage = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        myBall = new ImageView(ballImage);
        myBall.setPreserveRatio(true);
        myBall.setFitWidth(width/20);
        myBall.setX(width/2 - myBall.getBoundsInLocal().getWidth()/2);
        myBall.setY(height/2 - myBall.getBoundsInLocal().getHeight()/2);
        brick = new ImageView(brickImage);
        brick.setPreserveRatio(true);
        brick.setFitWidth(width/10);
        brick.setX(0);
        brick.setY(0);
        myPaddle = new ImageView(paddleImage);
        myPaddle.setPreserveRatio(true);
        myPaddle.setFitWidth(width/10);
        myPaddle.setX(0);
        myPaddle.setY(height - myPaddle.getBoundsInLocal().getHeight());
        root.getChildren().add(myBall);
        root.getChildren().add(brick);
        root.getChildren().add(myPaddle);
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;

    }

    private void step(double elapsedTime) {
        if(myBall.getBoundsInLocal().intersects(myPaddle.getBoundsInLocal())){

        }
        myBall.setY(myBall.getY() + BALL_SPEED * elapsedTime);
    }

    private void handleKeyInput(KeyCode code) {
        if(code == KeyCode.RIGHT) {
            myPaddle.setX(myPaddle.getX() + PADDLE_SPEED);
        }
        if(code == KeyCode.LEFT) {
            myPaddle.setX(myPaddle.getX() - PADDLE_SPEED);
        }

    }

    public static void main(String[] args) {launch(args);}

}