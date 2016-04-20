package textures;

import game.Game;

import game.objects.Hero;
import game.objects.Maze;
import javafx.scene.shape.Rectangle;

import java.util.List;

import java.awt.FontMetrics;
import java.awt.*;


public class Drawer {

    private final int boardX = Game.board.getBoardWidth();
    private final int boardY = Game.board.getBoardHeight();

    public Drawer(){

    }

    public void drawFrames(Graphics g) {
        g.drawImage(Assets.playerDown[0], 700, 490, 60, 60, null);
        g.drawImage(Assets.playerDown[1], 750, 490, 60, 60, null);
        g.drawImage(Assets.playerDown[2], 800, 490, 60, 60, null);
        g.drawImage(Assets.playerDown[3], 850, 490, 60, 60, null);
    }

    public void clearCanvas(Graphics g) {
        g.clearRect(0, 0, boardX, boardY);
    }

    public void drawSprites(Graphics g) {
        drawSprite(g, "hero");
        drawSprite(g, "badGuy");
    }

    private void drawSprite(Graphics g, String character) {
        int x, y, offsetX, offsetY, hitBoxOffsetX, hitBoxOffsetY;
        Image image;
        switch (character) {
            case "badGuy":
//                hitBoxOffsetX = Game.badGuy.getHitBoxOffsetX();
//                hitBoxOffsetY = Game.badGuy.getHitBoxOffsetY();
                offsetX = Game.badGuy.getImageOffsetX();
                offsetY = Game.badGuy.getImageOffsetY();
                image = Game.badGuy.getImage();
                break;
            default:
//                hitBoxOffsetX = Game.badGuy.getHitBoxOffsetX();
//                hitBoxOffsetY = Game.badGuy.getHitBoxOffsetY();
                offsetX = Game.hero.getImageOffsetX();
                offsetY = Game.hero.getImageOffsetY();
                image = Game.hero.getImage();
                break;
        }

        g.drawImage(image, offsetX, offsetY, null);

        int c = (int) Game.hero.getHitBox().getX();
        int d = (int) Game.hero.getHitBox().getY();
        int Awidth = (int) Game.hero.getHitBox().getWidth();
        int Bheight = (int) Game.hero.getHitBox().getHeight();
        g.drawRect(c, d, Awidth, Bheight);

        int a = (int) Game.badGuy.getHitBox().getX();
        int b = (int) Game.badGuy.getHitBox().getY();
        int width = (int) Game.badGuy.getHitBox().getWidth();
        int height = (int) Game.badGuy.getHitBox().getHeight();
        g.drawRect(a, b, width, height);

        Toolkit.getDefaultToolkit().sync();
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

    public void drawPause(Graphics g) {
        String message = "Paused";
        Font large = new Font("Helvetica", Font.BOLD, 22);
        FontMetrics fm = g.getFontMetrics(large);

        g.setColor(Color.red);
        g.setFont(large);
        g.drawString(message, 1250, 720);
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
            g.fillRoundRect(x, y, width, height, 2, 2);
            //
            counter++;
            String message = "" + counter;
            Font large = new Font("Helvetica", Font.BOLD, 20);
            g.setColor(Color.red);
            g.setFont(large);
            g.drawString(message, x, y);
        }
    }

//    public void drawArtefact(Graphics g) {
//        List<Rectangle> artefacts = Game.artefact.getArtefacts();
//        for (Rectangle artefact: artefacts) {
//            int x = (int) artefact.getX();
//            int y = (int) artefact.getY();
//            int width = (int) artefact.getWidth();
//            int height = (int) artefact.getHeight();
//            int offsetX = width / 2;
//            int offsetY = height / 2;
//
//            g.drawImage(Assets.artefact, x - offsetX, y - offsetY, null);
//            g.setColor(Color.red);
//            g.fillRect(x - 5, y - 5, 10, 10);
//        }
//    }

    public void drawHeroPanel(Graphics g) {
        int outline = Game.maze.getOutlineThickness();
        int margin = Game.maze.getMazeMargin();

        g.setColor(Color.green);
        g.fillRect(730, 0, boardX - 730, outline + margin + 10);
        g.setColor(Color.black);
        g.fillRoundRect(735, outline / 2, 155, 80, 10, 10);
        g.setColor(Color.gray);
        g.fillRoundRect(740, outline / 2 + outline / 4, 80, 70, 10, 10);
        g.drawImage(Assets.heroPortrait, 749, outline / 2 + outline / 4 + 3, 60, 60, null);


        g.setColor(Color.black);
        g.fillRoundRect(825, outline / 2, 145, 45, 10, 10);
        g.setColor(Color.white);
        g.fillRoundRect(828, outline + 3 - outline / 4, 130, 29, 10, 10);

        String speed = "Speed: " + Hero.speed;
        Font medium = new Font("Helvetica", Font.BOLD, 21);
        g.setColor(Color.red);
        g.setFont(medium);
        g.drawString(speed, 835, 2 * outline);


//        g.setColor(Color.black);
//        g.fillRect(825, 2 * outline - 2 + 12, 139, 35);
        g.setColor(Color.gray);
        g.fillRoundRect(828, 2 * outline + 14, 50, 28, 10, 10);
        g.drawImage(Assets.heroSprint, 830, 3 * outline, null);

        if (Hero.sprinting) {
            int transparent = 127;
            Color color = new Color(63, 224, 101, transparent);
            g.setColor(color);
            g.fillRoundRect(828, 54, 50, 28, 10, 10);
        }

        if (Hero.sprintCoolDown != 0) {
            int transparent = 127;
            Color color = new Color(255, 255, 255, transparent);
            g.setColor(color);
            g.fillRoundRect(828, 54, 50, 28, 10, 10);
            String coolDown = Integer.toString(Hero.sprintCoolDown / 30);
            Font large = new Font("Helvetica", Font.BOLD, 30);
            g.setColor(Color.red);
            g.setFont(large);
            g.drawString(coolDown, 842, 80);
        }
//        g.setColor(Color.white);
//        g.fillRoundRect(828, 49, 130, 29, 10, 10);
//
//        String artefacts = "Artefacts: ";
//        g.setColor(Color.red);
//        g.setFont(medium);
//        g.drawString(artefacts, 830, 70);


    }

}
