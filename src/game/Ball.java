package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

/** CODE WRITTEN BY: MARY GOONERATNE (@mmg53)
 * Class defining Ball object. Ball is instantiated prior to ever "reset" of the game
 * (e.g. life lost, level reset). The ball object is bounced using the paddle
 * and can collide with bricks to "hit" them. The ball starts at a standard speed
 * but can be changed by a power up brick. The ball has three lives or four
 * if level 1 and 2 are completed with all three lives. The ball can also become
 * rainbow from another power-up brick. The ball is automatically created in the configuration
 * of the game.
 */

public class Ball {
    public static final String BALL_IMAGE = "ball.gif";
    public static final String RAINBOW_BALL_IMAGE = "rainbowBall.gif";
    public static final int SPEED = 300;
    public static final int SLOW_SPEED = 150;
    public static final int START_LIVES = 3;
    public static final int BALL_SIZE = 20;

    private ImageView ballImage;
    private double ballSpeed;
    private double ballDirection;
    private double ballSize;
    private int lives;
    private boolean isPaused;

    /** Initializes new Ball object for new level of Breakout game. Ball has properties of speed, image, direction,
     * size, lives, and "isPaused"
     *
     * @param paddle Paddle object that Ball is initialized on
     */
    public Ball(Paddle paddle) {
        this.lives = START_LIVES;
        this.ballImage = new ImageView();
        this.reset(paddle);
    }

    /** Changes ball image. To be used in constructor, reset() method, or rainbow() method
     *
     * @param image String describing file path for desired image
     */
    private void setBallImage(String image) {
        var ballImage = new Image(this.getClass().getClassLoader().getResourceAsStream(image));
        this.getBallImage().setImage(ballImage);
        this.ballImage.setPreserveRatio(true);
        this.ballImage.setFitWidth(this.ballSize);
    }

    /** Returns ball image
     *
     *
     * @return ImageView object ballImage
     */
    public ImageView getBallImage() {
        return ballImage;
    }

    /** Returns private instance variable describing ball's number of lives left
     *
     *
     * @return int lives;
     */
    public int getLives() {
        return this.lives;
    }

    /** Adds additional lives to ball
     *
     * @param newLives Number of lives to be added
     */
    public void addLives(int newLives) {
        this.lives += newLives;
    }

    /** Handles key input for ball. Ball only changes states with use of KeyCode.SPACE to toggle pause/resume.
     *
     *
     * @param code Relevant keycode from user input
     */
    public void handleKeyInput(KeyCode code) {
        if (code == KeyCode.SPACE) {
            this.isPaused = !this.isPaused;
        }
    }

    /** Used when RainbowBrick() is hit and changes the ballImage to "rainbowBall.gif"
     *
     */
    public void rainbow(){
        this.setBallImage(RAINBOW_BALL_IMAGE);
    }

    /** Reverses direction of ball
     *
     */
    private void reverse() {
        this.ballSpeed *= -1;
    }

    /** Slows ball to parameter defined by SLOW_SPEED
     *
     */
    public void slow() {
        this.ballSpeed = SLOW_SPEED;
    }

    /** Resets ball. Used at beginning of level or life. Resets ball size, ball speed, ball direction, and ball image.
     * Ball is replaced on paddle and game is paused.
     * @param paddle Paddle that ball is reset on
     */
    private void reset(Paddle paddle) {
        this.ballSize = BALL_SIZE;
        this.ballSpeed = SPEED;
        this.ballDirection = 0;
        this.setBallImage(BALL_IMAGE);
        double ballY = paddle.getPaddleImage().getY() - this.ballImage.getBoundsInLocal().getHeight() - 1;
        double ballX = paddle.getPaddleImage().getX() + paddle.getPaddleImage().getBoundsInLocal().getWidth() / 2 - this.ballImage.getBoundsInLocal().getWidth() / 2;
        this.ballImage.setX(ballX);
        this.ballImage.setY(ballY);
        this.isPaused = true;
    }

    /** Method used in main step method to change ball. When game is unpaused, ball will move based on current speed and
     * elapsedTime. This method also checks for wall and paddle conditions and if the ball has fallen off the screen. If
     * a collision occurs, the appropriate direction change will occur. If the ball falls, a life is lost and the ball is reset.
     * @param elapsedTime Total time in step that ball is moving
     * @param paddle Paddle that ball is active with
     * @param screenSize Size of screen to check for bounds or wall collisions
     */
    public void step(double elapsedTime, Paddle paddle, double screenSize) {
        if(!this.isPaused) {
            this.ballImage.setY(this.ballImage.getY() + this.ballSpeed * elapsedTime);
            this.ballImage.setX(this.ballImage.getX() + this.ballDirection * elapsedTime);
            this.hitsPaddle(paddle);
            this.hitsSideWall(screenSize);
            this.hitsTopWall();
            if (this.falls(screenSize)) {
                isPaused = true;
                paddle.reset(screenSize);
                this.reset(paddle);
            }
        }
    }

    /** Checks for collision with brick and changes direction if so, returning true if there was a collision
     *
     * @param brick Brick to check for collision with
     * @return boolean false if no collision and true if collision
     */
    public boolean hitsBrick(Brick brick) {
        if (this.ballImage.getBoundsInLocal().intersects(brick.getBrickImage().getBoundsInLocal())) {
            var ballX = this.ballImage.getX() + (this.ballImage.getFitWidth()/2);
            var imageX = brick.getBrickImage().getX() + (brick.getBrickImage().getFitWidth()/2);
            this.ballDirection = ballX - imageX;
            this.reverse();
            return true;
        } else {
            return false;
        }
    }

    /** Checks for collision with paddle and changes direction if so
     *
     * @param paddle Paddle to check for collision with
     */
    public void hitsPaddle(Paddle paddle) {
        if (this.ballImage.getBoundsInLocal().intersects(paddle.getPaddleImage().getBoundsInLocal())) {
            var ballX = this.ballImage.getX() + (this.ballImage.getFitWidth()/2);
            var imageX = paddle.getPaddleImage().getX() + (paddle.getPaddleImage().getFitWidth()/2);
            this.ballDirection = ballX - imageX;
            this.reverse();
        }
    }

    /** Checks for collision with side walls and changes direction if so
     *
     * @param screenWidth Boundaries to check for collision against
     */
    public void hitsSideWall(double screenWidth) {
        if (this.ballImage.getBoundsInLocal().getMinX() <= 0 || this.ballImage.getBoundsInLocal().getMaxX() >= screenWidth) {
            this.ballDirection *= -1;
        }
    }

    /** Checks for collision with top wall and changes direction if so
     *
     */
    public void hitsTopWall() {
        if (this.ballImage.getBoundsInLocal().getMinY() <= 0) {
            this.reverse();
        }
    }

    /** Checks if ball falls out of bounds, decrementing lives and returning true if so
     *
     * @param screenHeight Boundaries to check if ball has fallen
     * @return boolean false if ball has not fallen and true if it has
     */
    public boolean falls(double screenHeight) {
        if (this.ballImage.getBoundsInLocal().getMaxY() >= screenHeight) {
            this.lives--;
            return true;
        }
        return false;
    }
}