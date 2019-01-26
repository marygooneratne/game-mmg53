package game;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;

/** CODE WRITTEN BY: MARY GOONERATNE (@mmg53)
 * LevelInfo is an object definition class used to create the Node displayed at the
 * bottom of level screens displaying relevant info about the current game state.
 * It is created automatically upon game configuration.
 */

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

    /** Initializes class responsible for score bar at bottom of level. Private variables include current score, level,
     * lives left, corresponding text boxes, and HBox display object.
     *
     * @param score current score
     * @param level current level
     * @param lives current lives
     */
    public LevelInfo(int score, int level, int lives){
        this.score = score;
        this.level = level;
        this.lives = lives;
        scoreText = new Text();
        levelText = new Text();
        livesText = new Text();
        this.makeDisplay();
    }

    /** Changes score and updates score text
     *
     * @param score new score
     */
    public void changeScore(int score){
        this.score = score;
        scoreText.setText("SCORE: " + this.score);
    }

    /** Changes level and updates level text
     *
     * @param level new level
     */
    public void changeLevel(int level){
        this.level = level;
        levelText.setText("LEVEL: " + this.level);
    }

    /** Changes lives and update lives text
     *
     * @param lives new lives
     */
    public void changeLives(int lives){
        this.lives = lives;
        livesText.setText("LIVES: " + this.lives);
    }

    /** Gets JavaFX Node HBox display to display on level scene
     *
     * @return HBox display
     */

    public HBox getDisplay(){
        return this.display;
    }

    /** Updates all variables of scorebar with setter methods
     *
     * @param score new score
     * @param level new level
     * @param lives new lives
     */
    public void update(int score, int level, int lives){
        this.changeLives(lives);
        this.changeScore(score);
        this.changeLevel(level);
    }

    /** Creates HBox to use as score bar, setting size alignment, styling, and text children.
     *
     */
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
