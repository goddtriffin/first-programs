package net.magnusfrater;

import javax.swing.*;

public class Toddworld extends JFrame {

    //frame
    private final int fWidth = 1000, fHeight = fWidth/16*10;

    GamePanel gp;

    public Toddworld(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(fWidth,fHeight);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Toddworld: by Todd Griffin");

        gp = new GamePanel();
        add(gp);

        setVisible(true);
    }

    public static void main(String[] args){
        Toddworld tw = new Toddworld();
    }
}
