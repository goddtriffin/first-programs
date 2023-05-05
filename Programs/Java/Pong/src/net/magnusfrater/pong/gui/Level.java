package net.magnusfrater.pong.gui;

import net.magnusfrater.pong.entity.Player;
import net.magnusfrater.pong.game.GameFrame;
import net.magnusfrater.pong.game.GamePanel;

import java.awt.*;

public class Level {

    public static int lineWidth;

    public static Rectangle ceiling, floor;

    public Level(){
        lineWidth = 7;

        ceiling = new Rectangle(0,55, GameFrame.WIDTH, lineWidth);
        floor = new Rectangle(0,575, GameFrame.WIDTH, lineWidth);
    }

    public void tick(Player p1, Player p2){
        if (p1.score>=5 || p2.score>=5){
            GamePanel.setGameOver();
        }
    }

    public void draw(Graphics2D g2d, Player p1, Player p2){
        g2d.setColor(Color.WHITE);

        StringWriter.draw(g2d,Integer.toString(p1.score), 415,50, 60, "Verdana", Font.PLAIN,Color.WHITE); //p1 score
        StringWriter.draw(g2d,Integer.toString(p2.score), 546,50, 60, "Verdana", Font.PLAIN,Color.WHITE); //p2 score

        g2d.fillRect(ceiling.x,ceiling.y,ceiling.width,ceiling.height); //ceiling
        g2d.fillRect(floor.x,floor.y,floor.width,floor.height); //floor

        for (int i=0; i<19; i++){
            g2d.fillRect((GameFrame.WIDTH/2) - (lineWidth/2), ceiling.y + (((lineWidth*2) * i) * 2) + 4, lineWidth, lineWidth*2); //middle lne
        }
    }
}
