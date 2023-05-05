package com.saintsquireen.game;

import javax.swing.*;

public class Run extends JFrame {
    final private static int WIDTH = 1000;
    final private static int HEIGHT = WIDTH/16*10;

    private TitlePanel tp;
    private GamePanel gp;

    private boolean playing;

    public Run(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Run");
        setLocationRelativeTo(null);
        setVisible(true);

        playing = true;

        //tp = new TitlePanel(WIDTH,HEIGHT);
        //getContentPane().add(tp);

        gp = new GamePanel(WIDTH,HEIGHT);
        getContentPane().add(gp);

    }

    public static void main(String[] args){
        Run r = new Run();
    }
}