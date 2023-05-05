package net.magnusfrater.project;

import java.awt.*;
import java.util.Random;

public class World {

    //GENERAL
    private Random r;

    //DATA
    private int width,height;
    private Tile[][] world;
    private boolean simpleGraphics;

    public World(int width, int height){
        r = new Random();

        this.width = width;
        this.height = height;

        generateWorld();

        simpleGraphics = true;
    }

    private void generateWorld(){
        world = new Tile[width][height];
        for (int x=0; x<width; x++)
            for (int y=0; y<height; y++)
                world[x][y] = new Tile(50,4);
                //world[x][y] = new Tile(50,rndInt(0,4));
    }

    protected void tick(){ //updates data

    }

    protected void draw(Graphics window, double xOffset,double yOffset){
        for (int x=0; x<width; x++)
            for (int y=0; y<height; y++)
                world[x][y].draw(window,x,y,xOffset,yOffset,false);
    }

    private int rndInt(int min,int max){
        return r.nextInt((max - min) + 1) + min;
    }
}
