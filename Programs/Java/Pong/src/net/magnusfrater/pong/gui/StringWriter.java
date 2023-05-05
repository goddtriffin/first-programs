package net.magnusfrater.pong.gui;

import java.awt.*;

public class StringWriter {

    public StringWriter(){
    }

    public static void draw(Graphics2D g2d, String text, int x,int y, int size, String font, int style, Color mainColor){
        g2d.setFont(new Font(font, style, size));

        g2d.setColor(mainColor);
        g2d.drawString(text, x,y);
    }

    public static void draw(Graphics2D g2d, String text, int x,int y, int size, String font, int style, Color mainColor, int offset, Color offsetColor){
        g2d.setFont(new Font(font, style, size));

        g2d.setColor(offsetColor);
        g2d.drawString(text, x + offset,y + offset);
        g2d.setColor(mainColor);
        g2d.drawString(text, x,y);
    }
}