//######################################################################################################################
// Game.java - Actual game object. Initializes all objects. Starts the game, controls the threads and stops the game.
// The Game runs on threaded frame - 'tick() - render()' system.
//######################################################################################################################
package game;

import game.objects.Artefact;
import game.objects.Maze;
import textures.Assets;
import textures.Drawer;

import frame.Board;
import game.objects.BadGuy;
import game.objects.Hero;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    // Objects needed by other classes as well - public.
    public static Hero hero;
    public static BadGuy badGuy;
    public static Board board;
    public static Maze maze;
    public static Collision collision;
    public static Artefact artefact;

    static boolean notPaused = false;

    private BufferStrategy buffer;
    private InputHandler inputHandler;
    private Assets assets;
    private Graphics g;
    private Drawer drawer;
    private Thread thread;
    private boolean gameRunning = false;
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
        assets = new Assets();
        maze = new Maze(BOARD_X, BOARD_Y);
        artefact = new Artefact();
        collision = new Collision();
        hero = new Hero(40, 40);
        badGuy = new BadGuy(1250, 700);
        drawer = new Drawer();
        inputHandler = new InputHandler(board);
    }

    private void tick() {    // Represents the actions happening inside the game. In this case :
        collision.checkCollisions();    // Checks sprites and walls for collisions.
        hero.move();    // Gets the next coordinates for Hero.
        badGuy.followHero(hero.getX(), hero.getY());    // Same for BadGuy
    }

    private void render() {    // Here we render the graphics
        // First segment takes care of buffers - double buffer strategy is standard. May be system dependent.
        this.buffer = board.getCanvas().getBufferStrategy();
        if (this.buffer == null) {    // This condition passes only on the first render, because we need to crate the buffer strategy only once.
            board.getCanvas().createBufferStrategy(2);
            return;
        }
        g = buffer.getDrawGraphics();    // Exactly as it says - get graphics from buffer.

        if (notPaused) {
            // Re-drawing canvas each time.
            drawer.clearCanvas(g);   // Clears the canvas from the objects drawn on the previous render()
            drawer.drawOutline(g);
            drawer.drawMaze(g);
            drawer.drawArtefact(g);
            drawer.drawSprites(g);
            if (Hero.sprintCoolDown >= 0) {
                drawer.drawCoolDown(g);
            }
        } else {
            drawer.drawPause(g);
        }

        if (collision.badGuyCollision) {    // End-game condition.
            drawer.drawGameOver(g);
            buffer.show();
            stop();    // Calls for stopping of the game process.
        }
        buffer.show();
        g.dispose();    // Good practice for system resources optimization.

        Toolkit.getDefaultToolkit().sync();    // May drastically improve animation quality on some systems.
    }

    @Override
    public void run() {    // Inner class for Runnable. it is automatically called by 'thread.start()' in 'start()'
        init();

        // This block represents our infinite game loop. The math bellow is done to optimize processing and reduce resources. It's a bit tricky for me as well :D
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

            if (delta >= 1) { // 'delta' makes sure we tick at the given FPS (frames per second)
                if (notPaused) {
                    tick();
                    render();
                } else {
                    render();
                }
                ticks++;
                delta--; // Reset delta.
            }

            if (timer >= second) { // Console output.
                System.out.println("Ticks and frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop(); // Calls for stop()
    }

    synchronized void start() { // Starts the game.
        if (gameRunning) {
            return;
        }
        gameRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {  // Joins all threads and ends the game.
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
