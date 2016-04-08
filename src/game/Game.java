package game;

import game.graphics.Maze;
import textures.Assets;
import textures.Drawer;

import display.Board;
import game.graphics.BadGuy;
import game.graphics.Hero;

import javafx.scene.shape.Rectangle;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    public static Hero hero;
    public static BadGuy badGuy;
    public static Board board;
    public static Maze maze;

    private BufferStrategy buffer;
    private InputHandler inputHandler;
    private Assets assets;
    private Graphics g;
    private Drawer drawer;
    private Thread thread;
    private boolean gameRunning = false;
    private boolean badGuyCollision = false;
    private String name;
    private int BOARD_X;
    private int BOARD_Y;

    Game(String name, int width, int height) {
        this.name = name;
        this.BOARD_X = width;
        this.BOARD_Y = height;
    }

    private void init() {
        board = new Board(name, BOARD_X, BOARD_Y);
        inputHandler = new InputHandler(board);
        assets = new Assets();
        maze = new Maze(BOARD_X, BOARD_Y);
        hero = new Hero(20, 20);
        badGuy = new BadGuy(850, 550);
        drawer = new Drawer();
    }

    private void tick() {
        hero.move();
        badGuy.followHero(hero.getX(), hero.getY());

        checkCollision();
    }

    private void render() {
        this.buffer = board.getCanvas().getBufferStrategy();
        if (this.buffer == null) {
            board.getCanvas().createBufferStrategy(2);
            return;
        }
        g = buffer.getDrawGraphics();

        drawer.clearCanvas(g);
        drawer.drawMaze(g);
        drawer.drawSprites(g);

        if (badGuyCollision) {
            drawer.drawGameOver(g);
            buffer.show();
            stop();
        }
        buffer.show();
        g.dispose();

        Toolkit.getDefaultToolkit().sync();

    }

    private void checkCollision() {
        Rectangle heroHitBox = hero.getBounds();
        Rectangle badGuyHitBox = badGuy.getBounds();
        if (badGuyHitBox.intersects(heroHitBox.getBoundsInLocal())) {
            badGuyCollision = true;
        }
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

        while (gameRunning) {
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

    synchronized void start() {
        if (gameRunning) {
            return;
        }
        gameRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (!gameRunning) {
            return;
        }
        gameRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
