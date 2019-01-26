package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** CODE WRITTEN BY: MARY GOONERATNE (@mmg53)
 * Type and extension of Brick class. Bomb brick is a power-up brick placed
 * in a random location on the brick grid. When hit once, it and its right and left neighbors
 * (if they exist) are destructed.
 */

public class BombBrick extends Brick{
    private static final String BOMB_BRICK_IMAGE = "bombBrick.gif";
    private static final int HITS = 1;

    /** Initializes BombBrick by setting image and hitsLeft to 1
     *
     */
    public BombBrick(){
        super();
        var brickImage = new Image(this.getClass().getClassLoader().getResourceAsStream(BOMB_BRICK_IMAGE));
        var brickImageView = new ImageView(brickImage);
        this.setBrickImage(brickImageView);
        this.setHitsLeft(HITS);

    }
}
