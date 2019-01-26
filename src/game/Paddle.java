package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
/** CODE WRITTEN BY: MARY GOONERATNE (@mmg53)
 * The paddle is a object definition class called at the beginning of every new level
 * or new ball life. The paddle is controlled by arrow keys and used to bounce ball to
 * break bricks. The paddle cannot move when the game is paused.
 */
public class Paddle {
    public static final String PADDLE_IMAGE = "paddle.gif";
    public static final String FAST_PADDLE_IMAGE = "fastPaddle.gif";
    public static final String SLOW_PADDLE_IMAGE = "slowPaddle.gif";
    public static final int NORMAL_PADDLE_SIZE = 60;
    public static final int LONG_PADDLE_SIZE = 100;
    public static final int FAST_PADDLE_SPEED = 30;
    public static final int NORMAL_PADDLE_SPEED = 20;
    public static final int SLOW_PADDLE_SPEED = 10;

    private ImageView paddleImage;
    private int paddleSpeed;
    private int paddleSize;
    private boolean isLong;
    private boolean isFast;
    private boolean isSlow;
    private boolean isPaused;
    private double screenSize;

    /** Initializes paddle object. Private instance variables include paddle image,
     *  size, isLong, isFast, isSlow, isPaused, and Scene screen size.
     *
     * @param screenSize sets scene size to center paddle
     */
    public Paddle(double screenSize){
        this.screenSize = screenSize;
        this.paddleImage = new ImageView();
        this.reset(screenSize);
    }

    /** Setter method used to change paddle image, useful for cheatcodes that change paddle
     *
     */

    public void setPaddleImage(){
        var paddleGetImage = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        this.getPaddleImage().setImage(paddleGetImage);
        this.paddleImage.setPreserveRatio(true);
        this.paddleImage.setFitWidth(paddleSize);
    }

    /** Getter method that returns ImageView of paddle
     *
     * @return ImageView of paddle
     */
    public ImageView getPaddleImage() {
        return paddleImage;
    }

    /** Method used to handle key input that affects paddle, called upon in main handleKeyInput(). When unpaused,
     * right and left arrow keys move paddle. Space toggles pause, L toggles long paddle, S toggles slow paddle, and F
     * toggles fast paddle.
     * @param code KeyCode fed by main handleKeyInput() method
     */

    public void handleKeyInput(KeyCode code){
        if(!this.isPaused){
            if(code == KeyCode.RIGHT && !(this.getPaddleImage().getX() + this.paddleSpeed > this.screenSize)) {
                this.getPaddleImage().setX(this.getPaddleImage().getX() + this.paddleSpeed);
            }
            if(code == KeyCode.LEFT && !(this.getPaddleImage().getX() - this.paddleSpeed < 0)) {
                this.getPaddleImage().setX(this.getPaddleImage().getX() - this.paddleSpeed);
            }
        }
        if (code == KeyCode.SPACE) {
            this.togglePause();
        }
        if(code == KeyCode.L){
            this.makeLong();
        }
        if(code == KeyCode.S){
            this.makeSlow();
        }
        if(code == KeyCode.F){
            this.makeFast();
        }
    }

    /** Setter method used to change x location of paddle
     *
     * @param x double local x value used to place paddle on parent scene
     */
    public void setX(double x){
        this.paddleImage.setX(x);
    }

    /** Setter method used to change y location of paddle
     *
     * @param y double local y value used to place paddle on parent scene
     */
    public void setY(double y){
        this.paddleImage.setY(y);
    }

    /** Method called when cheat code for fast paddle is used. Method changes image to fast paddle and increases speed,
     * or if already set to fast paddle, resets to normal paddle.
     *
     */
    public void makeFast(){
        if(this.isFast) {
            var paddleGetImage = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
            this.paddleImage.setImage(paddleGetImage);
            this.paddleSpeed = NORMAL_PADDLE_SPEED;
            this.isFast = !this.isFast;
        }
        else{
            var paddleGetImage = new Image(this.getClass().getClassLoader().getResourceAsStream(FAST_PADDLE_IMAGE));
            this.paddleImage.setImage(paddleGetImage);
            this.paddleSpeed = FAST_PADDLE_SPEED;
            this.isFast = !this.isFast;
        }
    }

    /** Method called when cheat code for slow paddle is used. Method changes image to slow paddle and decreases speed,
     * or if already set to slow paddle, resets to normal paddle.
     *
     */
    public void makeSlow(){
        if(this.isSlow) {
            var paddleGetImage = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
            this.paddleImage.setImage(paddleGetImage);
            this.paddleSpeed = NORMAL_PADDLE_SPEED;
            this.isSlow = !this.isSlow;
        }
        else{
            var paddleGetImage = new Image(this.getClass().getClassLoader().getResourceAsStream(SLOW_PADDLE_IMAGE));
            this.paddleImage.setImage(paddleGetImage);
            this.paddleSpeed = SLOW_PADDLE_SPEED;
            this.isSlow = !this.isSlow;
        }
    }

    /** Method called when cheat code for long paddle is used. Method changes image to long paddle and increases length,
     * or if already set to long paddle, resets to normal paddle.
     *
     */
    public void makeLong(){
        this.paddleImage.setPreserveRatio(false);
        if(this.isLong) {
            this.paddleSize = NORMAL_PADDLE_SIZE;
            this.paddleImage.setFitWidth(paddleSize);
            this.isLong = !this.isLong;
        }
        else {
            this.paddleSize = LONG_PADDLE_SIZE;
            this.paddleImage.setFitWidth(paddleSize);
            this.isLong = !this.isLong;
        }
        this.paddleImage.setPreserveRatio(true);
    }

    /** Method called at end of life or new level, resets paddle size, speed, image, length, and position to center. Also
     * pauses game.
     * @param screenSize size of Scene to recenter
     */
    public void reset(double screenSize){
        this.paddleSize = NORMAL_PADDLE_SIZE;
        this.paddleSpeed = 20;
        this.isLong = false;
        this.isSlow = false;
        this.isFast = false;
        this.setPaddleImage();
        double paddleX = screenSize/2 - this.paddleImage.getFitWidth()/2;
        double paddleY = screenSize - NORMAL_PADDLE_SIZE;
        this.setX(paddleX);
        this.setY(paddleY);
        this.togglePause();
    }

    /** Changes private instance variable isPaused
     *
     */
    public void togglePause(){
        this.isPaused = !this.isPaused;
    }
}