package game;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;

public class LevelInfo {
    public static final int SIZE = 400;
    public static final int HEIGHT = 20;
    public static final int SPACING = 30;

    private int score;
    private int level;
    private int lives;

    private Text scoreText;
    private Text levelText;
    private Text livesText;

    private HBox display;

    public LevelInfo(int score, int level, int lives){
        this.score = score;
        this.level = level;
        this.lives = lives;
        scoreText = new Text();
        levelText = new Text();
        livesText = new Text();
        this.makeDisplay();
    }

    public void changeScore(int score){
        this.score = score;
        scoreText.setText("SCORE: " + this.score);
    }

    public void changeLevel(int level){
        this.level = level;
        levelText.setText("LEVEL: " + this.level);
    }

    public void changeLives(int lives){
        this.lives = lives;
        livesText.setText("LIVES: " + this.lives);
    }

    public int getLives(){
        return this.lives;
    }

    public int getLevel(){
        return this.level;
    }

    public int getScore(){
        return this.score;
    }

    public HBox getDisplay(){
        return this.display;
    }

    public void update(int score, int level, int lives){
        this.changeLives(lives);
        this.changeScore(score);
        this.changeLevel(level);
    }

    public void makeDisplay(){
        this.display = new HBox();
        this.display.setMaxHeight(HEIGHT);
        this.display.setMinWidth(SIZE);
        this.display.setSpacing(SPACING);
        this.display.setAlignment(Pos.BASELINE_CENTER);
        this.display.setStyle("-fx-background-color: WHITE");

        levelText.setText("LEVEL: " + this.level);
        scoreText.setText("SCORE: " + this.score);
        livesText.setText("LIVES: " + this.lives);

        this.display.getChildren().addAll(levelText, scoreText, livesText);

        this.display.setLayoutX(0);
        this.display.setLayoutY(SIZE-HEIGHT);
    }
}
