package textures;

import game.Game;

import javafx.scene.shape.Rectangle;
import java.util.List;

import java.awt.FontMetrics;
import java.awt.*;


public class Drawer {

    private final int boardX = Game.board.getBoardWidth();
    private final int boardY = Game.board.getBoardHeight();

    public Drawer(){

    }

    public void clearCanvas(Graphics g) {
        g.clearRect(0, 0, boardX, boardY);
    }

    public void drawSprites(Graphics g) {
//        Game.hero.render(g);
//        Game.badGuy.render(g);
        drawSprite(g, "hero");
        drawSprite(g, "badGuy");
    }

    public void drawGameOver(Graphics g) {
        String message = "Game Over";
        Font large = new Font("Helvetica", Font.BOLD, 18);
        FontMetrics fm = g.getFontMetrics(large);

        clearCanvas(g);
        g.setColor(Color.black);
        g.setFont(large);
        g.drawString(message, (boardX - fm.stringWidth(message)) / 2, boardY / 2 - 50);
    }

    public void drawOutline(Graphics g) {
        List<Rectangle> outlines = Game.maze.getOutlineCoordinates();
        for (Rectangle outline : outlines) {
            int x = (int) outline.getX();
            int y = (int) outline.getY();
            int width = (int) outline.getWidth();
            int height = (int) outline.getHeight();
            g.setColor(Color.black);
            g.fillRect(x, y, width, height);
        }
    }

    public void drawMaze(Graphics g) {
        List<Rectangle> walls = Game.maze.getWallsCoordinates();
        int counter = 0;
        for (Rectangle coordinates: walls) {
            int x = (int) coordinates.getX();
            int y = (int) coordinates.getY();
            int width = (int) coordinates.getWidth();
            int height = (int) coordinates.getHeight();
            g.setColor(Color.black);
            g.fillRect(x, y, width, height);
            //
            counter++;
            String message = "" + counter;
            Font large = new Font("Helvetica", Font.BOLD, 20);
            g.setColor(Color.red);
            g.setFont(large);
            g.drawString(message, x, y);
        }
    }

    private void drawSprite(Graphics g, String character) {
        int x, y, offsetX, offsetY;
        Image image;
        switch (character) {
            case "badGuy":
                x = Game.badGuy.getX();
                y = Game.badGuy.getY();
                offsetX = Game.badGuy.getOffsetX();
                offsetY = Game.badGuy.getOffsetY();
                image = Game.badGuy.getImage();
                break;
            default:
                x = Game.hero.getX();
                y = Game.hero.getY();
                offsetX = Game.hero.getOffsetX();
                offsetY = Game.hero.getOffsetY();
                image = Game.hero.getImage();
                break;
        }

        g.drawImage(image, x - offsetX, y - offsetY, null);
        int a = (int) Game.badGuy.getBounds().getX();
        int b = (int) Game.badGuy.getBounds().getY();
        int width = (int) Game.badGuy.getBounds().getWidth();
        int height = (int) Game.badGuy.getBounds().getHeight();
        g.drawRect(a, b, width, height);
    }
}
