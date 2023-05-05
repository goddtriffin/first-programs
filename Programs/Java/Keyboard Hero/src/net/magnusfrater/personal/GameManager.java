package net.magnusfrater.personal;

import java.awt.*;

public class GameManager {

    //GENERAL
    Dimension size;

    //INSTRUMENT(S)
    Guitar g;

    public GameManager(int width, int height){
        size = new Dimension(width,height);

        g = new Guitar(size.width/4,0, width/2,height, Color.DARK_GRAY);
    }

    protected void tick(){
        g.tick();
    }

    protected void draw(Graphics window){
        g.draw(window);
    }

    public String toString(){
        String s = "GameManager: ";

        s+=g.toString();

        return s;
    }
}
