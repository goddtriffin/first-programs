package net.magnusfrater.pong.game;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    // Made by Todd Griffin

    public static int WIDTH = 1000;
    public static int HEIGHT = WIDTH/16*10;

    public GameFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(WIDTH,HEIGHT));
        setResizable(false);
        setLocationRelativeTo(null);

        setTitle("Pong: By Todd Griffin");

        GamePanel gp = new GamePanel();
        add(gp);

        setVisible(true);
    }

    public static void main(String[] args){
        GameFrame gf = new GameFrame();
    }
}
