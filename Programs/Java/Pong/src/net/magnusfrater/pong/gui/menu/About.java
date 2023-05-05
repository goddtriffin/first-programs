package net.magnusfrater.pong.gui.menu;

import net.magnusfrater.pong.game.GameFrame;
import net.magnusfrater.pong.game.GamePanel;
import net.magnusfrater.pong.gui.StringWriter;
import net.magnusfrater.pong.input.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;

public class About extends Menu{

    public About(){
        options = new String[]{"Back"};
        selected = 0;
    }

    @Override
    public void tick(){
        if (Keyboard.isKeyDown(KeyEvent.VK_SPACE) || Keyboard.isKeyDown(KeyEvent.VK_ENTER)){
            GamePanel.setMainMenu();
        }
    }

    @Override
    public void draw(Graphics2D g2d){
        StringWriter.draw(g2d,"INSTRUCTIONS", GameFrame.WIDTH/2 - 233,75,60,"Verdana",Font.PLAIN,Color.WHITE);
        g2d.fillRect(GameFrame.WIDTH/2 - 220, 90, 448, 5);

        StringWriter.draw(g2d,"1-PLAYER: Left is human controlled, right is a (really, REALLY hard) AI",17,150,27,"Verdana",Font.PLAIN,Color.WHITE);
        StringWriter.draw(g2d,"~P1 (HUMAN): W=/\\  S=\\/",80,180,22,"Verdana",Font.PLAIN,Color.WHITE);
        StringWriter.draw(g2d,"2-PLAYER: Both sides are human controlled",17,220,27,"Verdana",Font.PLAIN,Color.WHITE);
        StringWriter.draw(g2d,"~P1 (HUMAN): W=/\\  S=\\/",80,250,22,"Verdana",Font.PLAIN,Color.WHITE);
        StringWriter.draw(g2d,"~P2 (HUMAN): UP-ARROW=/\\  DOWN-ARROW=\\/",80,280,22,"Verdana",Font.PLAIN,Color.WHITE);

        StringWriter.draw(g2d,"Programming: Todd Griffin",270,357,35,"Verdana",Font.PLAIN,Color.WHITE);
        StringWriter.draw(g2d,"Artwork: Todd Griffin",367,397,35,"Verdana",Font.PLAIN,Color.WHITE);
        StringWriter.draw(g2d,"2016",455,437,35,"Verdana",Font.PLAIN,Color.WHITE);

        StringWriter.draw(g2d,"> BACK",GameFrame.WIDTH/2 - 140,530,55,"Verdana",Font.PLAIN,Color.WHITE);
    }
}
