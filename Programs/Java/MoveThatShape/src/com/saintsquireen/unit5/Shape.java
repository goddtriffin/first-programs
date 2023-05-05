package com.saintsquireen.unit5;

import java.awt.Color;
import java.awt.Graphics;

public class Shape
{
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private Color color;
    private int xSpeed;
    private int ySpeed;

    public Shape(int x, int y, int wid, int ht, Color col, int xSpd, int ySpd) {
        xPos = x;
        yPos = y;

        width = wid;
        height = ht;

        color = col;

        xSpeed = xSpd;
        ySpeed = ySpd;
    }

    public void draw(Graphics window){
        window.setColor(color);
        window.fillRect(xPos, yPos, width, height);
    }

    public void moveAndDraw(Graphics window){
        xPos += xSpeed;
        yPos += ySpeed;

        window.setColor(color);
        window.fillRect(xPos,yPos, width, height);
    }

    public int getX() {return xPos;}
    public void setX(int x){ xPos = x; }

    public int getY(){ return yPos; }
    public void setY(int y){ yPos = y; }

    public int getXSpeed(){ return xSpeed; }
    public void setXSpeed(int xSpd){ xSpeed = xSpd; }

    public int getYSpeed(){ return ySpeed; }
    public void setYSpeed(int ySpd){ ySpeed = ySpd; }

    public String toString()
    {
        return xPos +" "+ yPos +" "+ width +" "+ height +" "+ color +" "+ xSpeed +" "+ ySpeed;
    }
}