package net.magnusfrater.project;

import java.awt.*;

public class GameManager {

    //GENERAL
    private Dimension size;

    //WORLD
    private World w;

    //SCREEN
    private Screen s;
    private boolean followScreen;


    public GameManager(int width, int height){
        size = new Dimension(width,height);

        w = new World(50,50);

        s = new Screen();
        followScreen = true;
    }

    protected void tick(boolean[] keys){
        s.tick(keys);
    }

    protected void draw(Graphics window){
        w.draw(window,s.getxOffset(),s.getyOffset());
    }

    public String toString(){
        String s = "GameManager: ";

        //s+= 'entity'.toString();

        return s;
    }
}
