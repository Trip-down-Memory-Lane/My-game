package game.objects;

import game.Collision;
import game.Game;
import lib.Library;
import textures.Assets;

public class BadGuy extends Sprite {

    private static int velocityX;
    private static int velocityY;
    private final String[] directions = {"left", "right"};
    private boolean goingRight;
    private boolean goingLeft;
    private boolean deadEnd = false;

    public BadGuy(int x, int y) {

        super(x, y);
        initBadGuy();
    }

    private void initBadGuy() {

        image = Assets.badGuyUp;
        getImageDimensions();
        x = 850;
        y = 530;
    }

    public void followHero(int heroX, int heroY) {


        calculateVelocity(heroX, heroY);
        if (!Game.collision.badGuyWallCollide(x + velocityX, y + velocityY)) {
            x += velocityX;
            y += velocityY;
        }
        if (!Game.collision.badGuyWallCollide(x + velocityX, y) && !deadEnd) {
            if (Game.collision.badGuyWallCollide(x + velocityX, y + velocityY)) {
                if (x == heroX) {
                    chooseDirection();
                }
                if (goingLeft) {
                    x -= 2;
                } else if (goingRight) {
                    x += 2;
                } else {
                    x += velocityX;
                }
            }
        } else if (!Game.collision.badGuyWallCollide(x, y + velocityY) && !deadEnd) {
            if (Game.collision.badGuyWallCollide(x + velocityX, y + velocityY)) {
                y += velocityY;
                goingLeft = false;
                goingRight = false;
            }
        } else if (!Game.collision.badGuyWallCollide(x - velocityX, y)) {
            x += -velocityX;
            deadEnd = Game.collision.badGuyWallCollide(x + velocityX, y + velocityY);
        } else if (!Game.collision.badGuyWallCollide(x, y - velocityY)) {
            y += -velocityY;
        }
    }
//
    private void chooseDirection() {
        String direction = Library.randomString(directions);
        switch (direction) {
            case "left": goingLeft = true; break;
            default: goingRight = true; break;
        }
    }

    private void calculateVelocity(int heroX, int heroY) {
        if (heroX > x) {
            velocityX = 2;
        } else if (heroX < x) {
            velocityX = -2;
        } else {
            velocityX = 0;
        }
        if (heroY > y) {
            velocityY = 2;
        } else if (heroY < y) {
            velocityY = -2;
        } else {
            velocityY = 0;
        }
    }
}