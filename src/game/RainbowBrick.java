package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/** Type and extension of Brick class. Rainbow brick is a brick placed
 * in a random location on the brick grid at configuration. When hit ,
 * it turns the ball rainbow until a life is lost or new level begins.
 */
public class RainbowBrick extends Brick{
    private static final int HITS = 1;
    private static final String RAINBOW_BRICK_IMAGE = "rainbowBrick.gif";

    /** Initializes RainbowBrick() to be a Node with the correct Image and sets hits
     * to one.
     */
        public RainbowBrick(){
            super();
            var brickImage = new Image(this.getClass().getClassLoader().getResourceAsStream(RAINBOW_BRICK_IMAGE));
            var brickImageView = new ImageView(brickImage);
            this.setBrickImage(brickImageView);
            this.setHitsLeft(HITS);

        }
    }
