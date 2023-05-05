package net.magnusfrater.personal;

import java.awt.*;

public class Guitar extends Entity{

    public Guitar(int x, int y, int width, int height, Color c){
        super(x,y,0,0,width,height,c,"Guitar");
    }

    @Override
    protected void tick(){

    }

    @Override
    protected void draw(Graphics window){
        window.setColor(getColor());
        window.fillRect(getX(),getY(), getWidth(),getHeight());
    }

    public String toString(){
        return "";
    }
}
