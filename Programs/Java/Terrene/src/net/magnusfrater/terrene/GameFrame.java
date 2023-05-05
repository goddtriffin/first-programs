package net.magnusfrater.terrene;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public final static Dimension size = new Dimension(1000,1000/16*10);
    public final static String TITLE = "Terrene";

    private GamePanel gp;

    public GameFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
        setResizable(false);

        setLocationRelativeTo(null);

        setTitle(TITLE);

        gp = new GamePanel();
        add(gp);

        pack();

        setVisible(true);
    }

    public static void main(String[] args){
        GameFrame gf = new GameFrame();
    }
}
