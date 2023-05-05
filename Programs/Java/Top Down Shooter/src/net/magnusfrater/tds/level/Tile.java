package net.magnusfrater.tds.level;

import net.magnusfrater.tds.utility.ImageLoader;
import net.magnusfrater.tds.utility.RNG;
import net.magnusfrater.tds.utility.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    private int type;
    private String name;
    private boolean walkable;
    private BufferedImage img;

    public Tile (int type) {
        this.type = type;

        init();
    }

    private void init () {
        ImageLoader loader = new ImageLoader();
        SpriteSheet ss = new SpriteSheet(loader.load("res/image/tileset.png"));

        switch (type) {
            case 1:
                name = "floor1";
                walkable = true;
                img = ss.crop(8, 2, 16, 16);
                break;

            case 2:
                name = "floor2";
                walkable = true;
                img = ss.crop(8, 3, 16, 16);

            default:
                name = "error_tile";
                walkable = true;
                img = ss.crop(RNG.getRandInt(0, 17), RNG.getRandInt(0, 12), 16, 16);
                break;
        }
    }

    public void paint (Graphics g, double x, double y, int size) {
        g.drawImage(img, (int)x, (int)y, size, size, null);
    }

    public int getType () {
        return this.type;
    }

    public String getName () {
        return this.name;
    }

    public boolean isWalkable () {
        return this.walkable;
    }
}
