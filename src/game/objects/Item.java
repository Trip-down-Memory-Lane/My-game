//######################################################################################################################
// Item.java extends Sprite to implement user-input-driven moving function - 'move()'. Check /src/game/InputHandler for
// more info on input.
//######################################################################################################################
package game.objects;

import textures.Assets;
// Тук няма смисъл да еьтендваш 'Sprite'. Ненужно е и е погрешно. Идеята на Sprite( Hero, BadGuy ) е, че това са сходни класове. Тоест примерно и двата класа се движат. Правят сходни изчисления, за да се местят. Принтират се по еднакъв начин и се ъпдейтват по еднакъв начин. Затова те имат общи методи в Sprite. Класът 'Artefact' или 'Item' просто трябва да генерира четири статични артефакта. Тоест по същност той много повече прилича на 'Maze'. Разликата идва, че трябва да ги махнеш като геройчето мине през тях.
public class Item extends Sprite {
    // Boolean variables to determine weather the 'Hero" is moving in the specified direction or not. They are influenced by input (set to true/false) and collisions (set to false)

    public Item(int x, int y) { // Spawn class
        super(x, y);
        initItem();
    }

    private void initItem() {
        image = Assets.artefact;
        imageWidth = 20;
        imageHeight = 20;
        updateImage();
    }
}