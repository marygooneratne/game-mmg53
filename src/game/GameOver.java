package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameOver{
    public static final int SIZE = 400;
    public static final String GAME_OVER_IMAGE = "gameOver.gif";

    private Scene scene;
    private ImageView image;

    public GameOver(int width, int height, SceneController sceneControl) {
        var root = new Group();
        this.scene = new Scene(root, width, height);
        this.setImage();
        root.getChildren().add(this.getImage());
    }

    public void setImage(){
        var getSplashScreenImage = new Image(this.getClass().getClassLoader().getResourceAsStream(GAME_OVER_IMAGE));
        this.image = new ImageView(getSplashScreenImage);
        this.image.setPreserveRatio(true);
        this.image.setFitWidth(SIZE);
    }

    public ImageView getImage(){
        return this.image;
    }

    public Scene getScene(){
        return this.scene;
    }

}