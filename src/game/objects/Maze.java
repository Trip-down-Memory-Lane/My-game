package game.objects;

import javafx.scene.shape.Rectangle;
import lib.Library;

import java.util.ArrayList;
import java.util.List;

public class Maze {

    List<Rectangle> walls;
    List<Rectangle> outline;
    private int boardX;
    private int boardY;
    private int amountWalls;
    private int wallThickness;
    private int doorsX;
    private int margin;

    public Maze(int x, int y) {
        this.boardX = x;
        this.boardY = y;
        initMaze();
    }

    public void initMaze() {
        walls = new ArrayList<>();
        outline = new ArrayList<>();
        createMaze();
        createOutline();
    }

    private void createMaze() {
        margin = 80;
        wallThickness = 10;
        doorsX = 100;
        amountWalls = 5;
//        amountWalls = Library.randomInt(5, 7);

        int wallLengthStopper = 0;
        int startX = 0;
        int startY = margin;
        int mazeHeight = boardY - 2 * margin;
        int wallXLength = 0;
        int wallMinLength = 100;
        int wallMaxLength = 300;

        for (int wall = 0; wall < amountWalls; wall++) {
            while (startX < boardX) {
                if (wallMaxLength - startX == wallMinLength) {
//                    Integer[] wallSize = {startX, startY, wallMinLength, wallThickness};
                    walls.add(new Rectangle(startX, startY, wallMinLength, wallThickness));
                } else {
                    wallXLength = Library.randomInt(wallMinLength, wallMaxLength - wallLengthStopper);
//                    Integer[] wallSize = {startX, startY, wallXLength, wallThickness};
                    walls.add(new Rectangle(startX, startY, wallXLength, wallThickness));
                }
                startX += wallXLength + doorsX;
                wallLengthStopper = startX / 100;
            }
            startX = 0;
            startY += mazeHeight / amountWalls;
        }
    }

    private void createOutline() {
        outline.add(new Rectangle(0, 0, boardX, wallThickness));
        outline.add(new Rectangle(890, 0, wallThickness, boardY));
        outline.add(new Rectangle(0, 590, boardX, wallThickness));
        outline.add(new Rectangle(0, 0, wallThickness, boardY));
    }

    public List<Rectangle> getWallsCoordinates() {
        return walls;
    }

    public List<Rectangle> getOutlineCoordinates() {
        return outline;
    }
}
