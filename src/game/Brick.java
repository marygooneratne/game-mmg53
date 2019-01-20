package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Brick{
    public static final int BRICK_SIZE = 60;

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
        this.brickImage.setPreserveRatio(true);
        this.brickImage.setFitWidth(BRICK_SIZE);
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

    public int getBrickSize(){
        return BRICK_SIZE;
    }

    public boolean getIfGone(){
        return ifGone;
    }
}

