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

    public Hero(int x, int y) {
        super(x, y);
        initHero();
    }

    private void initHero() {
        image = Assets.playerDown;
        getImageDimensions();
    }

    public void move() {

        int velocity = 4;
        if (goingLeft) {
            x -= velocity;
            image = Assets.playerLeft;
        }
        if (goingRight) {
            x += velocity;
            image = Assets.playerRight;
        }
        if (goingUp) {
            y -= velocity;
            image = Assets.playerUp;
        }
        if (goingDown) {
            y += velocity;
            image = Assets.playerDown;
        }
    }
}