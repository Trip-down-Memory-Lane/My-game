package graphics;

import game.Game;

import java.awt.FontMetrics;
import java.awt.*;

public class Drawer {

    private final int x = Game.board.getBoardWidth();
    private final int y = Game.board.getBoardHeight();

    public Drawer(){

    }

    public void clearCanvas(Graphics g) {
        g.clearRect(0, 0, x, y);
    }

    public void drawSprites(Graphics g) {
        Game.hero.render(g);
        Game.badGuy.render(g);
    }

    public void drawGameOver(Graphics g) {
        String message = "Game Over";
        Font large = new Font("Helvetica", Font.BOLD, 18);
        FontMetrics fm = g.getFontMetrics(large);

        clearCanvas(g);
        g.setColor(Color.black);
        g.setFont(large);
        g.drawString(message, (x - fm.stringWidth(message)) / 2, y / 2 - 50);
    }

    public void drawWalls(Graphics g) {

    }



}
