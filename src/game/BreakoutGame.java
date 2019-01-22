package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
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
    public static final String GAME_OVER_SCENE_NAME = "Game Over";
    public static final String YOU_WIN_SCENE_NAME = "You win";
    public static final String LEVEL_ONE_NAME = "Level 1";
    public static final String LEVEL_TWO_NAME = "Level 2";
    public static final String LEVEL_THREE_NAME = "Level 3";


    private BrickWall brickWall;
    private ArrayList<Brick> bricksLeft;
    private Scene currentScene;
    private int score;
    private int level;
    private LevelInfo levelInfo;
    private SceneController sceneControl;
    private String sceneName;
    private Ball ball;
    private Paddle paddle;

    @Override
    public void start(Stage stage) {
        this.score = 0;
        this.level = 0;
        this.paddle = new Paddle(SIZE);
        this.ball = new Ball(paddle);
        this.sceneControl = new SceneController(stage);

        this.splashScreen();

        stage.setTitle(TITLE);
        stage.show();

        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }


    private Scene makeLevel(int level, int size, Paint background){
        var root = new Group();
        var gameScene = new Scene(root, size, size , background);
        this.paddle = new Paddle(SIZE);
        this.ball = new Ball(paddle);
        this.levelInfo = new LevelInfo(this.score, this.level, this.ball.getLives());


        if(level == 3){
            this.brickWall = new BrickWall(BrickWall.LEVEL_THREE_STRING);
        }
        else if(level == 2){
            this.brickWall = new BrickWall(BrickWall.LEVEL_TWO_STRING);
        }
        else{
            brickWall = new BrickWall(BrickWall.LEVEL_ONE_STRING);
        }
        bricksLeft = brickWall.getBrickList();
        root.getChildren().add(ball.getBallImage());
        root.getChildren().add(paddle.getPaddleImage());
        root.getChildren().add(levelInfo.getDisplay());

        for(Brick thisBrick: this.bricksLeft){
            root.getChildren().add(thisBrick.getBrickImage());
        }
        return gameScene;
    }
    public void levelOne(){
        var level1 = this.makeLevel(LEVEL_ONE, SIZE, BACKGROUND);
        sceneControl.newScene(LEVEL_ONE_NAME, level1);
        level1.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        this.sceneControl.changeScene(LEVEL_ONE_NAME);
        this.currentScene = level1;
        this.sceneName = LEVEL_ONE_NAME;
    }

    public void levelTwo(){
        var level2 = this.makeLevel(LEVEL_TWO, SIZE, BACKGROUND);
        sceneControl.newScene(LEVEL_TWO_NAME, level2);
        level2.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        this.sceneControl.changeScene(LEVEL_TWO_NAME);
        this.currentScene = level2;
        this.sceneName = LEVEL_TWO_NAME;
    }

   public void levelThree(){
        var level3 = this.makeLevel(LEVEL_THREE, SIZE, BACKGROUND);
        sceneControl.newScene(LEVEL_THREE_NAME, level3);
        level3.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        this.sceneControl.changeScene(LEVEL_THREE_NAME);
        this.currentScene = level3;
        this.sceneName = LEVEL_THREE_NAME;
    }

    public void gameOver() {
        var gameOver = new GameOver(SIZE, SIZE, this.sceneControl);
        sceneControl.newScene(GAME_OVER_SCENE_NAME, gameOver.getScene());
        gameOver.getScene().setOnKeyPressed(e -> handleOtherInput(e.getCode()));
        this.sceneControl.changeScene(GAME_OVER_SCENE_NAME);
        this.currentScene = gameOver.getScene();
        this.sceneName = GAME_OVER_SCENE_NAME;
    }

    public void youWin() {
        var youWin = new YouWin(SIZE, SIZE, this.sceneControl);
        sceneControl.newScene(YOU_WIN_SCENE_NAME, youWin.getScene());
        youWin.getScene().setOnKeyPressed(e -> handleOtherInput(e.getCode()));
        this.sceneControl.changeScene(YOU_WIN_SCENE_NAME);
        this.currentScene = youWin.getScene();
        this.sceneName = YOU_WIN_SCENE_NAME;
    }

    public void splashScreen(){
        var splashScreen = new SplashScreen(SIZE, SIZE, this.sceneControl);
        sceneControl.newScene(SPLASH_SCREEN_NAME, splashScreen.getSplashScreen());
        splashScreen.getSplashScreen().setOnKeyPressed(e -> handleOtherInput(e.getCode()));
        this.sceneControl.changeScene(SPLASH_SCREEN_NAME);
        this.currentScene = splashScreen.getSplashScreen();
        this.sceneName = SPLASH_SCREEN_NAME;
    }


    public void handleKeyInput(KeyCode code) {
        this.paddle.handleKeyInput(code);
        this.ball.handleKeyInput(code);
        if(code == KeyCode.ENTER){
            if(this.sceneName.equals(LEVEL_ONE_NAME)){
                this.levelTwo();
            }
            else if(this.sceneName.equals(LEVEL_TWO_NAME)){
                this.levelThree();
            }
            else if(this.sceneName.equals(LEVEL_THREE_NAME)){
                this.youWin();
            }
        }
    }

    public void handleOtherInput(KeyCode code) {
        if(code == KeyCode.SPACE){
            this.levelOne();
        }
        if(code == KeyCode.DIGIT1){
            this.levelOne();
        }
    }


    private void step(double elapsedTime) {
        if(this.sceneName.equals(LEVEL_ONE_NAME) || this.sceneName.equals(LEVEL_TWO_NAME) || this.sceneName.equals(LEVEL_THREE_NAME)) {
            ball.step(elapsedTime, paddle, SIZE);

            for (Brick thisBrick : this.bricksLeft) {
                if (ball.hitsBrick(thisBrick)) {
                    thisBrick.hit();
                    this.score += 5;
                    if (thisBrick.getIfGone()) {
                        bricksLeft.remove(thisBrick);
                        this.score += 10;
                    }
                }
            }
        }

    }

    public static void main(String[] args) {launch(args);}

}