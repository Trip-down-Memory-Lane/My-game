package lib;

public class Library {

    public static int randomInt(int min, int max) {
        return min + (int)(Math.random() * (max - min) + 1);
    }
}
