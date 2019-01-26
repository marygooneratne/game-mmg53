package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** CODE WRITTEN BY: MARY GOONERATNE (@mmg53)
 * Type and extension of Brick class. Slowdown brick is a power-up brick placed
 * in a random location on the brick grid. When hit once, it is destructed and slows
 * the speed of the ball.
 */
public class SlowdownBrick extends Brick{
    private static final int HITS = 1;
    private static final String SLOWDOWN_BRICK_IMAGE = "slowdownBrick.gif";

    /** Initializes SlowdownBrick() class by setting brick image and hits to 1
     *
     */
    public SlowdownBrick(){
        super();
        var brickImage = new Image(this.getClass().getClassLoader().getResourceAsStream(SLOWDOWN_BRICK_IMAGE));
        var brickImageView = new ImageView(brickImage);
        this.setBrickImage(brickImageView);
        this.setHitsLeft(HITS);

    }
}
