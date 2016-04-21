//######################################################################################################################
// Item.java extends Sprite to implement user-input-driven moving function - 'move()'. Check /src/game/InputHandler for
// more info on input.
//######################################################################################################################
package game.objects;

import game.Game;
import javafx.scene.shape.Rectangle;
import textures.Assets;

import java.util.List;

// Тук няма смисъл да еьтендваш 'Sprite'. Ненужно е и е погрешно. Идеята на Sprite( Hero, BadGuy ) е, че това са сходни класове. Тоест примерно и двата класа се движат. Правят сходни изчисления, за да се местят. Принтират се по еднакъв начин и се ъпдейтват по еднакъв начин. Затова те имат общи методи в Sprite. Класът 'Artefact' или 'Item' просто трябва да генерира четири статични артефакта. Тоест по същност той много повече прилича на 'Maze'. Разликата идва, че трябва да ги махнеш като геройчето мине през тях.
public class Item extends Sprite {
    // Boolean variables to determine weather the 'Hero" is moving in the specified direction or not. They are influenced by input (set to true/false) and collisions (set to false)

    public static List<Rectangle> items;
    public static Item starItem, starItemB, starItemC, starItemD;

    public Item(int x, int y) { // Spawn class
        super(x, y);
        createItems();
    }

    private void initItems() {
        createItems();
        image = Assets.artefact;
        imageWidth = 20;
        imageHeight = 20;
        updateImage();
        updateHitBox();
    }

    private void createItems() {
        x = 645 + (int) (Math.random() * 655);
        y = seedRandomPostion();
        imageWidth = 20;
        imageHeight = 20;
        updateHitBox();
        for (Rectangle wall: Game.maze.getOutlineCoordinates()) {
            while (this.getHitBox().intersects(wall.getBoundsInLocal())) {
                x = 645 + (int) (Math.random() * 655);
                y = seedRandomPostion();
            }
        }
        starItem = new Item(x, y);

        x = 645 + (int) (Math.random() * 655);
        y = seedRandomPostion();
        for (Rectangle wall: Game.maze.getOutlineCoordinates()) {
            while (this.getHitBox().intersects(wall.getBoundsInLocal())) {
                x = 645 + (int) (Math.random() * 655);
                y = seedRandomPostion();
            }

        }
        starItemB = new Item(x, y);

        x = 45 + (int) (Math.random() * 655);
        y = seedRandomPostion();
        for (Rectangle wall: Game.maze.getOutlineCoordinates()) {
            while (this.getHitBox().intersects(wall.getBoundsInLocal())) {
                x = 45 + (int) (Math.random() * 655);
                y = seedRandomPostion();
            }

        }
        starItemC = new Item(x, y);

        x = 45 + (int) (Math.random() * 655);
        y = seedRandomPostion();
        for (Rectangle wall: Game.maze.getOutlineCoordinates()) {
            while (this.getHitBox().intersects(wall.getBoundsInLocal())) {
                x = 45 + (int) (Math.random() * 655);
                y = seedRandomPostion();
            }

        }
        starItemD = new Item(x, y);
        initItems();
    }

    private int seedRandomPostion() {
        int positionY = 40;
        int ROW_OFFSET = 77;
        int row ;
        row = 3 + (int)(Math.random() * 6);

        positionY += (ROW_OFFSET * row);
        return positionY;
    }


}