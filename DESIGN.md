##High Level Design Goals
The high level goals of this project includde designing a break-out game 
with three increasingly difficult levels that were also user-friendly. In the 
design of this game, I wished to provide enough abstraction to allow for 
expansion and extension of the game in the context of levels, bricks, power-ups,
paddles, and balls.

##Adding New Features
* Adding another level: To add another level, one major adjustment must be made to the BrickWall 
class. There must be a new method adding on to the block-configuration, done easily with a pre-
existing "newRows()" method. Next, two major adjustments must be created in the main game 
class. First a method must be written to construct the Scene for the new level, the format
is almost identical to those of other levels. And lastly, the if-statement in the step() function
must be extended to include passing the third level to additional levels.
* Adding another kind of brick: The way to do this will be wildly dependent on the functionality of
the block. However, by following, the Brick template, the programmer can ensure the new class
will fit smoothly. Additionally, the if-statement in the step() must be extended to include
the consequences of detroying that type of block.
* Adding new paddle types: This will most likely involve the addition of new instance variables
to the paddle as well as methods in the paddle that change these parameters. These methods
would then be called as a result of different game states.
* Adding new ball types: This will most likely involve the addition of new instance variables
to the ball as well as methods in the ball that change these parameters. These methods
 would then be called as a result of different game states.
 * Adding cheat codes: This would be done in the game class in the handleKeyInput() method,
 where a chosen key could result in different (perhaps new) method calls on balls, bricks, or 
 paddles.
## Major Design Choices
* Major Design Choice 1: **Abstraction of Brick** Abstracting the brick
made the design and complication of each subsequent level much easier. However,
it also resulted in a bit of repetitive code and minimal consistency between classes.
* Major Design Choice 2: **Step function in game method** This was a hard decision for me.
The pros of it is that you don't have to worry too much about updating variables in different
classes because that one class can control the updates of a lot of different variables. However, at
the same time, it also meant a lot of clutter in what should be a clean, main class.
* Major Design Choice 3: **Making ball and paddle co-dependent** The paddle and ball depend
on one another in their construction. This is risky because it requires this connection between two separate objects.
However, the pro of this is that I was able to easily locate and reset the game.
## Assumptions or Decisions
I assumed the player would use cheat codes not as a way of truly cheating but rather exploring.
I say this because it seems rather odd to get victory screen after skipping three levels.
Furthermore, addition of features like a rainbow ball seem perhaps slightly unnecessary so
I built them with the assumption that they would be enjoyed for their novelty. Furthermore,
I assumed the player would understand the sacrifice of hitting the slowdown brick but also
being able to speed up again if they sacrificed a life.