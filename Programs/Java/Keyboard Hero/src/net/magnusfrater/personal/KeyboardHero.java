package net.magnusfrater.personal;

import javax.swing.*;

public class KeyboardHero extends JFrame{

    public int WIDTH=1000,HEIGHT=WIDTH/16*10;

    MainPanel mp;

    public KeyboardHero(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Keyboard Hero: By Todd Griffin");
        setLocationRelativeTo(null);
        setVisible(true);

        mp = new MainPanel();
        getContentPane().add(mp);
    }

    public static void main(String[] args){
        KeyboardHero kh = new KeyboardHero();
    }
}

//Game Manager isn't getting the right values for width and height because it hasn't really been initialised yet. Have to find a work around to get the current width and height through to the Game Manager (and subsequently Guitar, Button, etc). They will not display otherwise.