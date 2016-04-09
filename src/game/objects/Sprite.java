package game.objects;

import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

    int x;
    int y;
    int offsetX;
    int offsetY;
    private int imageWidth;
    private int imageHeight;
    BufferedImage image;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g) {
        g.drawImage(image, x - offsetX, y - offsetY, null);
        g.setColor(Color.red);
        g.fillRect(x, y, 10, 10);
        Toolkit.getDefaultToolkit().sync();
    }

    void getImageDimensions() {
        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);
        offsetX = imageWidth / 2;
        offsetY = imageHeight / 2;
    }

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


