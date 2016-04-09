package game.objects;

import javafx.scene.shape.Rectangle;
import lib.Library;

import java.util.ArrayList;
import java.util.List;

public class Maze {

    List<Rectangle> walls;
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
        createMaze();
    }

    private void createMaze() {
        margin = 60;
        wallThickness = 10;
        doorsX = 100;
        amountWalls = Library.randomInt(5, 7);

        int wallLengthStopper = 0;
        int startX = 0;
        int startY = margin;
        int mazeHeight = boardY - 2 * margin;
        int wallXLength = 0;
        int wallMinLength = 50;
        int wallMaxLength = boardX - doorsX;

        for (int wall = 0; wall < amountWalls; wall++) {
            while (startX < wallMaxLength) {
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

    public List<Rectangle> getWallsCoordinates() {
        return walls;
    }
}
