package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StrongBrick extends Brick {
    private static final int HITS = 2;
    private static final String STRONG_BRICK_IMAGE = "strongBrick.gif";
    private static final String HIT_STRONG_BRICK_IMAGE = "hitStrongBrick.gif";

    public StrongBrick(){
        super();
        var brickImage = new Image(this.getClass().getClassLoader().getResourceAsStream(STRONG_BRICK_IMAGE));
        var brickImageView = new ImageView(brickImage);
        this.setBrickImage(brickImageView);
        this.setHitsLeft(HITS);

    }

    @Override()
    public void hit() {
        this.setHitsLeft(this.getHitsLeft() - 1);
        if (this.getHitsLeft() == 1) {
            var newBrickImage = new Image(this.getClass().getClassLoader().getResourceAsStream(HIT_STRONG_BRICK_IMAGE));
            this.getBrickImage().setImage(newBrickImage);
        }
        else if(this.getHitsLeft() == 0){
            this.getBrickImage().setImage(null);
            this.setIfGone(true);
        }
    }
}
