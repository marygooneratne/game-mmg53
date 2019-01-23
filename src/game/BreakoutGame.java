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
import javafx.scene.text.Text;

public class BreakoutGame extends Application {
    public static final String TITLE = "Breakout";
    public static final int SIZE = 400;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.BLACK;
    public static final int BRICK_WIDTH = 40;

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

    private boolean allLivesLevel1;
    private boolean allLivesLevel2;

    @Override
    public void start(Stage stage) {
        this.score = 0;
        this.level = 0;
        this.allLivesLevel1 = false;
        this.allLivesLevel2 = false;
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
        this.bricksLeft = brickWall.getBrickList();
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
        this.level = LEVEL_ONE;
        this.levelInfo.changeLevel(this.level);
    }

    public void levelTwo(){
        var level2 = this.makeLevel(LEVEL_TWO, SIZE, BACKGROUND);
        sceneControl.newScene(LEVEL_TWO_NAME, level2);
        level2.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        this.sceneControl.changeScene(LEVEL_TWO_NAME);
        this.currentScene = level2;
        this.sceneName = LEVEL_TWO_NAME;
        this.level = LEVEL_TWO;
        this.levelInfo.changeLevel(this.level);
    }

   public void levelThree(){
        var level3 = this.makeLevel(LEVEL_THREE, SIZE, BACKGROUND);
        sceneControl.newScene(LEVEL_THREE_NAME, level3);
        level3.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        this.sceneControl.changeScene(LEVEL_THREE_NAME);
        this.currentScene = level3;
        this.sceneName = LEVEL_THREE_NAME;
        this.level = 3;
        this.levelInfo.changeLevel(this.level);
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
        if(code == KeyCode.D) {
            if (this.sceneName.equals(LEVEL_ONE_NAME)) {
                this.levelTwo();
            }
            if (this.sceneName.equals(LEVEL_TWO_NAME)) {
                this.levelThree();
            }
            if (this.sceneName.equals(LEVEL_THREE_NAME)) {
                this.youWin();
            }
        }
        if(code == KeyCode.DIGIT1){
            this.levelOne();
        }
        if(code == KeyCode.DIGIT2){
            this.levelTwo();
        }
        if(code == KeyCode.DIGIT3){
            this.levelThree();
        }
    }

    public void handleOtherInput(KeyCode code) {
        if(code == KeyCode.SPACE || code == KeyCode.DIGIT1){
            this.levelOne();
        }
        if(code == KeyCode.DIGIT2){
            this.levelTwo();
        }
        if(code == KeyCode.DIGIT3){
            this.levelThree();
        }

    }


    private void step(double elapsedTime) {
        if(this.sceneName.equals(LEVEL_ONE_NAME) || this.sceneName.equals(LEVEL_TWO_NAME) || this.sceneName.equals(LEVEL_THREE_NAME)) {
            ball.step(elapsedTime, paddle, SIZE);
            for (int i = 0; i < this.bricksLeft.size();i++) {
                var thisBrick = bricksLeft.get(i);
                if (ball.hitsBrick(thisBrick)) {
                    thisBrick.hit();
                    if(thisBrick instanceof SlowdownBrick){
                        ball.slow();
                    }
                    if(thisBrick instanceof RainbowBrick){
                        ball.rainbow();
                    }
                    if(thisBrick instanceof BombBrick){
                        if((i+1)<bricksLeft.size() && bricksLeft.get(i+1).getBrickImage().getX() == thisBrick.getBrickImage().getX() + BRICK_WIDTH){
                            bricksLeft.get(i+1).hit();
                            i++;
                        }
                        if((i-1)>0 && bricksLeft.get(i-1).getBrickImage().getX() == thisBrick.getBrickImage().getX() - BRICK_WIDTH){
                            bricksLeft.get(i+1).hit();
                        }
                    }
                    this.score += 5;
                    if (thisBrick.getIfGone()) {
                        this.bricksLeft.remove(thisBrick);
                        this.score += 10;
                    }
                }
            }
            if(bricksLeft.size() == 0){
                if(this.sceneName.equals(LEVEL_ONE_NAME)){
                    if(ball.getLives() == 3){
                        this.allLivesLevel1 = true;
                    }
                    this.levelTwo();
                }
                else if(this.sceneName.equals(LEVEL_TWO_NAME)){
                    if(ball.getLives() == 3){
                        this.allLivesLevel2 = true;
                    }
                    this.levelThree();
                    if(this.allLivesLevel1 && this.allLivesLevel2){
                        ball.addLives(1);
                    }
                }
                else if(this.sceneName.equals(LEVEL_THREE_NAME)){
                    this.youWin();
                }
            }
            this.levelInfo.update(this.score, this.level, this.ball.getLives());
            if(ball.getLives() == 0){
                this.gameOver();
            }
        }

    }

    public static void main(String[] args) {launch(args);}

}