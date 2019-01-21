package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StrongBrick extends Brick {

    private static final int HITS = 2;

    public StrongBrick(){
        super();
        var brickImage = new Image(this.getClass().getClassLoader().getResourceAsStream("strongBrick.gif"));
        var brickImageView = new ImageView(brickImage);
        this.setBrickImage(brickImageView);
        this.setHitsLeft(HITS);

    }

    @Override()
    public void hit() {
        this.setHitsLeft(this.getHitsLeft() - 1);
        if (this.getHitsLeft() == 1) {
            var newBrickImage = new Image(this.getClass().getClassLoader().getResourceAsStream("hitStrongBrick.gif"));
            this.getBrickImage().setImage(newBrickImage);
        }
        if (this.getHitsLeft() == 0) {
            this.getBrickImage().setImage(null);
            this.setIfGone(true);
        }
    }
}
