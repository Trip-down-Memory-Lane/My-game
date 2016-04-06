package game;

import sprites.BadGuy;
import sprites.Hero;

import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;


public class Board extends JPanel implements ActionListener {

    private Canvas canvas = new Canvas();
    private BadGuy badGuy;
    private Hero hero;
    private Timer timer;
//    private Thread thread;
//    private boolean running = false;
    private boolean collide = false;
    private final int DELAY = 10;
    private final int HERO_START_X = 20;
    private final int HERO_START_Y = 20;
    private final int BOARD_MAX_X = 855;
    private final int BOARD_MAX_Y = 525;


    public Board() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        add(canvas);

        hero = new Hero(HERO_START_X, HERO_START_Y);
        badGuy = new BadGuy(BOARD_MAX_X - 30, BOARD_MAX_Y - 25);
        timer = new Timer(DELAY, this);
        timer.start();
    }

//    private void start() {
//        if (running) {
//            return;
//        }
//        running = true;
//        thread = new Thread(this);
//        thread.start();
//    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setDoubleBuffered(true);
        if (collide) {
            drawGameOver(g);
        } else {
            drawObjects(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        setDoubleBuffered(true);

        g2d.drawImage(hero.getImage(), hero.getX(), hero.getY(), this);
        g2d.drawImage(badGuy.getImage(), badGuy.getX(), badGuy.getY(), this);
    }

    private void drawGameOver(Graphics g) {

        String message = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (BOARD_MAX_X - fm.stringWidth(message)) / 2, BOARD_MAX_Y / 2);
    }

    public void actionPerformed(ActionEvent e) {
        hero.move(BOARD_MAX_X, BOARD_MAX_Y);
        badGuy.followHero(hero.getX(), hero.getY());

        checkCollision();
        checkGameStatus();

        repaint();
    }

    public void checkCollision() {

        Rectangle heroHitBox = hero.getBounds();
        Rectangle badGuyHitBox = badGuy.getBounds();
        if (badGuyHitBox.intersects(heroHitBox.getBoundsInLocal())) {
            collide = true;
        }

    }

    public void checkGameStatus() {
        if (collide) {
            timer.stop();
        }
    }
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            hero.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            hero.keyReleased(e);
        }
    }

}