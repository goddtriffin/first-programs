package com.saintsquireen.game;

import javax.swing.*;
import java.awt.*;

public class TitlePanel extends JPanel {

    int WIDTH,HEIGHT;

    public TitlePanel(int WIDTH,int HEIGHT){
        setFocusable(true);
        setBackground(Color.WHITE);
        setVisible(true);

        this.WIDTH=WIDTH; this.HEIGHT=HEIGHT;
    }
}
