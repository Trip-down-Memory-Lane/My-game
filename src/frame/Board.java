//
package frame;

import java.awt.*;
import javax.swing.*;


public class Board extends Canvas {

    private JFrame frame;
    private Canvas canvas;
    private String name;
    private int width, height;

    public Board(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;

        createBoard();
    }

    private void createBoard() {
        frame = new JFrame(this.name);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setFocusable(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));

        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public int getBoardWidth() {
        return width;
    }

    public int getBoardHeight() {
        return height;
    }
}