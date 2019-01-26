package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/** CODE WRITTEN BY: MARY GOONERATNE (@mmg53)
 * Type and extension of Brick class. Normal brick is a brick placed
 * in a set location on the brick grid at configuration. When hit, it
 * is simply destructed
 */
public class NormalBrick extends Brick{
    private static final String NORMAL_BRICK_IMAGE = "normalBrick.gif";
    private static final int HITS = 1;

    /** Initializes NormalBrick() to be a Node with the correct Image and sets hits
     * to one.
     */
    public NormalBrick(){
        super();
        var brickImage = new Image(this.getClass().getClassLoader().getResourceAsStream(NORMAL_BRICK_IMAGE));
        var brickImageView = new ImageView(brickImage);
        this.setBrickImage(brickImageView);
        this.setHitsLeft(HITS);

    }
}
