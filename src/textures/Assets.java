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
    static BufferedImage heroPortrait, heroSprint;

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



        wall = new SpriteSheet(ImageLoader.loadImage("/wall.png"));

        floor = ImageLoader.loadImage("/floor.png");

        outline = ImageLoader.loadImage("/outline.png");

        heroPanel = ImageLoader.loadImage("/heroPanel.png");
        
        menu = ImageLoader.loadImage("/menu.png");

        gameOver = ImageLoader.loadImage("/gameOver.png");

        artefact = ImageLoader.loadImage("/item.png").getSubimage(60, 150, 60, 60);


    }
}


