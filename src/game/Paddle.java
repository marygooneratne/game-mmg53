package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Paddle {
    public static final String PADDLE_IMAGE = "paddle.gif";

    private ImageView paddleImage;
    private int paddleSpeed;
    private int paddleSize;

    public Paddle(){
        this.paddleSize = 60;
        this.paddleSpeed = 20;
        this.setPaddleImage();
    }

    public void setPaddleImage(){
        var ballImage = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        this.paddleImage = new ImageView(ballImage);
        this.paddleImage.setPreserveRatio(true);
        this.paddleImage.setFitWidth(40);
    }

    public ImageView getPaddleImage() {
        return paddleImage;
    }

    public void move(KeyCode code){
        if(code == KeyCode.RIGHT) {
            this.getPaddleImage().setX(this.getPaddleImage().getX() + this.paddleSpeed);
        }
        if(code == KeyCode.LEFT) {
            this.getPaddleImage().setX(this.getPaddleImage().getX() - this.paddleSpeed);
        }
    }

    public void setX(double x){
        this.paddleImage.setX(x);
    }

    public void setY(double y){
        this.paddleImage.setY(y);
    }


}