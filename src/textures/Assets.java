package textures;

import java.awt.image.BufferedImage;

public class Assets {

    public Assets() {
        initAssets();
    }

    public static BufferedImage playerUp, playerLeft, playerDown, playerRight;
    public static BufferedImage badGuyUp, badGuyLeft, badGuyDown, badGuyRight;

    private static void initAssets() {
        SpriteSheet heroSheet = new SpriteSheet(ImageLoader.loadImage("/heroSheet.png"));
        playerUp = heroSheet.crop(6, 113, 36, 42);
        playerLeft = heroSheet.crop(8, 60, 34, 44);
        playerDown = heroSheet.crop(4, 6, 36, 45);
        playerRight = heroSheet.crop(6, 164, 34, 44);

        SpriteSheet badGuySheet = new SpriteSheet((ImageLoader.loadImage("/badGuySheet.png")));
        badGuyUp = badGuySheet.crop(4, 204, 43, 48);
        badGuyLeft = badGuySheet.crop(216, 80, 26, 46);
        badGuyDown = badGuySheet.crop(76, 16, 42, 48);
        badGuyRight = badGuySheet.crop(207, 145, 26, 46);
    }
}
