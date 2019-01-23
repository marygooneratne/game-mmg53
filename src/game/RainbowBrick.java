package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RainbowBrick extends Brick{

    private static final int HITS = 1;

        public RainbowBrick(){
            super();
            var brickImage = new Image(this.getClass().getClassLoader().getResourceAsStream("rainbowBrick.gif"));
            var brickImageView = new ImageView(brickImage);
            this.setBrickImage(brickImageView);
            this.setHitsLeft(HITS);

        }
    }
