package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SlowdownBrick extends Brick{

    private static final int HITS = 1;

    public SlowdownBrick(){
        super();
        var brickImage = new Image(this.getClass().getClassLoader().getResourceAsStream("blueBrick.gif"));
        var brickImageView = new ImageView(brickImage);
        this.setBrickImage(brickImageView);
        this.setHitsLeft(HITS);

    }
}
