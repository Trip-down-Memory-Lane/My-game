package textures;

import javafx.scene.shape.Rectangle;

import java.awt.image.BufferedImage;

public class Assets {

    public Assets() {

        initAssets();
    }

    public static BufferedImage[] playerUp, playerLeft, playerDown, playerRight;
    public static BufferedImage badGuyUp, badGuyLeft, badGuyDown, badGuyRight;
    public static BufferedImage artefact;
    static BufferedImage heroPortrait, heroSprint;

    static SpriteSheet wall, floor;

    private static void initAssets() {

        SpriteSheet heroSheet = new SpriteSheet(ImageLoader.loadImage("/animation4.png"));
        int width = 146 / 3;
        int height = 216 / 4;
        playerUp = new BufferedImage[4];
        playerLeft = new BufferedImage[4];
        playerDown = new BufferedImage[4];
        playerRight = new BufferedImage[4];

        int index;
        for (int i = 1, leftI = 2; i <= 3; i++, leftI--) {
            index = i - 1;
            playerDown[index] = heroSheet.crop(index * width, 0, width, height);
            playerLeft[index] = heroSheet.crop(index * width, height, width, height);
            playerRight[index] = heroSheet.crop(index * width, 2 * height, width, height);
            playerUp[index] = heroSheet.crop(index * width, 3 * height, width, height);
        }
        playerDown[3] = heroSheet.crop(width, 0, width, height);
        playerLeft[3] = heroSheet.crop(width, height, width, height);
        playerRight[3] = heroSheet.crop(width, 2 * height, width, height);
        playerUp[3] = heroSheet.crop(width, 3 * height, width, height);

        heroSprint = heroSheet.crop(149, 221, 40, 20);

        SpriteSheet badGuySheet = new SpriteSheet((ImageLoader.loadImage("/badGuySheet.png")));
        badGuyUp = badGuySheet.crop(4, 204, 42, 48);
        badGuyLeft = badGuySheet.crop(76, 79, 42, 48);
        badGuyDown = badGuySheet.crop(76, 16, 42, 48);
        badGuyRight = badGuySheet.crop(76, 143, 42, 48);

        artefact = ImageLoader.loadImage("/artefact.png");

        SpriteSheet bigHero = new SpriteSheet(ImageLoader.loadImage("/Hero_FULL_SCALE.png"));
        heroPortrait = bigHero.crop(10, 10, 90, 80);

        wall = new SpriteSheet(ImageLoader.loadImage("/wall1.png"));

        floor = new SpriteSheet(ImageLoader.loadImage("/floor.png"));
    }
}


