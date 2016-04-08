package game.graphics;

import textures.Assets;

public class Hero extends Sprite {

    public static boolean goingUp;
    public static boolean goingLeft;
    public static boolean goingDown;
    public static boolean goingRight;

    private final int velocity = 6;

    public Hero(int x, int y) {

        super(x, y);
        initHero();
    }

    private void initHero() {
        image = Assets.playerDown;
        getImageDimensions();
        x = 20;
        y = 20;
    }

    public void move() {
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

//    public void render(Graphics g) {
////        if (goingLeft) {
////            image = Assets.playerLeft;
////        } else if (goingRight) {
////            image = Assets.playerRight;
////        }
////        if (goingUp) {
////            image = Assets.playerUp;
////        }
////        if (goingDown) {
////            image = Assets.playerDown;
////        }
//        g.drawImage(image, x, y, null);
//
//        Toolkit.getDefaultToolkit().sync();
//
//    }
}