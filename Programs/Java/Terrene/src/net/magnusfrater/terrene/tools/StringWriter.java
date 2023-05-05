package net.magnusfrater.terrene.tools;

import java.awt.*;

public class StringWriter {

    public static void draw(Graphics g, String text, Color c, int x, int y, int offset, String font, int style, int size){
        g.setFont(new Font(font, style, size));

        g.setColor(Color.BLACK);
        g.drawString(text, x + offset,y + offset);
        g.setColor(c);
        g.drawString(text, x,y);
    }
}
