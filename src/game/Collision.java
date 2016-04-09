package game;

import static game.objects.Hero.goingUp;
import static game.objects.Hero.goingLeft;
import static game.objects.Hero.goingDown;
import static game.objects.Hero.goingRight;

import javafx.scene.shape.Rectangle;

import java.util.List;

public class Collision {

    boolean badGuyCollision = false;
    private Rectangle heroHitBox;
    private Rectangle badGuyHitBox;
    private List<Rectangle> walls;
    private List<Rectangle> outlines;

    Collision() {
        initObjectBounds();
    }

    private void initObjectBounds() {
        outlines = Game.maze.getOutlineCoordinates();
        walls =  Game.maze.getWallsCoordinates();
    }

    void checkCollisions() {
        updateBounds();
        checkBadGuyCollision();
        checkHeroWallCollision();
    }

    private void updateBounds() {
        heroHitBox = Game.hero.getBounds();
        badGuyHitBox = Game.badGuy.getBounds();
    }

    private void checkBadGuyCollision() {
        if (badGuyHitBox.intersects(heroHitBox.getBoundsInLocal())) {
            badGuyCollision = true;
        }
    }

    private void checkHeroWallCollision() {
        walls.forEach(this::heroWallCollide);
        outlines.forEach(this::heroWallCollide);
    }

    private void heroWallCollide(Rectangle wall) {
        int wallX = (int) wall.getX();
        int wallY = (int) wall.getY();
        int wallWidth = (int) wall.getWidth();
        int wallHeight = (int) wall.getHeight();
        int heroX = Game.hero.getX();
        int heroY = Game.hero.getY();
        int heroPadWidth = 15;
        int heroPadHeight = 19;

        if (wall.intersects(heroHitBox.getBoundsInLocal())) {
            if (
                    wallX > heroX &&
                    heroY - heroPadHeight < wallY + wallHeight &&
                    heroY + heroPadHeight > wallY
                    ) {
                goingRight = false;
            }
            if (
                    wallX + wallWidth <= heroX - heroPadWidth &&
                    heroY - heroPadHeight < wallY + wallHeight &&
                    heroY + heroPadHeight > wallY
                    ) {
                goingLeft = false;
            }
            if (
                    wallY > heroY &&
                    heroX - heroPadWidth < wallX + wallWidth &&
                    heroX + heroPadWidth > wallX
                    ) {
                goingDown = false;
            }
            if (
                    wallY + wallHeight < heroY &&
                    heroX - heroPadWidth < wallX + wallWidth &&
                    heroX + heroPadWidth > wallX
                    ) {
                goingUp = false;
            }
        }
    }

    public boolean badGuyWallCollide(int currentX, int currentY) {
        int width = (int) badGuyHitBox.getWidth();
        int height = (int) badGuyHitBox.getHeight();
        Rectangle currentHitBox = new Rectangle(currentX - 21, currentY - 24, width, height);
        for (Rectangle wall : walls) {
            if (wall.intersects(currentHitBox.getBoundsInLocal())) {
                return true;
            }
        }
        for (Rectangle outline : outlines) {
            if (outline.intersects(currentHitBox.getBoundsInLocal())) {
                return true;
            }
        }
        return false;
    }
}
