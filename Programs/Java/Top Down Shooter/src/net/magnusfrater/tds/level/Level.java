package net.magnusfrater.tds.level;

import java.awt.*;

public class Level {

    //tile
    private Tile[][] tileMap;
    private int tileSize;
    private boolean simpleView;

    public Level (int levelNum) {
        //tile
        tileMap = loadMap();
        tileSize = 64;
        simpleView = true;
    }

    public void tick () {

    }

    public void paint (Graphics g, double cameraX, double cameraY) {
        for (int y=0; y<tileMap.length; y++){
            for (int x=0; x<tileMap[y].length; x++){
                tileMap[y][x].paint(g, (x * tileSize) + (int)cameraX, (y * tileSize) + (int)tileSize, tileSize);
            }
        }

        g.dispose();
    }

    private Tile[][] loadMap () {
        Tile[][] tileMap = new Tile[20][20];

        for (int y=0; y<tileMap.length; y++){
            for (int x=0; x<tileMap[y].length; x++){
                tileMap[y][x] = new Tile(0);
            }
        }

        return tileMap;
    }
}
