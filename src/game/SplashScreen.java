package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

/** SplashScreen is an object definition used to define the scene shown at the very
 * beginning of the game. The scene contains all the rules and keys in an image.
 */
public class SplashScreen {
    public static final int SIZE = 400;
    public static final String SPLASH_SCREEN_IMAGE = "splashScreen.gif";

    private Scene splashScreen;
    private ImageView splashImage;

    /** Initializes the SplashScreen() object to set up the scene with the splash image
     * containing all the rules and adding that child to the Scene variable
     * @param width Scene width
     * @param height Scene height
     * @param sceneControl SceneController class that splash screen will be added to
     */
    public SplashScreen(int width, int height, SceneController sceneControl) {
        var root = new Group();
        this.splashScreen = new Scene(root, width, height);
        this.setSplashImage();
        root.getChildren().add(this.getSplashImage());
    }

    private void setSplashImage(){
        var getSplashScreenImage = new Image(this.getClass().getClassLoader().getResourceAsStream(SPLASH_SCREEN_IMAGE));
        this.splashImage = new ImageView(getSplashScreenImage);
        this.splashImage.setPreserveRatio(true);
        this.splashImage.setFitWidth(SIZE);
    }

    private ImageView getSplashImage(){
        return this.splashImage;
    }

    /** Returns scene object of SplashScreen() class
     *
     * @return Scene of splashScreen()
     */

    public Scene getSplashScreen(){
        return this.splashScreen;
    }

}
