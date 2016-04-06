package lib;

class Library {

    static int randomInt(int min, int max) {

        return min + (int)(Math.random() * (max - min) + 1);
    }
}
