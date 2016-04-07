package graphics;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int CROP_WIDTH = 48;
    private static final int CROP_HEIGHT = 52;
//    private static final String heroSheetPath = "resources/hero.png";
//    private static final String badGuyPath = "resources/craft.gif";
    public Assets() {
        initAssets();
    }

    public static BufferedImage playerUp, playerLeft, playerDown, playerRight, badGuy;

    private static void initAssets() {
        SpriteSheet heroSheet = new SpriteSheet(ImageLoader.loadImage("/hero1.png"));
        playerUp = heroSheet.crop(0, 2 * CROP_HEIGHT, CROP_WIDTH, CROP_HEIGHT);
        playerLeft = heroSheet.crop(0, CROP_HEIGHT, CROP_WIDTH, CROP_HEIGHT);
        playerDown = heroSheet.crop(0, 0, CROP_WIDTH, CROP_HEIGHT);
        playerRight = heroSheet.crop(0, 3 * CROP_HEIGHT, CROP_WIDTH, CROP_HEIGHT);

        badGuy = ImageLoader.loadImage("/craft.gif");
    }
}
