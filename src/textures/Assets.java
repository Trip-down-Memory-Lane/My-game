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

    public static int heroWidth;
    public static int heroHeight;
    public static int badGuyWidth;
    public static int badGuyHeight;

    private static void initAssets() {

        SpriteSheet heroSheet = new SpriteSheet(ImageLoader.loadImage("/animation.png"));
        int width = 32;
        int height = 32;
        playerUp = new BufferedImage[4];
        playerLeft = new BufferedImage[4];
        playerDown = new BufferedImage[4];
        playerRight = new BufferedImage[4];

        int index;
        for (int i = 1, leftI = 2; i <= 3; i++, leftI--) {
            index = i - 1;
            playerDown[index] = heroSheet.crop(index * width, 0, width, height);
            playerLeft[index] = heroSheet.crop(leftI * width, height, width, height);
            playerRight[index] = heroSheet.crop(index * width, 2 * height, width, height);
            playerUp[index] = heroSheet.crop(index * width, 3 * height, width, height);
        }
        playerDown[3] = heroSheet.crop(width, 0, width, height);
        playerLeft[3] = heroSheet.crop(width, height, width, height);
        playerRight[3] = heroSheet.crop(width, 2 * height, width, height);
        playerUp[3] = heroSheet.crop(width, 3 * height, width, height);

        SpriteSheet sprintSheet = new SpriteSheet(ImageLoader.loadImage("/heroSheet.png"));
        heroSprint = sprintSheet.crop(100, 393, 40, 20);
//        playerUp = heroSheet.crop(6, 113, 36, 44);
//        playerLeft = heroSheet.crop(8, 60, 36, 44);
//        playerDown = heroSheet.crop(4, 6, 36, 44);
//        playerRight = heroSheet.crop(6, 164, 36, 44);

        SpriteSheet badGuySheet = new SpriteSheet((ImageLoader.loadImage("/badGuySheet.png")));
        badGuyUp = badGuySheet.crop(4, 204, 42, 48);
        badGuyLeft = badGuySheet.crop(76, 79, 42, 48);
        badGuyDown = badGuySheet.crop(76, 16, 42, 48);
        badGuyRight = badGuySheet.crop(76, 143, 42, 48);

        artefact = ImageLoader.loadImage("/artefact.png");

        SpriteSheet bigHero = new SpriteSheet(ImageLoader.loadImage("/Hero_FULL_SCALE.png"));
        heroPortrait = bigHero.crop(10, 10, 90, 80);
    }
}
//    SpriteSheet heroSheet = new SpriteSheet(ImageLoader.loadImage("/heroSheet.png"));
//        playerUp = heroSheet.crop(6, 113, 36, 42);
//        playerLeft = heroSheet.crop(8, 60, 34, 44);
//        playerDown = heroSheet.crop(4, 6, 36, 45);
//        playerRight = heroSheet.crop(6, 164, 34, 44);
//
//        SpriteSheet badGuySheet = new SpriteSheet((ImageLoader.loadImage("/badGuySheet.png")));
//        badGuyUp = badGuySheet.crop(4, 204, 43, 48);
//        badGuyLeft = badGuySheet.crop(216, 80, 26, 46);
//        badGuyDown = badGuySheet.crop(76, 16, 42, 48);
//        badGuyRight = badGuySheet.crop(207, 145, 26, 46);
//    }

