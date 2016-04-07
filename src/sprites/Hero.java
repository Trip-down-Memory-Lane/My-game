package sprites;

import graphics.Assets;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Hero extends Sprite {

    public static boolean goingUp;
    public static boolean goingLeft;
    public static boolean goingDown;
    public static boolean goingRight;

    private int dynamicX;
    private int dynamicY;

//    private Image Assets

    public Hero(int x, int y) {
        super(x, y);
        initHero();
    }

    private void initHero() {
        loadImage("resources/Sprite.png");
        getImageDimensions();
    }

    public void move(int maxX, int maxY) {
        if (x + dynamicX > 0 && x + dynamicX < maxX) {
            x += dynamicX;
        }
        if (y + dynamicY > 0 && y + dynamicY < maxY) {
            y += dynamicY;
        }
    }

    public void render(Graphics g) {
        Image image;
        switch(dynamicX) {
            case 2: image = Assets.playerRight; break;
            case -2: image = Assets.playerLeft; break;
        }
        switch (dynamicY) {
            case 2: image = Assets.playerDown; break;
            case -2: image = Assets.playerUp; break;
            default: image = Assets.playerUp; break;
        }
        g.drawImage(image, x, y, null);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP: dynamicY = -2; break;
            case KeyEvent.VK_LEFT: dynamicX = -2; break;
            case KeyEvent.VK_DOWN: dynamicY = 2; break;
            case KeyEvent.VK_RIGHT: dynamicX = 2; break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP: dynamicY = 0; break;
            case KeyEvent.VK_LEFT: dynamicX = 0; break;
            case KeyEvent.VK_DOWN: dynamicY = 0; break;
            case KeyEvent.VK_RIGHT: dynamicX = 0; break;
        }
    }
}