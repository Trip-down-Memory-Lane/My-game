//######################################################################################################################
// Item.java extends Sprite to implement user-input-driven moving function - 'move()'. Check /src/game/InputHandler for
// more info on input.
//######################################################################################################################
package game.objects;

import textures.Assets;

public class Item extends Sprite {
    // Boolean variables to determine weather the 'Hero" is moving in the specified direction or not. They are influenced by input (set to true/false) and collisions (set to false)

    public Item(int x, int y) { // Spawn class
        super(x, y);
        initItem();
    }

    private void initItem() {
        image = Assets.starItem;
        getImageDimensions();
    }
}