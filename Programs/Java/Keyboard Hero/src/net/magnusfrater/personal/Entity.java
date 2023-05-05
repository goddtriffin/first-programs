package net.magnusfrater.personal;

import java.awt.*;

public abstract class Entity {

    private int x,y;
    private int vX,vY;
    private int width,height;
    private Color c;
    private String tag;

    public Entity(int x,int y, int vX,int vY, int width,int height, Color c, String tag){
        this.x = x;
        this.y = y;
        this.vX = vX;
        this.vY = vY;
        this.width = width;
        this.height = height;
        this.c = c;
        this.tag = tag;
    }

    abstract protected void tick(); //updates entity info

    abstract protected void draw(Graphics window); //draws entity to the screen

    protected void setX(int x){
        this.x = x;
    }
    protected int getX(){
        return x;
    }

    protected void setY(int y){
        this.y = y;
    }
    protected int getY(){
        return y;
    }

    protected void setVX(int vX){
        this.vX = vX;
    }
    protected int getVX(){
        return vX;
    }

    protected void setVY(int vY){
        this.vY = vY;
    }
    protected int getVY(){
        return vY;
    }

    protected void setWidth(int width){
        this.width = width;
    }
    protected int getWidth(){
        return width;
    }

    protected void setHeight(int height){
        this.height = height;
    }
    protected int getHeight(){
        return height;
    }

    protected void setColor(Color c){
        this.c = c;
    }
    protected Color getColor(){
        return c;
    }

    protected void setTag(String tag){
        this.tag = tag;
    }
    protected String getTag(){
        return tag;
    }
}
