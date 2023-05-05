package net.magnusfrater.project;

import javax.swing.*;

public class Miniworld extends JFrame {

    //GENERAL
    final public int WIDTH=1000,HEIGHT=WIDTH/16*10;
    private MainPanel mp = new MainPanel();

    public Miniworld(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Miniworld: By Todd Griffin");
        setLocationRelativeTo(null);
        setVisible(true);

        mp = new MainPanel();
        getContentPane().add(mp);
    }

    public static void main(String[] args){
        Miniworld mw = new Miniworld();
    }
}
