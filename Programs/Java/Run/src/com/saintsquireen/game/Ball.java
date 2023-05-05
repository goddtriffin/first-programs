package com.saintsquireen.game;

import java.awt.*;

public class Ball extends Entity{

    final private Color color = new Color(rndInt(0,255),rndInt(0,255),rndInt(0,255));

    public Ball(int screenWidth, int vX, int vY, int size, String tag){
        this.x = rndInt(0,screenWidth-size);
        this.y = 0;
        this.vX = rndNeg()*vX;
        this.vY = vY;
        this.width = size;
        this.height = size;
        this.tag = tag;
    }

    private int rndNeg(){ //returns 1 or -1
        int rnd = rndInt(0,2);
        if (rnd == 0) rnd = -1;
        return rnd;
    }

    public void draw(Graphics window){
        window.setColor(color);
        window.fillRect(x,y,width,height);
    }
}