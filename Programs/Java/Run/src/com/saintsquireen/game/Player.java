package com.saintsquireen.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    private int screenWidth, floorY;

    private BufferedImage still, left1,left2, right1,right2;
    private int runSeq;

    private boolean runLeft, runRight;

    protected int health;
    private BufferedImage heart;

    protected int invTimer; //invincibility timer: keeps track of time left of invincibility

    public Player(int screenWidth, int floorY){
        this.screenWidth = screenWidth;
        this.floorY = floorY;

        this.width = 25; this.height = this.width*2;
        this.x = (screenWidth/2) - (this.width/2);
        this.y = floorY - this.height;

        this.vX = 5;

        runSeq = 0;
        runLeft = false; runRight = false;

        health = 10;

        invTimer = 0;
    }

    private void setPlayerArt(){
        try{
            still = ImageIO.read(getClass().getResourceAsStream("/player-still.PNG"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/player-left1.PNG"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player-left2.PNG"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/player-right1.PNG"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player-right2.PNG"));

            heart = ImageIO.read(getClass().getResourceAsStream("/heart.PNG"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void move(){
        if (runLeft) x-=vX;
        if (runRight) x+=vX;

        if (this.x<0) this.x = screenWidth - this.width;
        if (this.x + this.width > screenWidth) this.x = 0;
    }

    protected void draw(Graphics window){
        if (still == null){
            setPlayerArt();
        }

        for (int i=0; i<health; i++){
            window.drawImage(heart, screenWidth - 45*i - 50,15, 35,35,null);
        }

        if (runLeft){
            if (runSeq>0 && runSeq<4) window.drawImage(left1, x,y, width,height, null);
            if (runSeq>3 && runSeq<7) window.drawImage(left2, x,y, width,height, null);
        }else if (runRight){
            if (runSeq>0 && runSeq<4) window.drawImage(right1, x,y, width,height, null);
            if (runSeq>3 && runSeq<7) window.drawImage(right2, x,y, width,height, null);
        }else{
            window.drawImage(still, x,y, width,height, null);
        }

        runSeq++;
        if (runSeq>6) runSeq = 1;
    }

    public boolean getRunLeft(){ return this.runLeft; } //get/set runLeft
    public void setRunLeft(boolean runLeft){ this.runLeft = runLeft; }
    public boolean getRunRight(){ return this.runRight; } //get/set runRight
    public void setRunRight(boolean runRight){ this.runRight = runRight; }
}