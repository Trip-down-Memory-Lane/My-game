package game.sprites;

import graphics.Assets;

import java.awt.*;

public class BadGuy extends Sprite {

    private static int veloity = 3;

    public BadGuy(int x, int y) {
        super(x, y);
        initBadGuy();
    }

    private void initBadGuy() {
        image = Assets.badGuyUp;
        getImageDimensions();
        x = 860;
        y = 560;
    }

//    public void render(Graphics g) {
//        g.drawImage(Assets.badGuy, x, y, null);
//        Toolkit.getDefaultToolkit().sync();
//    }

    public void followHero(int heroX, int heroY) {
        if (heroX > x) {
            x += veloity;
            image = Assets.badGuyRight;
        } else if (heroX < x) {
            x -= veloity;
            image = Assets.badGuyLeft;
        }
        if (heroY > y) {
            y += veloity;
            image = Assets.badGuyDown;
        } else if (heroY < y) {
            y -= veloity;
            image = Assets.badGuyUp;
        }
    }
}
