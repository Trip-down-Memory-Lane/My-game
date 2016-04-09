package game.objects;

import textures.Assets;

public class Hero extends Sprite {

    public static boolean goingUp;
    public static boolean goingLeft;
    public static boolean goingDown;
    public static boolean goingRight;

    private final int velocity = 4;

    public Hero(int x, int y) {
        super(x, y);
        initHero();
    }

    private void initHero() {
        image = Assets.playerDown;
        getImageDimensions();
//        offsetX = getOffsetX();
//        offsetY = getOffsetY();
//        x -= offsetX;
//        y -= offsetX;
        x = 30;
        y = 30;
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

//    void render(Graphics g) {
//
//    }

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