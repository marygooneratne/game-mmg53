package game;

import javafx.scene.image.ImageView;

/** The Brick class is an superclass definition. No direct calls to brick are made,
 * rather to various subclasses of Brick(). Bricks make up the brick walls that
 * the user's goal is to destroy. Bricks are stationary with locations set at the configuration
 * of BrickWall() and hold different collision properties based on brick type.
 */
public class Brick{
    public static final int BRICK_WIDTH = 40;
    public static final int BRICK_HEIGHT = 20;

    private int hitsLeft;
    private ImageView brickImage;
    private boolean ifGone;

    /** CODE WRITTEN BY: MARY GOONERATNE (@mmg53)
     * Initializes new Brick object to be used in BrickWall() class used in formation of levels. Several different kinds
     * of Bricks extend Brick(). Brick() is never called and used exclusively as a super class. Brick() has properties of
     * int hitsLeft, ImageView brickImage, and boolean ifGone.
     */
    public Brick() {
    }

    /** Returns hits that brick has left before being destroyed
     *
     * @return int hitsLeft
     */
    public int getHitsLeft(){
        return this.hitsLeft;
    }

    /** Returns ImageView of brick, dependent on brickType
     *
     * @return brickImage private instance variable
     */
    public ImageView getBrickImage(){
        return this.brickImage;
    }

    /** "Hits" a brick by decrementing hitsLeft variable and eliminating brick if 0 hits Left
     *
     */
    public void hit(){
        this.hitsLeft--;
        if(this.hitsLeft == 0){
            this.brickImage.setImage(null);
            this.ifGone = true;
        }
    }

    /** Setter method to change brickImage, useful in subclasses
     *
     * @param brickImage ImageView to change brickImage to
     */
    public void setBrickImage(ImageView brickImage) {
        this.brickImage = brickImage;
        this.brickImage.setFitWidth(BRICK_WIDTH);
        this.brickImage.setFitHeight(BRICK_HEIGHT);
    }

    /** Setter method used to change number of hits left
     *
     * @param hitsLeft int value to change this.hitsLeft to
     */
    public void setHitsLeft(int hitsLeft){
        this.hitsLeft = hitsLeft;
    }

    /** Setter method used to change x location of brick
     *
     * @param x double local x value used to place brick on parent scene
     */
    public void setX(double x){
        this.brickImage.setX(x);
    }

    /** Setter method used to change y location of brick
     *
     * @param y double local y value used to place brick on parent scene
     */
    public void setY(double y){
        this.brickImage.setY(y);
    }

    /** Setter method used to denote the "destruction" of a brick when there are no more hits left
     *
     * @param setGone boolean true if brick is destructed, false otherwise
     */
    public void setIfGone(boolean setGone){
        this.ifGone = setGone;
    }

    /** Setter method used to set both  x and y locations of brick
     *
     * @param x double local x value used to place brick on parent scene
     * @param y double local y value used to place brick on parent scene
     */
    public void setLoc(double x, double y){
        this.setX(x);
        this.setY(y);
    }

    /** Getter method used to get ImageView for Brick
     *
     * @return ImageView of current brick image
     */

    public ImageView getImage(){
        return this.brickImage;
    }
}

