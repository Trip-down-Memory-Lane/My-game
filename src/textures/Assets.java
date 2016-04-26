package textures;

import javafx.scene.shape.Rectangle;

import java.awt.image.BufferedImage;

public class Assets {

    public Assets() {

        initAssets();
    }

    public static BufferedImage[] playerUp, playerLeft, playerDown, playerRight;
    public static BufferedImage[] badGuyUp, badGuyLeft, badGuyDown, badGuyRight;
    public static BufferedImage artefact;
    static BufferedImage heroPortrait[], heroSprint;

    static SpriteSheet wall;
    static BufferedImage floor, outline, menu, heroPanel, gameOver;

    private static void initAssets() {

        SpriteSheet heroSheet = new SpriteSheet(ImageLoader.loadImage("/heroSheet.png"));
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

        SpriteSheet badGuySheet = new SpriteSheet(ImageLoader.loadImage("/badGuySheet.png"));
        int badGuyWidth = 95 / 3;
        int badGuyHeight = 128 / 4;
        badGuyUp = new BufferedImage[4];
        badGuyLeft = new BufferedImage[4];
        badGuyDown = new BufferedImage[4];
        badGuyRight = new BufferedImage[4];

        for (int i = 0; i <= 3; i++) {
           badGuyDown[i] = badGuySheet.crop(i * badGuyWidth, 0, badGuyWidth, badGuyHeight);
           badGuyLeft[i] = badGuySheet.crop(i * badGuyWidth, badGuyHeight, badGuyWidth, badGuyHeight);
           badGuyRight[i] = badGuySheet.crop(i * badGuyWidth, 2 * badGuyHeight, badGuyWidth, badGuyHeight);
           badGuyUp[i] = badGuySheet.crop(i * badGuyWidth, 3 * badGuyHeight, badGuyWidth, badGuyHeight);
        }
        badGuyDown[3] = badGuySheet.crop(badGuyWidth, 0, badGuyWidth, badGuyHeight);
        badGuyLeft[3] = badGuySheet.crop(badGuyWidth, badGuyHeight, badGuyWidth, badGuyHeight);
        badGuyRight[3] = badGuySheet.crop(badGuyWidth, 2 * badGuyHeight, badGuyWidth, badGuyHeight);
        badGuyUp[3] = badGuySheet.crop(badGuyWidth, 3 * badGuyHeight, badGuyWidth, badGuyHeight);

        SpriteSheet portrait = new SpriteSheet(ImageLoader.loadImage("/heroPortrait.png"));
        heroPortrait = new BufferedImage[43];

        heroPortrait[0] = portrait.crop(0, 0, 100, 100);
        heroPortrait[1] = portrait.crop(100, 0, 100, 100);
        heroPortrait[2] = portrait.crop(200, 0, 100, 100);
        heroPortrait[3] = portrait.crop(300, 0, 100, 100);
        heroPortrait[4] = portrait.crop(400, 0, 100, 100);
        heroPortrait[5] = portrait.crop(500, 0, 100, 100);
        heroPortrait[6] = portrait.crop(600, 0, 100, 100);
        heroPortrait[7] = portrait.crop(0,100, 100, 100);
        heroPortrait[8] = portrait.crop(100,100, 100, 100);
        heroPortrait[9] = portrait.crop(200,100, 100, 100);
        heroPortrait[10] = portrait.crop(300,100, 100, 100);
        heroPortrait[11] = portrait.crop(300,100, 100, 100);
        heroPortrait[12] = portrait.crop(400,100, 100, 100);
        heroPortrait[13] = portrait.crop(500,100, 100, 100);
        heroPortrait[14] = portrait.crop(600,100, 100, 100);
        heroPortrait[15] = portrait.crop(600,100, 100, 100);

        heroPortrait[16] = portrait.crop(801, 0, 100, 100);
        heroPortrait[17] = portrait.crop(901, 0, 100, 100);
        heroPortrait[18] = portrait.crop(1001, 0, 100, 100);
        heroPortrait[19] = portrait.crop(801, 100, 100, 100);
        heroPortrait[20] = portrait.crop(901,100, 100, 100);
        heroPortrait[21] = portrait.crop(1001,100, 100, 100);
        heroPortrait[22] = portrait.crop(801, 200, 100, 100);

        heroPortrait[23] = portrait.crop(801, 0, 100, 100);
        heroPortrait[24] = portrait.crop(901, 0, 100, 100);
        heroPortrait[25] = portrait.crop(1001, 0, 100, 100);
        heroPortrait[26] = portrait.crop(801, 100, 100, 100);
        heroPortrait[27] = portrait.crop(901,100, 100, 100);
        heroPortrait[28] = portrait.crop(1001,100, 100, 100);
        heroPortrait[29] = portrait.crop(801, 200, 100, 100);

        heroPortrait[30] = portrait.crop(801, 0, 100, 100);
        heroPortrait[31] = portrait.crop(901, 0, 100, 100);
        heroPortrait[32] = portrait.crop(1001, 0, 100, 100);
        heroPortrait[33] = portrait.crop(801, 100, 100, 100);
        heroPortrait[34] = portrait.crop(901,100, 100, 100);
        heroPortrait[35] = portrait.crop(1001,100, 100, 100);
        heroPortrait[36] = portrait.crop(801, 200, 100, 100);
//        int portW = 100;
//        int portH = 100;
//        int indexX = 0;
//        int indexY = 0;
//        for (int i = 0; i < 16; i++) {
//            indexX = i % 7;
//            if (i > 7) {
//                indexY = 1;
//            }
//            if (i == 10) {
//                i = 11;
//            }
//            heroPortrait[i] = portrait.crop(indexX * portH,indexY * portW, portH, portW);
//        }

        wall = new SpriteSheet(ImageLoader.loadImage("/wall.png"));

        floor = ImageLoader.loadImage("/floor.png");

        outline = ImageLoader.loadImage("/outline.png");

        heroPanel = ImageLoader.loadImage("/heroPanel.png");
        
        menu = ImageLoader.loadImage("/menu.png");

        gameOver = ImageLoader.loadImage("/gameOver.png");

        artefact = ImageLoader.loadImage("/item.png").getSubimage(130, 0, 130, 130);


    }
}


