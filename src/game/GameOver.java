package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** CODE WRITTEN BY: MARY GOONERATNE (@mmg53)
 * GameOver() is a object definition to define a scene to be displayed at the loss of
 * Breakout game
 *
 */
public class GameOver{
    public static final int SIZE = 400;
    public static final String GAME_OVER_IMAGE = "gameOver.gif";

    private Scene scene;
    private ImageView image;

    /** Initializes class GameOver that sets up scene to show gameOver.gif after game loss
     *
     * @param width int screen width
     * @param height int screen height
     * @param sceneControl Controller from SceneController class that scene is to be added to
     */
    public GameOver(int width, int height, SceneController sceneControl) {
        var root = new Group();
        this.scene = new Scene(root, width, height);
        this.setImage();
        root.getChildren().add(this.getImage());
    }

    private void setImage(){
        var getSplashScreenImage = new Image(this.getClass().getClassLoader().getResourceAsStream(GAME_OVER_IMAGE));
        this.image = new ImageView(getSplashScreenImage);
        this.image.setPreserveRatio(true);
        this.image.setFitWidth(SIZE);
    }

    /** Getter method that returns ImageView of game over scene
     *
     * @return ImageView of Game over scene
     */
    public ImageView getImage(){
        return this.image;
    }

    /** Getter method that returns JavaFX scene object of game over scene
     *
     * @return Scene of game over
     */
    public Scene getScene(){
        return this.scene;
    }

}