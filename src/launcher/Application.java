package launcher;

import game.Board;

import java.awt.EventQueue;
import javax.swing.JFrame;


public class Application extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Application ex = new Application();
                ex.setVisible(true);
            }
        });
    }

    public Application() {
        initUI();
    }

    private void initUI() {
        add(new Board());

        getGlassPane().setVisible(true);
        setSize(900, 600);
        setResizable(false);
        setTitle("MyGame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}