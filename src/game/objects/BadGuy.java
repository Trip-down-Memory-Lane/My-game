//######################################################################################################################
// BadGuy.java - subclass of Sprite.java. BadGuy extends Sprite to implement a specific move function - 'followHero()'.
// It woks by checking Hero(x, y) values and incrementing or decrementing BadGuy(x, y).
// Terms used in description:
// step - x + velocityX, y + velocityX;
// clear - no wall collision.
//######################################################################################################################
package game.objects;

import game.Game;
import lib.Library;
import textures.Assets;

public class BadGuy extends Sprite {

    private static int velocityX;   // Stores the speed at which BadGuy is moving and occasionaly gives control over direction (positive and negative values).
    private static int velocityY;   // Same but for Y axis.
    // These variables are here for the special case, when Hero and BadGuy both have equal X coordinates, but are separated by wall. This way BadGuy can choose a direction to go around the wall. If not present, BadGuy would just stand bellow Hero.
    private final String[] directions = {"left", "right"};
    private boolean goingRight;
    private boolean goingLeft;

    private boolean deadEnd = false;    // This helps BadGuy return from dead ends at the borders of the frame.

    public BadGuy(int x, int y) {

        super(x, y);
        initBadGuy();
    }

    private void initBadGuy() {    // initialize

        image = Assets.badGuyUp;    // store the starting image in 'image'. See src/graphics for more.
        getImageDimensions();   // load image dimensions to use for collision.
        x = 850;
        y = 530;
    }

    public void followHero(int heroX, int heroY) {    // moving


        calculateVelocity(heroX, heroY);    // get values for 'velocityX/velocityY'
        if (!Game.collision.badGuyWallCollide(x + velocityX, y + velocityY)) {    // if does not collide with wall. 'x + velocityX' represents the next coordinates. Basically it checks if the next step (coordinates + velocity) collides with 'wall'. If not - move, else - skip.
            x += velocityX;
            y += velocityY;
        }
        // When we come here - BadGuy collides on the next step.
        if (!Game.collision.badGuyWallCollide(x + velocityX, y) && !deadEnd) {    // checks if collides with wall if moving by X axis. 'deadEnd' is overrule mechanic. See below for more info.
            if (Game.collision.badGuyWallCollide(x + velocityX, y + velocityY)) {   // Keep changing 'x' while walking by wall.
                if (x == heroX) {
                    chooseDirection();    //
                }
                if (goingLeft) {    //
                    x -= 2;
                    image = Assets.badGuyLeft;
                } else if (goingRight) {   //
                    x += 2;
                    image = Assets.badGuyRight;
                } else {
                    x += velocityX;
                }
            }
        } else if (!Game.collision.badGuyWallCollide(x, y + velocityY) && !deadEnd) {    // Same as previous. This time we increment or decrement Y values.
            if (Game.collision.badGuyWallCollide(x + velocityX, y + velocityY)) {    // Keep doing, until clear of future collisions.
// TODO IMPLEMENT Around if y == HeroY
                y += velocityY;
                goingLeft = false;    //
                goingRight = false;    //
            }
        } else if (!Game.collision.badGuyWallCollide(x - velocityX, y)) {    // Initialize 'deadEnd' if we hit the frame border. Stop 'deadEnd' if next step is clear.
            x += -velocityX;    // while 'deadEnd = true' BadGuy will only move here, because 'deadEnd' is true and all conditions above will be false, thus overruled. When BadGuy moves far enough that its next step is clear in both X and Y axis - 'deadEnd" gets 'false' value and normal movement is restored.
            deadEnd = Game.collision.badGuyWallCollide(x + velocityX, y + velocityY);   // evaluate 'deadEnd'
        } else if (!Game.collision.badGuyWallCollide(x, y - velocityY)) {
            y += -velocityY;
        }
    }
    private void calculateVelocity(int heroX, int heroY) {    // Increments or decrements BadGuy(x, y), depending on Hero(x, y)
        if (heroX > x) {
            velocityX = 2;
            image = Assets.badGuyRight;
        } else if (heroX < x) {
            velocityX = -2;
            image = Assets.badGuyLeft;
        } else {
            velocityX = 0;  // Assures no movement on X axis if BadGuy is above or below Hero.
        }
        if (heroY > y) {
            velocityY = 2;
            image = Assets.badGuyDown;
        } else if (heroY < y) {
            velocityY = -2;
            image = Assets.badGuyUp;
        } else {
            velocityY = 0;  // Same as before only for Y axis.
        }
    }

    private void chooseDirection() {    // chooses a direction randomly.
        String direction = Library.randomString(directions);
        switch (direction) {
            case "left": goingLeft = true; break;
            default: goingRight = true; break;
        }
    }
}