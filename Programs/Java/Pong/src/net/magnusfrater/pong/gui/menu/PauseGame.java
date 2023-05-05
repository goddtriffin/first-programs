package net.magnusfrater.pong.gui.menu;

import net.magnusfrater.pong.game.GamePanel;
import net.magnusfrater.pong.gui.StringWriter;
import net.magnusfrater.pong.input.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PauseGame extends Menu {

    private long iTime;
    private long ns = 1000000000 / 10;

    public PauseGame(){
        options = new String[]{"PAUSED"};
        selected = 0;

        iTime = System.nanoTime();
    }

    @Override
    public void tick(){
        long fTime = System.nanoTime();
        if (fTime-iTime>=ns){
            if (Keyboard.isKeyDown(KeyEvent.VK_P)){
                GamePanel.paused = !GamePanel.paused;
                iTime = fTime;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2d){
        if (GamePanel.paused)
            StringWriter.draw(g2d, options[0],199,360,150,"Verdana",Font.PLAIN,Color.WHITE);
    }
}
