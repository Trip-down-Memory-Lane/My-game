//######################################################################################################################
// Maze.java - creates outline (frame border) walls and random maze walls on startup. Stores each wall as Rectangle in
// list.
//######################################################################################################################
package game.objects;

import frame.Board;

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
    private static int outlineThickness = 20;

    public static int mazeMargin;
    static int wallMinLength = 150;
    static final int doorMaxLength = 100;    // Length of the passages between walls

    public Maze(int x, int y) {
        mazeX = x - 2 * outlineThickness;
        mazeY = y - 2 * outlineThickness;
        initMaze();
    }

    private void initMaze() {
        walls = new ArrayList<>();
        outline = new ArrayList<>();
        createMaze();
        createOutline();
    }

    private void createMaze() {    // Creates maze
        mazeMargin = 70;
        int wallThickness = 33;
        int mazeHeight = mazeY - mazeMargin;
        int interval = (mazeHeight - 80) / 6;
        int rowMargin = outlineThickness - 3;

        int rowStartX;
        int rowStartY = interval + mazeMargin;
        int wallLength;
        int randomWallToIncrease = Library.randomPick(1, 2, 3);

        for (int rowWithWalls = 0; rowWithWalls < 5; rowWithWalls++) {

            int mazeEntranceWall = (mazeX - 100) / 2 + 3;
            int mazeEntranceWallX = mazeX / 2 + doorMaxLength / 2 + rowMargin;
            if (rowWithWalls == 0) {
//                walls.add(new Rectangle(rowMargin, mazeMargin, mazeEntranceWall, wallThickness));
                walls.add(new Rectangle(mazeEntranceWallX, 0, mazeEntranceWall, wallThickness + mazeMargin));
            } else if (rowWithWalls == 4) {
                walls.add(new Rectangle(rowMargin, mazeHeight + mazeMargin, mazeEntranceWall, wallThickness));
                walls.add(new Rectangle(mazeEntranceWallX, mazeHeight + mazeMargin, mazeEntranceWall, wallThickness));
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
                    wallLength += 3;

                    walls.add(new Rectangle(rowLength, rowStartY, wallLength, wallThickness));

                    secondRowStartY = rowStartY + interval;
                    secondRowWallLength = rowStartX + wallLength + doorMaxLength;
                    if (Math.abs(previousWallLength - secondRowWallLength) < doorMaxLength) {
                        secondRowWallLength = wallMaxLength;
                    }
                    secondRowWallLength += 3;

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
        outline.add(new Rectangle(0, 0, 740, outlineThickness));
        outline.add(new Rectangle(740, 0, Board.canvasX - 740, 90));
        outline.add(new Rectangle(Board.canvasX - outlineThickness, 0, outlineThickness, Board.canvasY));
        outline.add(new Rectangle(outlineThickness, Board.canvasY - outlineThickness, Board.canvasX, outlineThickness));
        outline.add(new Rectangle(0, 0, outlineThickness, Board.canvasY));
    }

    public List<Rectangle> getWallsCoordinates() {
        return walls;
    }

    public List<Rectangle> getOutlineCoordinates() {
        return outline;
    }

    public int getOutlineThickness() {
        return outlineThickness;
    }

    public int getMazeMargin() {
        return mazeMargin;
    }

    int getMazeWidth() {
        return mazeX;
    }
}
