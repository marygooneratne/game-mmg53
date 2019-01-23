package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

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

    public Paddle(double screenSize){
        this.screenSize = screenSize;
        this.paddleImage = new ImageView();
        this.reset(screenSize);
    }

    public void setPaddleImage(){
        var paddleGetImage = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        this.getPaddleImage().setImage(paddleGetImage);
        this.paddleImage.setPreserveRatio(true);
        this.paddleImage.setFitWidth(paddleSize);
    }

    public ImageView getPaddleImage() {
        return paddleImage;
    }

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

    public void setX(double x){
        this.paddleImage.setX(x);
    }

    public void setY(double y){
        this.paddleImage.setY(y);
    }

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

    public void togglePause(){
        this.isPaused = !this.isPaused;
    }
}