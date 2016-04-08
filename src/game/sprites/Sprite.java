package game.sprites;

import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

    int x;
    int y;
    private int imageWidth;
    private int imageHeight;
    BufferedImage image;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g) {
        g.drawImage(image, x, y, null);
        Toolkit.getDefaultToolkit().sync();
    }

    void getImageDimensions() {
        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, imageWidth, imageHeight);
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}


