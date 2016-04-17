//######################################################################################################################
// Artefact.java
//######################################################################################################################
package game.objects;

import lib.Library;
import textures.Assets;

import javafx.scene.shape.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Artefact{

    private final BufferedImage image = Assets.artefact;
    private int artefactWidth = image.getWidth();
    private int artefactHeight = image.getHeight();
    private int offsetX = artefactHeight/ 2;
    private int offsetY = artefactHeight / 2;

    private static List<Rectangle> artefacts;

    public Artefact() { // Spawn class
        initArtefact();
    }

    private void initArtefact() {
        artefacts = new ArrayList<>();
        createArtefact("one");
        createArtefact("two");
        createArtefact("three");
        createArtefact("four");
    }

    private void createArtefact(String square) {
        int startX;
        int endX;
        int startY;
        int endY;
        switch (square) {
            case "one":
                startX = 0;
                endX = 684;
                startY = 0;
                endY = 385;
                break;
            case "two":
                startX = 684;
                endX = 1368;
                startY = 0;
                endY = 385;
                break;
            case "three":
                startX = 0;
                endX = 684;
                startY = 385;
                endY = 770;
                break;
            default:
                startX = 684;
                endX = 1368;
                startY = 385;
                endY = 770;
                break;
        }

        int x = Library.randomInt(startX, endX);
        int y = Library.randomInt(startY, endY);
        artefacts.add(new Rectangle(x - offsetX, y - offsetY, artefactWidth, artefactHeight));
    }

    public List<Rectangle> getArtefacts() {
        return artefacts;
    }
//
//    public int getWidth() {
//        return artefactWidth;
//    }
//    public int artefactHeight() {
//        return artefactHeight;
//    }
//    public int getOffsetX() {
//        return offsetX;
//    }
//    public int getOffsetY() {
//        return offsetY;
//    }

}

///*TODO:
//        /1. 1 random number (1 - 5) for y.
//        /2. 1 random number (5 - 895) for itemX.
//        /3. Switch y random number (line 1).
//        /4  ^ calculate itemY
//        /5 Set first item using itemX and itemY.
//        /6 Steps 1 to 5, put into a function
//        /7 Repeat this function for the other 2 items.
//
//        */
//
//    int itemX, itemY;
//    int randomNumber = 1; /// add random() functions. 1 + (int)(Math.random() * 5);
//itemX = 645 + (int)(Math.random() * 655); /// RandomNumber;
//        itemY = 45 + (int)(Math.random() * 350);
////        switch (randomNumber) {
////            case 1: itemY = 45 + (int)(Math.random() * 700); break;
////            case 2: itemY = 127; break;
////            case 3: itemY = 205; break;
////            case 4: itemY = 295; break;
////            case 5: itemY = 382; break;
////            default: itemY = 0;itemX=0; break;
////        }
//
//        starItem = new Artefact(itemX, itemY);// where to spawn
//        // call function for new itemX and itemY;
//        itemX = 645 + (int)(Math.random() * 655);
//        itemY = 350 + (int)(Math.random() * 350);
//        starItemB = new Artefact(itemX , itemY); // where to spawn
//        // call function for new itemX and itemY;
//        itemX = 45 + (int)(Math.random() * 600);
//        itemY = 350 + (int)(Math.random() * 350);
//        starItemC = new Artefact(itemX , itemY); // where to spawn
//
//        collision = new Collision();
//        drawer = new Drawer();