package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BombBrick extends Brick{

    private static final int HITS = 1;

    public BombBrick(){
        super();
        var brickImage = new Image(this.getClass().getClassLoader().getResourceAsStream("bombBrick.gif"));
        var brickImageView = new ImageView(brickImage);
        this.setBrickImage(brickImageView);
        this.setHitsLeft(HITS);

    }
}
