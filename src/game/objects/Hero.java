//######################################################################################################################
// Hero.java extends Sprite to implement user-input-driven moving function - 'move()'. Check /src/game/InputHandler for
// more info on input.
//######################################################################################################################
package game.objects;

import textures.Assets;

public class Hero extends Sprite {
    // Boolean variables to determine weather the 'Hero" is moving in the specified direction or not. They are influenced by input (set to true/false) and collisions (set to false)
    public static boolean goingUp;
    public static boolean goingLeft;
    public static boolean goingDown;
    public static boolean goingRight;
    private static int step;

    public Hero(int x, int y) {
        super(x, y);
        initHero();
    }

    private void initHero() {
        image = Assets.playerDown;
        getImageDimensions();
    }

    public void move() {
        step = 4;
        if (goingUp && (goingRight || goingLeft) || goingDown  && (goingRight || goingLeft)) {
            step = step - 1;
        }
        if (goingLeft) {
            x -= step;
            image = Assets.playerLeft;
        }
        if (goingRight) {
            x += step;
            image = Assets.playerRight;
        }
        if (goingUp) {
            y -= step;
            image = Assets.playerUp;
        }
        if (goingDown) {
            y += step;
            image = Assets.playerDown;
        }
    }

    private void sprint() {
        
    }
}