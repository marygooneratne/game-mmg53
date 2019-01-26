package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/** Type and extension of Brick class. Strong brick is a brick placed
 * in a set location on the brick grid at configuration. When hit twice,
 * it is destructed.
 */
public class StrongBrick extends Brick {
    private static final int HITS = 2;
    private static final String STRONG_BRICK_IMAGE = "strongBrick.gif";
    private static final String HIT_STRONG_BRICK_IMAGE = "hitStrongBrick.gif";

    /**Initializes StrongBrick() by setting its image and setting its initial hits to 2
     *
     */
    public StrongBrick(){
        super();
        var brickImage = new Image(this.getClass().getClassLoader().getResourceAsStream(STRONG_BRICK_IMAGE));
        var brickImageView = new ImageView(brickImage);
        this.setBrickImage(brickImageView);
        this.setHitsLeft(HITS);

    }

    /**Overrides Brick() hit class to show a cracked brick when hit once and not
     * destruct until hit twice
     *
     */
    @Override()
    public void hit() {
        this.setHitsLeft(this.getHitsLeft() - 1);
        if (this.getHitsLeft() == 1) {
            var newBrickImage = new Image(this.getClass().getClassLoader().getResourceAsStream(HIT_STRONG_BRICK_IMAGE));
            this.getBrickImage().setImage(newBrickImage);
        }
        else if(this.getHitsLeft() == 0){
            this.getBrickImage().setImage(null);
            this.setIfGone(true);
        }
    }
}
