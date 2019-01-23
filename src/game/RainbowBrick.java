package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RainbowBrick extends Brick{
    private static final int HITS = 1;
    private static final String RAINBOW_BRICK_IMAGE = "rainbowBrick.gif";

        public RainbowBrick(){
            super();
            var brickImage = new Image(this.getClass().getClassLoader().getResourceAsStream(RAINBOW_BRICK_IMAGE));
            var brickImageView = new ImageView(brickImage);
            this.setBrickImage(brickImageView);
            this.setHitsLeft(HITS);

        }
    }
