//######################################################################################################################
// Maze.java - creates outline (frame border) walls and random maze walls on startup. Stores each wall as Rectangle in
// list.
//######################################################################################################################
package game.objects;

import javafx.scene.shape.Rectangle;
import lib.Library;

import java.util.ArrayList;
import java.util.List;

public class Maze {

    List<Rectangle> walls;    // Store maze walls
    List<Rectangle> outline;    //Store outline walls.
    private int boardY;
    private int amountRollsWithWalls;    // !!NOT ACTUAL WALL AMOUNT!! Stores amount of rolls with walls. It is like that, because of the nature of 'createMaze()' algorithm.
    private int wallThickness;
    private int margin;    // None-maze space at the top of the frame.

    static int boardX;
    static final int doorsX = 100;    // Length of the passages between walls
    static final int wallMaxLength = 300;
    static final int wallMinLength = 150;

    public Maze(int x, int y) {
        this.boardX = x;
        this.boardY = y;
        initMaze();
    }

    private void initMaze() {
        walls = new ArrayList<>();
        outline = new ArrayList<>();
        createMaze();
        createOutline();
    }

    private void createMaze() {    // Create maze. Pretty basic, it need some work, but will do for now.
        margin = 80;
        wallThickness = 20;
        amountRollsWithWalls = 6;
//        amountRollsWithWalls = Library.randomInt(5, 7);

//        int wallLengthStopper = 0;
        int startX = 0;
        int startY = margin;
        int mazeHeight = boardY - 2 * margin;
        int wallXLength = 0;
        int wallMinLength = 250; // 100
        int interval = mazeHeight / amountRollsWithWalls;

        for (int rowWalls = 0; rowWalls < amountRollsWithWalls; rowWalls++) {
            while (startX < boardX) {   // ensures each roll has X length of Frame X length.
                if (wallMaxLength - startX == wallMinLength) {   // Minimizes appearance of too small passages at the right frame outline.
                    walls.add(new Rectangle(startX, startY, wallMinLength, wallThickness));
                } else {
                    wallXLength = Library.randomInt(wallMinLength, wallMaxLength); // creates a wall with random length.
                    walls.add(new Rectangle(startX, startY, wallXLength, wallThickness));
                }
                startX += wallXLength + doorsX;    // staring point for next wall = wall + passage;
//                wallLengthStopper = startX / 100;
            }
            startX = 0;
            startY += interval;  // Start point for next roll of walls at constant interval interval.
        }
    }

    private void createOutline() {  // Create outline walls.
        outline.add(new Rectangle(0, 0, boardX, 10));
        outline.add(new Rectangle(1356, 0, 10, boardY));
        outline.add(new Rectangle(0, 733, boardX, 10));
        outline.add(new Rectangle(0, 0, 10, boardY));
    }

    public List<Rectangle> getWallsCoordinates() {
        return walls;
    }

    public List<Rectangle> getOutlineCoordinates() {
        return outline;
    }
}
