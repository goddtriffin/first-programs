package com.saintsquireen.game;

import java.util.Random;

public class Entity {

    protected int x,y;
    protected int vX,vY;
    protected int width, height;
    protected String tag;

    public Entity(){
        this.x = 0;
        this.y = 0;
        this.vX = 0;
        this.vY = 0;
        this.width = 50;
        this.height = 50;
    }

    public Entity(int x, int y, int vX, int vY, int width, int height){
        this.x = x;
        this.y = y;
        this.vX = vX;
        this.vY = vY;
        this.width = width;
        this.height = height;
    }

    protected void move(){ //moves entity by specified velocity in both x/y directions
        x+=vX;
        y+=vY;
    }

    protected int rndInt(int low, int high){ //returns random integer between specified min and max
        Random r = new Random();
        return r.nextInt(high) + low;
    }

    protected int getX(){ return this.x; } //gets-sets x, y
    protected void setX(int x){ this.x = x; }
    protected int getY(){ return this.y; }
    protected void setY(int y){ this.y = y; }

    protected int getVX(){ return this.vX; } //gets-sets vX, vY
    protected void setVX(int vX){ this.vX = vX; }
    protected int getVY(){ return this.vY; }
    protected void setVY(int vY){ this.vY = vY; }

    protected int getWidth(){ return this.width; } //gets-sets width,height
    protected void setWidth(int width){ this.width = width; }
    protected int getHeight(){ return this.height; }
    protected void setHeight(int height){ this.height = height; }

    protected String getTag(){ return this.tag; } //gets/sets tag
    protected void setTag(String tag){ this.tag = tag; }

    public String toString(){
        String output = "";
        output+= "X: "+ x +" Y: "+ y;
        output+= ", vX: "+ vX +" vY: "+ vY;
        output+= ", Width: "+ width +" Height: "+ height;
        return output;
    }
}