package sprites;

import com.sun.org.apache.regexp.internal.RE;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
import java.awt.Image;

public class Sprite {

    protected int x;
    protected int y;
    protected int imageWidth;
    protected int imageHeight;
    protected Image image;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Image loadImage(String filePath) {
        ImageIcon ii = new ImageIcon(filePath);
        return image = ii.getImage();
    }

    public void getImageDimensions() {
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


