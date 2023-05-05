package net.magnusfrater;

import java.awt.*;

public class Screen {

    private boolean followPlayer;
    private double sX,sY;

    public Screen(){
        this.followPlayer = true;
        this.sX = 0;
        this.sY = 0;
    }
    public Screen(boolean followPlayer){
        this.followPlayer = followPlayer;
    }
    public Screen(double sX, double sY){
        this.followPlayer = false;
        this.sX = sX;
        this.sY = sY;
    }

    protected void tick(Dimension frame, World w, Player p){
        if (followPlayer){
            sX = p.getX() - (frame.getWidth()/2) + (p.getSize()/2);
            sY = p.getY() - (frame.getHeight()/2) + (p.getSize()/2);
        }

        if (sX < 0) sX = 0; //within world width
        if (sX+frame.getWidth() > w.getWorldSize()*w.getTileSize()) sX = w.getWorldSize()*w.getTileSize()-frame.getWidth();

        if (sY<0) sY = 0; //within world height
        if (sY+frame.getHeight() > w.getWorldSize()*w.getTileSize()) sY = w.getWorldSize()*w.getTileSize()-frame.getHeight();
    }

    protected double getX(){
        return this.sX;
    }
    protected void setX(double sX){
        this.sX = sX;
    }

    protected double getY(){
        return this.sY;
    }
    protected void setY(double sY){
        this.sY = sY;
    }

    protected boolean getFollowPlayer(){
        return this.followPlayer;
    }
    protected void setFollowPlayer(boolean followPlayer){
        this.followPlayer = followPlayer;
    }
}
