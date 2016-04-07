package game;

import graphics.Assets;

import display.Board;
import graphics.Assets;
import sprites.BadGuy;
import sprites.Hero;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    private Thread thread;
    private boolean running = false;
    private String name;
    private int width, height;
    private InputHandler inputHandler;
    private Hero hero;
    private BadGuy badGuy;
    private Graphics g;
    private BufferStrategy buffer;
    private Board board;


    private final int HERO_START_X = 20;
    private final int HERO_START_Y = 20;
    private final int BOARD_MAX_X = 855;
    private final int BOARD_MAX_Y = 525;

    public Game(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    private void init() {
        board = new Board("My Game", 900, 600);
        this.inputHandler = new InputHandler(this.board);
        Assets.init();
        hero = new Hero(HERO_START_X, HERO_START_Y);
        badGuy = new BadGuy(BOARD_MAX_X, BOARD_MAX_Y);
    }

    private void tick() {
        hero.move(BOARD_MAX_X, BOARD_MAX_Y);
        badGuy.followHero(hero.getX(), hero.getY());

//        checkCollision();
//        checkGameStatus();
    }

    private void render() {
        this.buffer = board.getCanvas().getBufferStrategy();
        if (this.buffer == null) {
            board.getCanvas().createBufferStrategy(2);
            this.buffer = board.getCanvas().getBufferStrategy();
            return;
        }

        g = buffer.getDrawGraphics();
        g.clearRect(0, 0, BOARD_MAX_X, BOARD_MAX_Y);
        hero.render(g);
        badGuy.render(g);

        buffer.show();
        g.dispose();

    }

    @Override
    public void run() {
        init();

        int fps = 30;
        int second = 1_000_000_000;
        double timePerTick = second / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long now;
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= second) {
                System.out.println("Ticks and frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    @Override
//        super.paintComponent(g);
//        setDoubleBuffered(true);
//        if (collide) {
//            drawGameOver(g);
//        } else {
//            drawObjects(g);
//        }
//
//        Toolkit.getDefaultToolkit().sync();
//    }
//
//    private void drawObjects(Graphics g) {
//        Graphics2D g2d = (Graphics2D) g;
//        setDoubleBuffered(true);
//
//        g2d.drawImage(hero.getImage(), hero.getX(), hero.getY(), this);
//        g2d.drawImage(badGuy.getImage(), badGuy.getX(), badGuy.getY(), this);
//    }
//
//    private void drawGameOver(Graphics g) {
//
//        String message = "Game Over";
//        Font small = new Font("Helvetica", Font.BOLD, 14);
//        FontMetrics fm = getFontMetrics(small);
//
//        g.setColor(Color.white);
//        g.setFont(small);
//        g.drawString(message, (BOARD_MAX_X - fm.stringWidth(message)) / 2, BOARD_MAX_Y / 2);
//    }
//
//    public void actionPerformed(ActionEvent e) {
//        hero.move(BOARD_MAX_X, BOARD_MAX_Y);
//        badGuy.followHero(hero.getX(), hero.getY());
//
//        checkCollision();
//        checkGameStatus();
//
//        repaint();
//    }
//
//    public void checkCollision() {
//
//        javafx.scene.shape.Rectangle heroHitBox = hero.getBounds();
//        javafx.scene.shape.Rectangle badGuyHitBox = badGuy.getBounds();
//        if (badGuyHitBox.intersects(heroHitBox.getBoundsInLocal())) {
//            collide = true;
//        }
//
//    }
//
//    public void checkGameStatus() {
//        if (collide) {
//            timer.stop();
//        }
//    }
//
//    private class TAdapter extends KeyAdapter {
//
//        @Override
//        public void keyPressed(KeyEvent e) {
//            hero.keyPressed(e);
//        }
//
//        @Override
//        public void keyReleased(KeyEvent e) {
//            hero.keyReleased(e);
//        }
//    }
//
//
}
