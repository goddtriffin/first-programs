package com.saintsquireen.graphics;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = SCREEN_WIDTH / 16 * 10;

    Screen s;

    public Frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setResizable(false);
        setTitle("Graphics");

        init();
    }

    public void init() {
        setLocationRelativeTo(null);

        s = new Screen();
        add(s);

        setVisible(true);
    }

    public static void main(String[] args){
        new Frame();
    }
}
