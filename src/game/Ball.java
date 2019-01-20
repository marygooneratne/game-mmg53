package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ball {
    public static final String BALL_IMAGE = "ball.gif";

    private ImageView ballImage;
    private double ballSpeed;
    private double ballDirection;
    private double ballSize;
    private int lives;

    public Ball(){
        this.ballSize = 20;
        this.ballSpeed = 60;
        this.lives = 3;
        this.ballDirection = 0;
        this.setBallImage();
    }

    public void setBallImage(){
        var ballImage = new Image(this.getClass().getClassLoader().getResourceAsStream(BALL_IMAGE));
        this.ballImage = new ImageView(ballImage);
        this.ballImage.setPreserveRatio(true);
        this.ballImage.setFitWidth(this.ballSize);
    }

    public ImageView getBallImage() {
        return ballImage;
    }

    public void step(double elapsedTime ) {
        this.ballImage.setY(this.ballImage.getY() + this.ballSpeed * elapsedTime);
        this.ballImage.setX(this.ballImage.getX() + this.ballDirection * elapsedTime);
        if(this.ballImage.getY() <= 0){
            this.reverse();
        }
    }

    public boolean hitsBrick(Brick brick){
        if(this.ballImage.getBoundsInLocal().intersects(brick.getBrickImage().getBoundsInLocal())) {
            var ballX = this.ballImage.getBoundsInLocal().getMinX()+ this.ballImage.getFitWidth();
            var imageX = brick.getBrickImage().getBoundsInLocal().getMinX() + brick.getBrickImage().getFitWidth();
            this.ballDirection = ballX-imageX;
            return true;
        }
        else{
            return false;
        }
    }

    public void hitsPaddle(Paddle paddle){
        if(this.ballImage.getBoundsInLocal().intersects(paddle.getPaddleImage().getBoundsInLocal())) {
            var ballX = this.ballImage.getBoundsInLocal().getMinX()+ this.ballImage.getFitWidth();
            var imageX = paddle.getPaddleImage().getBoundsInLocal().getMinX() + paddle.getPaddleImage().getFitWidth();
            this.ballDirection = ballX-imageX;
            this.reverse();
        }
    }

    public void hitsSideWall(double screenWidth){
        if(this.ballImage.getBoundsInLocal().getMinX() <= 0 || this.ballImage.getBoundsInLocal().getMaxX() >= screenWidth){
            this.ballDirection *= -1;
        }
    }

    public void hitsTopWall(double screenHeight){
        if(this.ballImage.getBoundsInLocal().getMinY() <= 0) {
            this.reverse();
        }
    }

    public void falls(double screenHeight){
        if(this.ballImage.getBoundsInLocal().getMaxY() >= screenHeight) {
            this.lives--;
        }
    }

    public void reverse(){
        this.ballSpeed *= -1;
    }

    public void setX(double x){
        this.ballImage.setX(x);
    }

    public void setY(double y){
        this.ballImage.setY(y);
    }


}
