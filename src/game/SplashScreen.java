package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class SplashScreen {
    public static final int SIZE = 400;
    public static final String SPLASH_SCREEN_IMAGE = "splashScreen.gif";

    private Scene splashScreen;
    private ImageView splashImage;
    private SceneController sceneControl;

    public SplashScreen(int width, int height, SceneController sceneControl) {
        var root = new Group();
        this.splashScreen = new Scene(root, width, height);
        this.setSplashImage();
        root.getChildren().add(this.getSplashImage());
        this.splashScreen.setOnKeyPressed(e -> this.handleKeyInput(e.getCode()));
        this.sceneControl = sceneControl;
    }

    public void setSplashImage(){
        var getSplashScreenImage = new Image(this.getClass().getClassLoader().getResourceAsStream(SPLASH_SCREEN_IMAGE));
        this.splashImage = new ImageView(getSplashScreenImage);
        this.splashImage.setPreserveRatio(true);
        this.splashImage.setFitWidth(SIZE);
    }

    public ImageView getSplashImage(){
        return this.splashImage;
    }

    public Scene getSplashScreen(){
        return this.splashScreen;
    }

    public void handleKeyInput(KeyCode code){
        if(code == KeyCode.SPACE){
            this.sceneControl.changeScene("1");
        }
    }

}
