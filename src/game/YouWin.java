package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class YouWin{
    public static final int SIZE = 400;
    public static final String YOU_WIN_IMAGE = "youWin.gif";

    private Scene scene;
    private ImageView image;

    public YouWin(int width, int height) {
        var root = new Group();
        this.scene = new Scene(root, width, height);
        this.setImage();
        root.getChildren().add(this.getImage());
    }

    public void setImage(){
        var getSplashScreenImage = new Image(this.getClass().getClassLoader().getResourceAsStream(YOU_WIN_IMAGE));
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