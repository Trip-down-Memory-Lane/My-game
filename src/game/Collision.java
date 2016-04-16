//######################################################################################################################
// Collision.java. Detects all collisions by intersecting bounds data of rectangles.
//######################################################################################################################
package game;

import javafx.scene.shape.Rectangle;

import java.util.List;

import static game.objects.Hero.*;

public class Collision {

    boolean badGuyCollision = false;
    private Rectangle heroHitBox;
    private Rectangle badGuyHitBox;
    private List<Rectangle> walls;
    private List<Rectangle> outlines;

    Collision() {
        initObjectBounds();
    }

    private void initObjectBounds() {   // Gets walls coordinates from maze object.
        outlines = Game.maze.getOutlineCoordinates();
        walls =  Game.maze.getWallsCoordinates();
    }

    void checkCollisions() {    // Checks for all collisions.
        updateBounds();     // gets the proper Rectangle bounds of non-static objects for this 'tick()'
        checkBadGuyCollision();
        checkHeroWallCollision();
    }

    private void updateBounds() {
        heroHitBox = Game.hero.getBounds();
        badGuyHitBox = Game.badGuy.getBounds();
    }

    private void checkBadGuyCollision() {    // Game over
        if (badGuyHitBox.intersects(heroHitBox.getBoundsInLocal())) {
            badGuyCollision = true;
        }
    }

    private void checkHeroWallCollision() {   // Prevents 'Hero' from moving through walls.
        walls.forEach(this::heroWallCollide);
        outlines.forEach(this::heroWallCollide);
    }

    private void heroWallCollide(Rectangle wall) {
        int wallX = (int) wall.getX();    // Getting wall coordinates
        int wallY = (int) wall.getY();
        int wallWidth = (int) wall.getWidth();
        int wallHeight = (int) wall.getHeight();
        int heroX = Game.hero.getX();     // Getting some 'Hero' coordinates, needed for more specific wall interactions.
        int heroY = Game.hero.getY();
        // Hero pads accounts for the offsets. They equal image's width / 2 and height / 2. Center point for 'Hero' is 'heroX'If you decrement 'heroX' with 'heroPadWidth' you get the leftmost X point of Hero, while if you sum them up - you get the rightmost point X. Same applies for Y axis as well.
        int heroPadWidth = 15;
        int heroPadHeight = 19;
        // These are a bit of headache, but they are necessary to make sure only one movement boolean ('Hero') is turned false per wall collision. This way if 'Hero' collides with a wall while going upward, it would only stop 'goingUp'
        if (wall.intersects(heroHitBox.getBoundsInLocal())) {
            if ( // Wall to the right of 'Hero'
                    wallX >= heroX &&
                    heroY - heroPadHeight < wallY + wallHeight && // Last two make sure that 'Hero' is to the left of the wall and not under or above it.
                    heroY + heroPadHeight > wallY
                    ) {
                goingRight = false;
            }
            if (  // Wall to the left
                    wallX + wallWidth <= heroX - heroPadWidth &&    // we add the width of wall, to find the rightmost X coordinates
                    heroY - heroPadHeight < wallY + wallHeight &&   // Same as before, only this time 'Hero' is on the right
                    heroY + heroPadHeight > wallY
                    ) {
                goingLeft = false;
            }
            if (
                    wallY >= heroY &&
                    heroX - heroPadWidth < wallX + wallWidth &&    // 'Hero' is above;
                    heroX + heroPadWidth > wallX
                    ) {
                goingDown = false;
            }
            if (
                    wallY + wallHeight <= heroY &&
                    heroX - heroPadWidth < wallX + wallWidth &&    // 'Hero' is below;
                    heroX + heroPadWidth > wallX
                    ) {
                goingUp = false;
            }
        }
    }

    public boolean badGuyWallCollide(int currentX, int currentY) { // checks if 'BadGuy' Collides and returns result
        int width = (int) badGuyHitBox.getWidth();
        int height = (int) badGuyHitBox.getHeight();
        int offsetX = Game.badGuy.getOffsetX();
        int offsetY = Game.badGuy.getOffsetY();
        Rectangle currentHitBox = new Rectangle(currentX - offsetX, currentY - offsetY, width, height);
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
