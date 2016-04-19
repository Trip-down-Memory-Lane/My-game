//######################################################################################################################
// BadGuy.java - subclass of Sprite.java. BadGuy extends Sprite to implement a specific move function - 'followHero()'.
// It woks by checking Hero(x, y) values and incrementing or decrementing BadGuy(x, y).
// Terms used in description:
// step - x + stepX, y + stepX;
// clear - no wall collision.
//######################################################################################################################
package game.objects;

import game.Game;
import lib.Library;
import textures.Assets;

public class BadGuy extends Sprite {

    public static int lengthLeft;
    public static int lengthRight;

    private static int stepX;   // Stores the step pixels  at which BadGuy is moving and occasionaly gives control over direction (positive and negative values).
    private static int stepY;   // Same but for Y axis.
    private static int speed = 4;   // Speed of the step
    // These variables are here for the special case, when Hero and BadGuy both have equal X coordinates, but are separated by wall. This way BadGuy can choose a direction to go around the wall. If not present, BadGuy would just stand bellow Hero.
    private boolean goingRight;
    private boolean goingLeft;
    private boolean goingUp;
    private boolean goingDown;

    private boolean deadEnd = false;    // This helps BadGuy return from dead ends at the borders of the frame.

    public BadGuy(int x, int y) {
        super(x, y);
        initBadGuy();
    }

    private void initBadGuy() {    // initialize
        image = Assets.badGuyUp;    // store the starting image in 'image'. See src/graphics for more.
        getImageDimensions();   // load image dimensions to use for collision.
    }

    public void followHero(int heroX, int heroY) {    // moving
        calculateHeading(heroX, heroY);    // get values for 'stepX/stepY'
        if (!wallCollision(x + stepX, y + stepY)) {    // if does not collide with wall. 'x + stepX' represents the next coordinates. Basically it checks if the next step (coordinates + step) collides with 'wall'. If not - move, else - skip.
            x += stepX;
            y += stepY;
        }
        // When we come here - BadGuy collides on the next step.
        if (!wallCollision(x + stepX, y)) {    // checks if collides with wall if moving by X axis. 'deadEnd' is overrule mechanic. See below for more info.
            if (wallCollision(x + stepX, y + stepY)) {   // Keep changing 'x' while walking by wall.
                if (Math.abs(heroX - x) <= speed) {
                    chooseDirection('x');    //
                }
                if (goingLeft) {    //
                    x -= speed;
                    image = Assets.badGuyLeft;
                } else if (goingRight) {   //
                    x += speed;
                    image = Assets.badGuyRight;
                } else {
                    if (x < Maze.wallMinLength + Maze.doorMaxLength + speed && wallCollision(x + stepX, y + stepY) && heroX < x) {
                        x -= stepX;
                        if (heroX < x) {
                            goingRight = true;
                        } else if (heroX > x) {
                            goingLeft = true;
                        }
                    } else if (x > Game.maze.getMazeWidth() - (Maze.wallMinLength + Maze.doorMaxLength + speed) && wallCollision(x + stepX, y + stepY) && heroX > x) {
                        x -= stepX;
                        if (heroX < x) {
                            goingRight = true;
                        } else if (heroX > x) {
                            goingLeft = true;
                        }
                    } else {
                        x += stepX;
                    }
                }
                goingDown = false;
                goingUp = false;
            }
        } else if (!wallCollision(x, y + stepY)) {    // Same as previous. This time we increment or decrement Y values.
            if (wallCollision(x + stepX, y + stepY)) {    // Keep doing, until clear of future collisions.
                if (Math.abs(heroY - y) <= speed) {
                    chooseDirection('y');    //
                }
                if (goingUp) {    //
                    y -= speed;
                    image = Assets.badGuyUp;
                } else if (goingDown) {   //
                    y += speed;
                    image = Assets.badGuyDown;
                } else {
                    y += stepY;
                }
                goingLeft = false;    //
                goingRight = false;    //
            }
        }
//        } else if (!wallCollision(x - stepX, y)) {    // Initialize 'deadEnd' if we hit the frame border. Stop 'deadEnd' if next step is clear.
//            x += -stepX;    // while 'deadEnd = true' BadGuy will only move here, because 'deadEnd' is true and all conditions above will be false, thus overruled. When BadGuy moves far enough that its next step is clear in both X and Y axis - 'deadEnd" gets 'false' value and normal movement is restored.
//            deadEnd = wallCollision(x + stepX, y + stepY);   // evaluate 'deadEnd'
//        } else if (!wallCollision(x, y - stepY)) {
//            y += -stepY;
//        }
    }

//    private boolean deadEnd(int stepX) {
//        for (int i = 0, n = Maze.wallMaxLength / 4; i < n; i++) {
//            if(Game.collision.badGuyOutlineCollision(x + stepX, y)) {
//                return true
//            }
//        }
//    }

    private void calculateHeading(int heroX, int heroY) {    // Increments or decrements BadGuy(x, y), depending on Hero(x, y)
        if (Math.abs(heroX - x) < speed) {
            stepX = 0;  // Assures no movement on X axis if BadGuy is above or below Hero.
        } else if (heroX < x) {
            if (!(Math.abs(heroY - y) <= speed) && !wallCollision(x, y)) {
                stepX = -(speed - 1);
            } else {
                stepX = -speed;
            }
            image = Assets.badGuyLeft;
        } else {
            if (!(Math.abs(heroY - y) <= speed) && !wallCollision(x, y)) {
                stepX = speed - 1;
            } else {
                stepX = speed;
            }
            image = Assets.badGuyRight;
        }
        if (Math.abs(heroY - y) < speed) {
            stepY = 0;  // Same as before only for Y axis.
        } else if (heroY < y) {
            if (!(Math.abs(heroX - x) <= speed) && !wallCollision(x, y)) {
                stepY = -(speed - 1);
            } else {
                stepY = -speed;
            }
            image = Assets.badGuyUp;
        } else {
            if (!(Math.abs(heroX - x) <= speed) && !wallCollision(x, y)) {
                stepY = speed - 1;
            } else {
                stepY = speed;
            }
            image = Assets.badGuyDown;
        }
    }

    private boolean wallCollision(int x, int y) {
        return Game.collision.badGuyWallCollide(x, y);
    }

    private void chooseDirection(char axis) {    // chooses a direction randomly.
        String direction;
        if (lengthLeft < lengthRight) {
            direction = "left";
        } else if (lengthLeft > lengthRight) {
            direction = "right";
        } else {
            direction = Library.randomString("left", "right");
        }
        if (axis == 'x') {
            switch (direction) {
                case "left": goingLeft = true; break;
                default: goingRight = true; break;
            }
        } else {
            switch (direction) {
                case "left": goingUp = true; break;
                default: goingDown = true; break;
            }
        }

    }
}