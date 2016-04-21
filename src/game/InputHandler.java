//######################################################################################################################
// InputHandler.java - Listens for input ( as in arrow keys ) and passes it to Hero.java
//######################################################################################################################
package game;

import frame.Board;
import game.objects.Hero;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class InputHandler implements KeyListener {

    InputHandler(Board display) {
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
            case KeyEvent.VK_ESCAPE: Game.notPaused = !Game.notPaused; break;
            case KeyEvent.VK_C: Hero.cheating = !Hero.cheating; break;
            case KeyEvent.VK_S:
                if (Hero.sprintCoolDown == 0) {
                    Hero.sprintAttempt = true;
                }
                break;
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
