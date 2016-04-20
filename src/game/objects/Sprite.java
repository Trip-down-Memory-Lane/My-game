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
    int hitBoxWidth;
    int hitBoxHeight;
    Rectangle hitBox;
    private int imageOffsetX;
    private int imageOffsetY;
    private int hitBoxOffsetX;
    private int hitBoxOffsetY;
    BufferedImage image;

    Sprite(int x, int y) {    // Constructor
        this.x = x;
        this.y = y;
    }

    void initHitBox() {
        hitBoxOffsetX = x - hitBoxWidth / 2;
        hitBoxOffsetY = y - hitBoxHeight / 2;
    }

    void initImageDimensions() {    // Image dimensions, or Bounds, are used to detect collision.
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        imageOffsetX = x - imageWidth / 2;   // Offsets are used to center the image. By default java will put the image's left top corner on the given coordinates.
        imageOffsetY = y - imageHeight / 2;  // Using offsets we put the center of the image on X and Y.
    }
    // Bounds, or image dimensions are used for collisions.
    public Rectangle getHitBox() {
        return new Rectangle(hitBoxOffsetX, hitBoxOffsetY, hitBoxWidth, hitBoxHeight);
    }

    public Image getImage() {
        return image;
    }

    public int getHitBoxOffsetX() {
        return hitBoxOffsetX;
    }

    public int getHitBoxOffsetY() {
        return hitBoxOffsetY;
    }

    public int getImageOffsetX() {
        return imageOffsetX;
    }

    public int getImageOffsetY() {
        return imageOffsetY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}


