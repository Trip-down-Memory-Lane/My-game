package game;

public class Launcher {

    public static void main(String[] args) {
        Game game = new Game("My game", 900, 600);
        game.start();
    }
}