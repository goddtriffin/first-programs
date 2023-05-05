package com.saintsquireen.graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Screen extends JPanel {

    private BufferedImage img;

    public Screen() {

        try{
            img = ImageIO.read(getClass().getResourceAsStream("/IMG_1624.PNG"));
        }catch(IOException e){
            e.printStackTrace();
        }

        repaint();
    }

    public void paint(Graphics g) {
        g.drawImage(img,0,0,null);
    }

}
