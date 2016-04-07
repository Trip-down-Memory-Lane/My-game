package game.sprites;

import graphics.Assets;

import java.awt.*;

public class BadGuy extends Sprite {

    private static int veloity = 2;

    public BadGuy(int x, int y) {
        super(x, y);
        initBadGuy();
    }

    private void initBadGuy() {
        image = Assets.badGuy;
        getImageDimensions();
        x = 860;
        y = 560;
    }

    public void render(Graphics g) {
        g.drawImage(Assets.badGuy, x, y, null);
        Toolkit.getDefaultToolkit().sync();
    }

    public void followHero(int heroX, int heroY) {
        if (heroX > x) {
            x += veloity;
        } else if (heroX < x) {
            x -= veloity;
        }
        if (heroY > y) {
            y += veloity;
        } else if (heroY < y) {
            y -= veloity;
        }
    }
}
