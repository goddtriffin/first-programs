package net.magnusfrater.pong.entity;

import net.magnusfrater.pong.game.GameFrame;
import net.magnusfrater.pong.gui.Level;
import net.magnusfrater.pong.input.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {

    public int x,y;
    public int width,height;
    private int speed;
    private Color c;

    public int playerNum;
    public int score;

    public Player(int playerNum){
        width = (int)(Level.lineWidth*1.5);
        height = width * 8;

        speed = 8;

        c = Color.WHITE;

        this.playerNum = playerNum;
        score = 0;

        switch (playerNum){
            case 1: x = 30;
                break;
            default: x = GameFrame.WIDTH-35 - width;
                break;
        }
        y = (GameFrame.HEIGHT / 2) - (height / 2);
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(c);
        g2d.fillRect(x,y,width,height);
    }

    public void tick(Ball b){
        if (playerNum == 1){
            if (Keyboard.isKeyDown(KeyEvent.VK_W)){ //up
                y -= speed;
            }
            if (Keyboard.isKeyDown(KeyEvent.VK_S)){ //down
                y += speed;
            }
        }

        if (playerNum == 2){
            if (Keyboard.isKeyDown(KeyEvent.VK_UP)){ //up
                y -= speed;
            }
            if (Keyboard.isKeyDown(KeyEvent.VK_DOWN)){ //down
                y += speed;
            }
        }

        if (playerNum == 3){
            if (b.y < y+(height/2)){
                y -= speed;
            }
            if (b.y > y+(height/2)){
                y += speed;
            }
        }

        if (y< Level.ceiling.y+Level.ceiling.height) y = Level.ceiling.y+Level.ceiling.height; //ceiling collision detection
        if (y+height > Level.floor.y) y = Level.floor.y - height; //floor collision detection
    }
}
