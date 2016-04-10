//######################################################################################################################
// Sprite.java - parent class for 'Hero' and 'BadGuy'.
//######################################################################################################################
package game.objects;

import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

    int x;
    int y;
    private int offsetX;
    private int offsetY;
    private int imageWidth;
    private int imageHeight;
    BufferedImage image;

    Sprite(int x, int y) {    // Constructor
        this.x = x;
        this.y = y;
    }

    void getImageDimensions() {    // Image dimensions, or Bounds, are used to detect collision.
        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);
        offsetX = imageWidth / 2;   // Offsets are used to center the image. By default java will put the image's left top corner on the given coordinates.
        offsetY = imageHeight / 2;  // Using offsets we put the center of the image on X and Y.
    }
    // Bounds, or image dimensions are used for collisions.
    public Rectangle getBounds() {
        return new Rectangle(x - 18, y - 22, imageWidth, imageHeight);
    }

    public Image getImage() {
        return image;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}


