package game;

import java.util.ArrayList;

public class BrickWall {
    public static final String LEVEL_ONE_STRING = "Level 1";
    public static final String LEVEL_TWO_STRING = "Level 2";
    public static final String LEVEL_THREE_STRING = "Level 3";
    public static final int BRICK_WIDTH = 40;
    public static final int BRICK_HEIGHT = 20;
    public static final int SIZE = 400;

    private ArrayList<Brick> brickList;
    private double xPos;
    private double yPos;
    private int index;
    private int yRow;

    public BrickWall(String level){
        this.xPos = 0;
        this.yPos = 0;
        this.index = 0;
        this.yRow = 0;
        this.brickList = new ArrayList<Brick>();
       if(level.equals(LEVEL_TWO_STRING)) {
            this.levelTwo();
        }
        else if(level.equals(LEVEL_THREE_STRING)){
            this.levelThree();
        }
        else{
            this.brickList = new ArrayList<Brick>();
            this.levelOne();
       }
    }

    private void levelOne(){
        int powerUpIndex1 = (int)(Math.random() * 30);
        int powerUpIndex2 = (int)(Math.random() * 30);
        int rainboxIndex = (int)(Math.random() * 30);
        while(this.xPos + BRICK_WIDTH <= SIZE && this.yRow < 3){
            if(((xPos/40)+(yRow*10)) == powerUpIndex1){
                this.brickList.add(new SlowdownBrick());
            }
            else if(((xPos/40)+(yRow*10)) == powerUpIndex2){
                this.brickList.add(new BombBrick());
            }
            else if(rainboxIndex % 2 == 0 && (((xPos/40)+(yRow*10)) == rainboxIndex)){
                this.brickList.add(new RainbowBrick());
            }
            else if(this.yRow == 50 || this.yRow == 2){
                this.brickList.add(new NormalBrick());
            }
            else{
                this.brickList.add(new StrongBrick());
            }
            this.brickList.get(index).setLoc(this.xPos, this.yPos);
            if(this.xPos + BRICK_WIDTH == SIZE){
                this.increment();
            }
            else{
                this.xPos = this.xPos + BRICK_WIDTH;
            }
            index++;
        }
        //this.addPowerUps();
    }

    private void levelTwo(){
        this.levelOne();
        while(this.xPos + BRICK_WIDTH <= SIZE && this.yRow < 5){
            if(this.yRow == 3){
                this.brickList.add(new NormalBrick());
            }
            else{
                this.brickList.add(new StrongBrick());
            }
            this.brickList.get(index).setLoc(this.xPos, this.yPos);
            if(this.xPos + BRICK_WIDTH == SIZE || this.xPos + (2 * BRICK_WIDTH) == SIZE){
                this.increment();
                xPos += BRICK_WIDTH;
            }
            else{
                this.xPos = this.xPos + (2 * BRICK_WIDTH);
            }
            index++;
        }
        xPos -= BRICK_WIDTH;
    }

    private void levelThree(){
        this.levelTwo();
        while(this.xPos + BRICK_WIDTH <= SIZE && this.yRow < 6){
            this.brickList.add(new NormalBrick());
            this.brickList.get(index).setLoc(this.xPos, this.yPos);
            if(this.xPos + BRICK_WIDTH == SIZE){
                this.increment();
            }
            else{
                this.xPos = this.xPos + BRICK_WIDTH;
            }
            index++;
        }
    }

    public ArrayList<Brick> getBrickList(){
        return this.brickList;
    }

    private void increment(){
        this.xPos = 0;
        this.yRow++;
        this.yPos = yRow * BRICK_HEIGHT;
    }
}
