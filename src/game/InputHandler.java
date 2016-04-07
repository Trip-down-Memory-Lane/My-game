package game;

import display.Board;
import sprites.Hero;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    public InputHandler(Board display) {
        display.getCanvas().addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP: Hero.goingUp = true; break;
            case KeyEvent.VK_LEFT: Hero.goingLeft = true; break;
            case KeyEvent.VK_DOWN: Hero.goingDown = true; break;
            case KeyEvent.VK_RIGHT: Hero.goingRight = true; break;
            case KeyEvent.VK_ESCAPE: System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP: Hero.goingUp = false; break;
            case KeyEvent.VK_LEFT: Hero.goingLeft = false; break;
            case KeyEvent.VK_DOWN: Hero.goingDown = false; break;
            case KeyEvent.VK_RIGHT: Hero.goingRight = false; break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
