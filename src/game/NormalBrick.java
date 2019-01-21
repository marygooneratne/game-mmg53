package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NormalBrick extends Brick{

    private static final int HITS = 1;

    public NormalBrick(){
        super();
        var brickImage = new Image(this.getClass().getClassLoader().getResourceAsStream("normalBrick.gif"));
        var brickImageView = new ImageView(brickImage);
        this.setBrickImage(brickImageView);
        this.setHitsLeft(HITS);

    }
}
