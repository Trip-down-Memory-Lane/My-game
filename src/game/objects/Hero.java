//######################################################################################################################
// Hero.java extends Sprite to implement user-input-driven moving function - 'move()'. Check /src/game/InputHandler for
// more info on input.
//######################################################################################################################
package game.objects;

import javafx.scene.shape.Rectangle;
import textures.Assets;

public class Hero extends Sprite {

    // Boolean variables t'o determine weather the 'Hero" is moving in the specified direction or not. They are influenced by input (set to true/false) and collisions (set to false)
    public static boolean goingUp;
    public static boolean goingLeft;
    public static boolean goingDown;
    public static boolean goingRight;
    public static boolean blockedUp;
    public static boolean blockedLeft;
    public static boolean blockedDown;
    public static boolean blockedRight;

    public static boolean sprinting;
    public static boolean sprintAttempt;
    public static int sprintCoolDown = 0;
    public static int speed = 4;

    private static int sprintDuration = 3 * 30;
    private static int frames = 0;
    private static int indexImg = 0;
    private static int counter = 0;

    public Hero(int x, int y) {
        super(x, y);
        initHero();
    }

    private void initHero() {
        image = Assets.playerDown[1];
//        hitBoxWidth = 80;
//        hitBoxHeight = 80;
        hitBoxWidth = 22;
        hitBoxHeight = 26;
//
        updateHero();
    }

    private void updateHero() {
        updateImage();
        updateHitBox();
    }

    public void move() {
        checkSprint();
        int fps = 7;
        if (sprinting) {
            fps = 3;
        }
        if (frames == fps) {
            frames = 0;
            counter++;
        }
        indexImg = counter % 4;

        int step = speed;
        if (goingUp && (goingRight || goingLeft) || goingDown && (goingRight || goingLeft)) {
            step = speed - 1;
        }
        if (goingLeft) {
            x -= step;
            image = Assets.playerLeft[indexImg];
        }
        if (goingRight) {
            x += step;
            image = Assets.playerRight[indexImg];
        }
        if (goingUp) {
            y -= step;
            image = Assets.playerUp[indexImg];
        }
        if (goingDown) {
            y += step;
            image = Assets.playerDown[indexImg];
        }
        frames++;
        if (frames > fps) {
            frames = fps;
        }
        updateHero();
    }

    private void checkSprint() {
        if (sprintReady() && sprintAttempt) {
            sprinting = true;
            speed = 6;
            sprintDuration--;
            if (sprintDuration == 0) {
                speed = 4;
                sprinting = false;
                sprintAttempt = false;
                sprintCoolDown = 10 * 30;
                sprintDuration = 3 * 30;
            }
        }
    }

    private boolean sprintReady() {
        if (sprintCoolDown == 0) {
            return true;
        }
        sprintCoolDown--;
        return false;
    }
}
//Game.collision.checkHeroWallCollision();
//        int step = speed;
//        if (goingUp && (goingRight || goingLeft) || goingDown && (goingRight || goingLeft)) {
//        step = speed - 1;
//        }
//        if (goingLeft) {
//        image = Assets.playerLeft[indexImg];
//        if (!blockedLeft) {
//        x -= step;
//        }
//        }
//        if (goingRight) {
//        image = Assets.playerRight[indexImg];
//        if (!blockedRight) {
//        x += step;
//        }
//        }
//        if (goingUp) {
//        image = Assets.playerUp[indexImg];
//        if (!blockedUp) {
//        y -= step;
//        }
//        }
//        if (goingDown) {
//        image = Assets.playerDown[indexImg];
//        if (!blockedDown) {
//        y += step;
//        }
//        }