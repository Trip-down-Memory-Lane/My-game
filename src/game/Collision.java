package game;

import static game.graphics.Hero.goingUp;
import static game.graphics.Hero.goingLeft;
import static game.graphics.Hero.goingDown;
import static game.graphics.Hero.goingRight;

import javafx.scene.shape.Rectangle;

import java.util.List;



public class Collision {

    boolean badGuyCollision = false;
    private Rectangle heroHitBox;
    private Rectangle badGuyHitBox;

    Collision() {}

    void checkCollisions() {
        checkBadGuyCollision();
//        checkHeroWallCollision();
    }

    private void checkBadGuyCollision() {
        heroHitBox = Game.hero.getBounds();
        badGuyHitBox = Game.badGuy.getBounds();
        if (badGuyHitBox.intersects(heroHitBox.getBoundsInLocal())) {
            badGuyCollision = true;
        }
    }

    private void checkHeroWallCollision() {
        List<Rectangle> walls =  Game.maze.getWallsCoordinates();
        for (Rectangle wall : walls) {
            if (wall.intersects(heroHitBox.getBoundsInLocal())) {
                if (wall.getX() > Game.hero.getX()) {
                    goingRight = false;
                }
                if (wall.getX() + wall.getWidth() < Game.hero.getX()) {
                    goingLeft = false;
                }
                if (wall.getY() > Game.hero.getY()) {
                    goingDown = false;
                }
                if (wall.getY() + wall.getHeight() < Game.hero.getY()) {
                    goingUp = false;
                }
            }
        }
    }
}
