package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NormalBrick extends Brick{
    private static final String NORMAL_BRICK_IMAGE = "normalBrick.gif";
    private static final int HITS = 1;

    public NormalBrick(){
        super();
        var brickImage = new Image(this.getClass().getClassLoader().getResourceAsStream(NORMAL_BRICK_IMAGE));
        var brickImageView = new ImageView(brickImage);
        this.setBrickImage(brickImageView);
        this.setHitsLeft(HITS);

    }
}
