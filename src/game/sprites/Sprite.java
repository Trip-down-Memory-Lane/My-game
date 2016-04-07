package game.sprites;

import graphics.ImageLoader;

import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

    int x;
    int y;
    private int imageWidth;
    private int imageHeight;
    BufferedImage image;

    Sprite(int x, int y) {
        this.x = x;
        this.y = y;
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


