package sprites;

public class BadGuy extends Sprite {

    public BadGuy(int x, int y) {
        super(x, y);
        initBadGuy();
    }

    public void initBadGuy() {
        loadImage("resources/craft.gif");
        getImageDimensions();
    }


    public void followHero(int heroX, int heroY) {
        if (heroX > x) {
            x += 1;
        } else if (heroX < x) {
            x -= 1;
        }
        if (heroY > y) {
            y += 1;
        } else if (heroY < y) {
            y -= 1;
        }
    }
}
