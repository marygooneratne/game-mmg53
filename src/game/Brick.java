package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Brick{
    public static final int BRICK_WIDTH = 40;
    public static final int BRICK_HEIGHT = 20;

    private int hitsLeft;
    private String powerUp;
    private ImageView brickImage;
    private boolean ifGone;

    public Brick() {
    }

    public int getHitsLeft(){
        return this.hitsLeft;
    }

    public String getPowerUp(){
        return this.powerUp;
    }

    public ImageView getBrickImage(){
        return brickImage;
    }

    public void hit(){
        this.hitsLeft--;
        if(hitsLeft == 0){
            this.brickImage.setImage(null);
            this.ifGone = true;
        }
    }

    public void setBrickImage(ImageView brickImage) {
        this.brickImage = brickImage;
        this.brickImage.setFitWidth(BRICK_WIDTH);
        this.brickImage.setFitHeight(BRICK_HEIGHT);
    }

    public void setHitsLeft(int hitsLeft){
        this.hitsLeft = hitsLeft;
    }

    public void setPowerUp(String powerUp){
        this.powerUp = powerUp;
    }

    public void setX(double x){
        this.brickImage.setX(x);
    }

    public void setY(double y){
        this.brickImage.setY(y);
    }

    public boolean getIfGone(){
        return ifGone;
    }

    public void setIfGone(boolean setGone){
        this.ifGone = setGone;
    }

    public void setLoc(double x, double y){
        this.setX(x);
        this.setY(y);
    }
}

