package net.magnusfrater.tds.game;

import javax.swing.*;

public class GameFrame extends JFrame {

    public static final int width = 1000;
    public static final int height = width / 16 * 10;

    private GamePanel gp;

    public GameFrame () {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width,height);
        setResizable(false);
        setLocationRelativeTo(null);

        setTitle("Time Based Fast Paced Top Down Shooter Demo");

        gp = new GamePanel();
        add(gp);

        setVisible(true);
    }

    public static void main (String[] args) {
        GameFrame gf = new GameFrame();
    }
}