package net.magnusfrater.tds.utility;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet (BufferedImage sheet) {
        this.sheet = sheet;
    }

    public BufferedImage crop (int row, int col, int width, int height){
        return sheet.getSubimage(row * width, col * height, width, height);
    }
}

//http://opengameart.org/content/top-down-dungeon-tileset