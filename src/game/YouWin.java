package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/** CODE WRITTEN BY: MARY GOONERATNE (@mmg53)
 * YouWin() is a object definition to define a scene to be displayed at the victory of
 * Breakout game
 *
 */
public class YouWin{
    public static final int SIZE = 400;
    public static final String YOU_WIN_IMAGE = "youWin.gif";

    private Scene scene;
    private ImageView image;

    /** Initializes the YouWin object by creating a scene with the correct image
     *
     * @param width width of scene
     * @param height height of scene
     */
    public YouWin(int width, int height) {
        var root = new Group();
        this.scene = new Scene(root, width, height);
        this.setImage();
        root.getChildren().add(this.getImage());
    }

    private void setImage(){
        var getSplashScreenImage = new Image(this.getClass().getClassLoader().getResourceAsStream(YOU_WIN_IMAGE));
        this.image = new ImageView(getSplashScreenImage);
        this.image.setPreserveRatio(true);
        this.image.setFitWidth(SIZE);
    }

    private ImageView getImage(){
        return this.image;
    }

    /**Getter method to get scene to show on stage in SceneController
     *
     * @return Scene object YouWin class showing victory message
     */
    public Scene getScene(){
        return this.scene;
    }

}