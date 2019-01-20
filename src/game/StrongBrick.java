package game;

import game.Brick;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StrongBrick extends Brick {

    private static final int HITS = 2;

    public StrongBrick(){
        super();
        var brickImage = new Image(this.getClass().getClassLoader().getResourceAsStream("blueBrick.gif"));
        var brickImageView = new ImageView(brickImage);
        this.setBrickImage(brickImageView);
        this.setHitsLeft(HITS);

    }
}
