package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Ball {
    public static final String BALL_IMAGE = "ball.gif";
    public static final String RAINBOW_BALL_IMAGE = "rainbowBall.gif";

    private ImageView ballImage;
    private double ballSpeed;
    private double ballDirection;
    private double ballSize;
    private int lives;
    private boolean isPaused;

    public Ball(Paddle paddle) {
        this.lives = 3;
        this.ballImage = new ImageView();
        this.reset(paddle);
    }

    public void setBallImage(String image) {
        var ballImage = new Image(this.getClass().getClassLoader().getResourceAsStream(image));
        this.getBallImage().setImage(ballImage);
        this.ballImage.setPreserveRatio(true);
        this.ballImage.setFitWidth(this.ballSize);
    }

    public ImageView getBallImage() {
        return ballImage;
    }

    public void rainbow(){
        this.setBallImage(RAINBOW_BALL_IMAGE);
    }

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


    public boolean hitsBrick(Brick brick) {
        if (this.ballImage.getBoundsInLocal().intersects(brick.getBrickImage().getBoundsInLocal())) {
            var ballX = this.ballImage.getBoundsInLocal().getMinX() + this.ballImage.getFitWidth();
            var imageX = brick.getBrickImage().getBoundsInLocal().getMinX() + brick.getBrickImage().getFitWidth();
            this.ballDirection = ballX - imageX;
            this.reverse();
            return true;
        } else {
            return false;
        }
    }

    public void hitsPaddle(Paddle paddle) {
        if (this.ballImage.getBoundsInLocal().intersects(paddle.getPaddleImage().getBoundsInLocal())) {
            var ballX = this.ballImage.getBoundsInLocal().getMinX() + this.ballImage.getFitWidth();
            var imageX = paddle.getPaddleImage().getBoundsInLocal().getMinX() + paddle.getPaddleImage().getFitWidth();
            this.ballDirection = ballX - imageX;
            this.reverse();
        }
    }

    public void hitsSideWall(double screenWidth) {
        if (this.ballImage.getBoundsInLocal().getMinX() <= 0 || this.ballImage.getBoundsInLocal().getMaxX() >= screenWidth) {
            this.ballDirection *= -1;
        }
    }

    public void hitsTopWall() {
        if (this.ballImage.getBoundsInLocal().getMinY() <= 0) {
            this.reverse();
        }
    }

    public boolean falls(double screenHeight) {
        if (this.ballImage.getBoundsInLocal().getMaxY() >= screenHeight) {
            this.lives--;
            return true;
        }
        return false;
    }

    public void reverse() {
        this.ballSpeed *= -1;
    }

    public void setX(double x) {
        this.ballImage.setX(x);
    }

    public void setY(double y) {
        this.ballImage.setY(y);
    }

    public void slow() {
        this.ballSpeed = 60;
    }

    public void reset(Paddle paddle) {
        this.ballSize = 20;
        this.ballSpeed = 300;
        this.ballDirection = 0;
        this.setBallImage(BALL_IMAGE);
        double ballY = paddle.getPaddleImage().getY() - this.ballImage.getBoundsInLocal().getHeight() - 1;
        double ballX = paddle.getPaddleImage().getX() + paddle.getPaddleImage().getBoundsInLocal().getWidth() / 2 - this.ballImage.getBoundsInLocal().getWidth() / 2;
        this.ballImage.setX(ballX);
        this.ballImage.setY(ballY);
        this.isPaused = true;
    }

    public boolean noLives() {
        if (this.lives == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void addLives(int newLives) {
        this.lives += newLives;
    }

    public int getLives() {
        return this.lives;
    }

    public void handleKeyInput(KeyCode code) {
        if (code == KeyCode.SPACE) {
            this.isPaused = !this.isPaused;
        }
    }
}