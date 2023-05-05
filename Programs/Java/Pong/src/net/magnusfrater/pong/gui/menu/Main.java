package net.magnusfrater.pong.gui.menu;

import net.magnusfrater.pong.game.GameFrame;
import net.magnusfrater.pong.game.GamePanel;
import net.magnusfrater.pong.gui.StringWriter;
import net.magnusfrater.pong.input.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Main extends Menu{

    public Main(){
        options = new String[]{"1-PLAYER","2-PLAYER","ABOUT","QUIT"};
        selected = 0;
    }

    @Override
    public void tick() {
        cycle();

        if (Keyboard.isKeyDown(KeyEvent.VK_SPACE) || Keyboard.isKeyDown(KeyEvent.VK_ENTER)){
            switch (selected){
                case 0:
                    GamePanel.setNewGame(1);
                    break;
                case 1:
                    GamePanel.setNewGame(2);
                    break;
                case 2:
                    GamePanel.setAboutMenu();
                    break;
                case 3:
                    GamePanel.running = false;
                    break;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        StringWriter.draw(g2d,"PONG", GameFrame.WIDTH/2 - 285, 200, 200, "Verdana",Font.PLAIN,Color.WHITE);

        g2d.fillRect(GameFrame.WIDTH/2 - 255, 220, 520,10);

        for (int i=0; i<options.length; i++){
            String option = options[i];

            if (i == selected)
                option = "> "+ option;

            StringWriter.draw(g2d,option, GameFrame.WIDTH/2 - 140, 310 +(i*80), 60, "Verdana",Font.PLAIN,Color.WHITE);
        }
    }
}
