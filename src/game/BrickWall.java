package game;

import java.util.ArrayList;

/** CODE WRITTEN BY: MARY GOONERATNE (@mmg53)
 * Instantiaion of BrickWall sets up the grid of bricks for the appropriate level
 *  of Breakout. The bricks are stored in an ArrayList. This class is responsible
 *  for creating Normal, Strong, and Power-up bricks, placing them on the Scene.
 *  This class is automatically called upon configuration of a game level. Instance
 *  variables include the list of bricks and indices for brick placement.
 */
public class BrickWall {
    public static final String LEVEL_ONE_STRING = "Level 1";
    public static final String LEVEL_TWO_STRING = "Level 2";
    public static final String LEVEL_THREE_STRING = "Level 3";

    public static final int INIT = 0;
    public static final int SINGLE = 1;
    public static final int DOUBLE = 2;
    public static final int TRIPLE = 3;

    public static final String NORMAL = "Normal";
    public static final String STRONG = "Strong";
    public static final String SOLID = "Solid";
    public static final String ALTERNATING = "Alternating";

    public static final int BRICK_WIDTH = 40;
    public static final int BRICK_HEIGHT = 20;
    public static final int SIZE = 400;

    private ArrayList<Brick> brickList;
    private double xPos;
    private double yPos;
    private int yRow;
    private int index;
    private int slowdownIndex;
    private int rainbowIndex;
    private int bombIndex;
    private int alternatingIndex;

    /** The BrickWall() constructor initializes all indices for brick placement,
     * makes calls to determine random indices for power-up bricks, sets up an empty
     * ArrayList, and calls a unique method to configure the BrickWall() based
     * on the level.
     * @param level int parameter indicating level and block configuration
     */

    public BrickWall(String level){
        this.xPos = INIT;
        this.yPos = INIT;
        this.index = INIT;
        this.yRow = INIT;

        this.alternatingIndex = 0;

        this.slowdownIndex = this.getArrayIndex();
        this.rainbowIndex = this.getArrayIndex();
        this.bombIndex = this.getArrayIndex();

        this.brickList = new ArrayList<Brick>();

        if(level.equals(LEVEL_TWO_STRING)) {
            this.levelTwo();
        }
        else if(level.equals(LEVEL_THREE_STRING)){
            this.levelThree();
        }
        else{
            this.levelOne();
        }
    }

    /** Getter method that returns brickList to be used in Scene set-up in main game
     * method
     * @return ArrayList<Brick>
     */

    public ArrayList<Brick> getBrickList() {
        return this.brickList;
    }

    private void levelOne(){
        this.newRows(SINGLE, NORMAL, SOLID);
        this.newRows(SINGLE, STRONG, SOLID);
        this.newRows(SINGLE, NORMAL, SOLID);
    }

    private void levelTwo(){
        this.levelOne();
        this.newRows(SINGLE, STRONG, ALTERNATING);
        this.newRows(SINGLE, NORMAL, ALTERNATING);
    }

    private void levelThree(){
        this.levelTwo();
        this.newRows(SINGLE, NORMAL, SOLID);
    }

    /** Creates new rows on Scene and in brickList. Bricks are indexed from 0 moving
     * left to right, top to bottom on scene. Calls to this method are used to
     * configure more complex brick set-ups
     *
     * @param numRows number of rows to be added to grid and list
     * @param brickType kind of brick in these rows
     * @param patternType ALTERNATING (e.g. checkerboard pattern) or SOLID (simple row)
     */
    private void newRows(int numRows, String brickType, String patternType){
        int brickSpace = BRICK_WIDTH;
        int totalRows = this.yRow + numRows;
        if(patternType.equals(ALTERNATING)){
            brickSpace *= 2;
            if(alternatingIndex % DOUBLE == INIT){
                this.xPos = INIT;
            }
            else{
                this.xPos = BRICK_WIDTH;
            }
            this.alternatingIndex++;
        }
        while(this.xPos + BRICK_WIDTH <= SIZE && this.yRow < totalRows){
            if(this.index == this.slowdownIndex){
                this.brickList.add(new SlowdownBrick());
            }
            else if(this.index == this.bombIndex){
                this.brickList.add(new BombBrick());
            }
            else if(this.index == this.rainbowIndex){
                this.brickList.add(new RainbowBrick());
            }
            else if(brickType.equals(STRONG)){
                this.brickList.add(new StrongBrick());
            }
            else{
                this.brickList.add(new NormalBrick());
            }
            this.brickList.get(this.index).setLoc(this.xPos, this.yPos);

            if(this.xPos + brickSpace >= SIZE){
                this.increment();
            }
            else{
                this.xPos = this.xPos + brickSpace;
            }
            this.index++;
        }

    }

    private int getArrayIndex(){
        return (int)(Math.random()*30);
    }

    /** Helper method for newRows() class that increments indices at end of each row
     *
     */
    private void increment(){
        this.xPos = 0;
        this.yRow++;
        this.yPos = yRow * BRICK_HEIGHT;
    }
}
