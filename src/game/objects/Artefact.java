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
                startX = 30;
                endX = 654;
                startY = 30;
                endY = 355;
                break;
            case "two":
                startX = 704;
                endX = 1338;
                startY = 30;
                endY = 355;
                break;
            case "three":
                startX = 30;
                endX = 654;
                startY = 405;
                endY = 740;
                break;
            default:
                startX = 704;
                endX = 1338;
                startY = 405;
                endY = 740;
                break;
        }

        int x = Library.randomInt(startX, endX);
        int y = Library.randomInt(startY, endY);
        artefacts.add(new Rectangle(x - offsetX, y - offsetY, artefactWidth, artefactHeight));
    }

    public List<Rectangle> getArtefacts() {
        return artefacts;
    }
}
