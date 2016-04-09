package game.objects;

import textures.Assets;

public class BadGuy extends Sprite {

    private static int veloity = 0;

    public BadGuy(int x, int y) {
        super(x, y);
        initBadGuy();
    }

    private void initBadGuy() {
        image = Assets.badGuyUp;
        getImageDimensions();
//        offsetX = getOffsetX();
//        offsetY = getOffsetY();
//        x = 870 - offsetX;
//        y = 570 -  offsetX;
        x = 850;
        y = 550;
    }

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
