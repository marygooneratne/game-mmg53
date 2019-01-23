package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SlowdownBrick extends Brick{
    private static final int HITS = 1;
    private static final String SLOWDOWN_BRICK_IMAGE = "slowdownBrick.gif";

    public SlowdownBrick(){
        super();
        var brickImage = new Image(this.getClass().getClassLoader().getResourceAsStream(SLOWDOWN_BRICK_IMAGE));
        var brickImageView = new ImageView(brickImage);
        this.setBrickImage(brickImageView);
        this.setHitsLeft(HITS);

    }
}
