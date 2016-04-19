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

    private List<Rectangle> walls;    // Store maze walls
    private List<Rectangle> outline;    //Store outline walls.
    private int mazeX;
    private int mazeY;
    private static int wallMaxLength = 300;

    static int wallMinLength = 150;
    static final int doorMaxLength = 100;    // Length of the passages between walls

    public Maze(int x, int y) {
        this.mazeX = x - 20;
        this.mazeY = y - 20;
        initMaze();
    }

    private void initMaze() {
        walls = new ArrayList<>();
        outline = new ArrayList<>();
        createMaze();
        createOutline();
    }

    private void createMaze() {    // Creates maze
        int margin = 70;
        int wallThickness = 20;
        int mazeHeight = mazeY - 50 - 2 * margin;
        int interval = (mazeHeight - 80) / 6;
        int rowMargin = 10;

        int rowStartX;
        int rowStartY = interval + margin;
        int wallLength;
        int randomWallToIncrease = Library.randomPick(1, 2, 3);
        for (int rowWithWalls = 0; rowWithWalls < 5; rowWithWalls++) {
            int mazeEntranceWall = (mazeX - 100) / 2;
            int mazeEntranceWallX = mazeX / 2 + doorMaxLength / 2 + rowMargin;
            if (rowWithWalls == 0) {
                walls.add(new Rectangle(rowMargin, margin, mazeEntranceWall, wallThickness));
                walls.add(new Rectangle(mazeEntranceWallX, 0, mazeEntranceWall, wallThickness + margin));
            } else if (rowWithWalls == 4) {
                walls.add(new Rectangle(rowMargin, mazeHeight + margin, mazeEntranceWall, 2 * wallThickness));
                walls.add(new Rectangle(mazeEntranceWallX, mazeHeight + margin, mazeEntranceWall, 2 * wallThickness));
            } else {
                rowStartX = Library.randomPick(0, 50, 100) + rowMargin;
                int secondRowStartX, secondRowStartY, secondRowWallLength;
                switch (rowStartX) {
                    case 10: secondRowStartX = Library.randomPick(50, 100) + rowMargin; break;
                    case 60: secondRowStartX = Library.randomPick(0, 100) + rowMargin; break;
                    default: secondRowStartX = Library.randomPick(0, 50) + rowMargin; break;
                }
                int rowLength = rowStartX;
                int secondRowLength = secondRowStartX;
                int previousWallLength = 0;
                while (rowLength < mazeX) {   // ensures each roll has X length of Frame X length.
                    wallLength = Library.randomIntBetween(wallMinLength, wallMaxLength); // creates a wall with random length.
                    int rowEndDoor = Library.randomPick(50, 100);
                    if (randomWallToIncrease == rowWithWalls) {
                        wallLength = 400;
                    } else if (rowLength + wallLength > mazeX - rowEndDoor) {
                        wallLength = mazeX - rowLength - rowEndDoor;
                    } else if (mazeX - (rowLength + wallLength) < wallMinLength + doorMaxLength) {
                        wallLength = mazeX - (rowLength) - rowEndDoor;
                    }

                    walls.add(new Rectangle(rowLength, rowStartY, wallLength, wallThickness));

                    secondRowStartY = rowStartY + interval;
                    secondRowWallLength = rowStartX + wallLength + doorMaxLength;
                    if (Math.abs(previousWallLength - secondRowWallLength) < doorMaxLength) {
                        secondRowWallLength = wallMaxLength;
                    }

                    walls.add(new Rectangle(secondRowLength, secondRowStartY, secondRowWallLength, wallThickness));

                    rowLength += wallLength + doorMaxLength;    // staring point for next wall = wall + passage;
                    secondRowLength += secondRowWallLength + doorMaxLength;

                    wallMinLength += Library.randomPick(50, 75);
                    wallMaxLength += Library.randomPick(50, -50);
                    previousWallLength = secondRowWallLength;
                }
                wallMinLength = 150;
                wallMaxLength = 300;
                rowStartY += interval * 2;  // Start point for next roll of walls at constant interval interval.
            }
        }
    }

    private void createOutline() {  // Create outline walls.
        outline.add(new Rectangle(0, 0, mazeX, 10));
        outline.add(new Rectangle(1348, 0, 10, mazeY));
        outline.add(new Rectangle(10, 705, mazeX, 10));
        outline.add(new Rectangle(0, 0, 10, mazeY));
    }

    public List<Rectangle> getWallsCoordinates() {
        return walls;
    }

    public List<Rectangle> getOutlineCoordinates() {
        return outline;
    }

    int getMazeWidth() {
        return mazeX;
    }
}
