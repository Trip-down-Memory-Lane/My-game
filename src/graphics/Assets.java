package graphics;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int CROP_WIDTH = 120;
    private static final int CROP_HEIGHT = 130;
//    private static final String heroSheetPath = "resources/hero.png";
//    private static final String badGuyPath = "resources/craft.gif";

    public static BufferedImage playerUp, playerLeft, playerDown, playerRight, badGuy;

    public static void init() {
        SpriteSheet heroSheet = new SpriteSheet(ImageLoader.loadImage("/Hero.png"));
        playerUp = heroSheet.crop(0, 2 * CROP_HEIGHT, CROP_WIDTH, CROP_HEIGHT);
        playerLeft = heroSheet.crop(0, CROP_HEIGHT, CROP_WIDTH, CROP_HEIGHT);
        playerDown = heroSheet.crop(0, 0, CROP_WIDTH, CROP_HEIGHT);
        playerRight = heroSheet.crop(0, 3 * CROP_HEIGHT, CROP_WIDTH, CROP_HEIGHT);

        badGuy = ImageLoader.loadImage("/craft.gif");
    }
}
