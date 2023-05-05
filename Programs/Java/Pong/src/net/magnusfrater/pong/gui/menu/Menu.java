package net.magnusfrater.pong.gui.menu;

import net.magnusfrater.pong.input.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Menu {

    public String[] options;
    public int selected;

    public boolean onCooldown = false;
    public long iTime;
    public long ns = 1000000000 / 10;

    public void tick(){
    }

    public void cycle(){
        if (!onCooldown){
            if (Keyboard.isKeyDown(KeyEvent.VK_W) || Keyboard.isKeyDown(KeyEvent.VK_UP)){
                selected--;

                if (selected < 0)
                    selected = options.length-1;

                onCooldown = true;
                iTime = System.nanoTime();
            }

            if (Keyboard.isKeyDown(KeyEvent.VK_S) || Keyboard.isKeyDown(KeyEvent.VK_DOWN)){
                selected++;

                if (selected > options.length -1)
                    selected = 0;

                onCooldown = true;
                iTime = System.nanoTime();
            }
        } else {
            long fTime = System.nanoTime();
            if (fTime-iTime>=ns){
                onCooldown = false;
            }
        }
    }

    public void draw(Graphics2D g2d){
    }
}
