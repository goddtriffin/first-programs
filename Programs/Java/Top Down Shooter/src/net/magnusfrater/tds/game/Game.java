package net.magnusfrater.tds.game;

import net.magnusfrater.tds.level.Camera;
import net.magnusfrater.tds.level.Level;

import java.awt.*;

public class Game {

    //level
    private int levelNum;
    private Level level;

    //camera
    private Camera camera;

    public Game () {
        //level
        this.levelNum = 1;
        level = new Level(this.levelNum);

        //camera
        camera = new Camera();
    }

    public Game (int levelNum) {
        //level
        this.levelNum = levelNum;
        level = new Level(this.levelNum);

        //camera
        camera = new Camera();
    }

    public void tick () {
        if (level != null)
            level.tick();
    }

    public void paint (Graphics g) {
        if (level != null && camera != null)
            level.paint(g, camera.getX(), camera.getY());
    }
}
