package net.magnusfrater.pong.gui.menu;

import net.magnusfrater.pong.game.GameFrame;
import net.magnusfrater.pong.game.GamePanel;
import net.magnusfrater.pong.gui.StringWriter;
import net.magnusfrater.pong.input.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOver extends Menu{

    private int playerWon;
    private int playerCount;

    public GameOver(int playerWon, int playerCount){
        options = new String[]{"RESTART","MAIN MENU"};
        selected = 0;

        this.playerWon = playerWon;
        this.playerCount = playerCount;
    }

    @Override
    public void tick(){
        cycle();

        if (Keyboard.isKeyDown(KeyEvent.VK_SPACE) || Keyboard.isKeyDown(KeyEvent.VK_ENTER)){
            switch(selected){
                case 0:
                    if (playerCount == 1){
                        GamePanel.setNewGame(1);
                    }else{
                        GamePanel.setNewGame(2);
                    }
                    break;
                case 1:
                    GamePanel.setMainMenu();
                    break;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2d){
        if (playerWon == 1){
            StringWriter.draw(g2d,"PLAYER 1 WINS!", 25, 125, 115, "Verdana",Font.PLAIN,Color.WHITE);
        }else {
            StringWriter.draw(g2d,"PLAYER 2 WINS!", 25, 125, 115, "Verdana",Font.PLAIN,Color.WHITE);
        }

        g2d.fillRect(GameFrame.WIDTH/2 - 400, 170, 750,10);

        for (int i=0; i<options.length; i++){
            String option = options[i];

            if (i == selected)
                option = "> "+ option;

            StringWriter.draw(g2d,option, GameFrame.WIDTH/2 - 250, 400 +(i*100), 80, "Verdana",Font.PLAIN,Color.WHITE);
        }
    }
}
