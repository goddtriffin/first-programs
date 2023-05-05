package net.magnusfrater;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //panel
    private final Dimension frame = new Dimension(1000,1000/16*10);

    //settings
    private boolean fpsLock;

    //game
    private boolean running;

    private World world;
    private Screen screen;
    private Player player;
    private NPC npc;

    public GamePanel(){
        setFocusable(true);

        init();

        addKeyListener(player);
        addMouseListener(player);
        new Thread(this).start();

        setVisible(true);
    }

    private void init(){ //initialize variables
        //settings
        fpsLock = false;

        //game
        running = true;

        world = new World(500, 20, 50);
        //world = new World(500, 2, 50);
        screen = new Screen(true);
        player = new Player("Todd", world, 20, new Color(169,0,0));
        npc = new NPC("Stalker", 100,100, 20, new Color(207, 104, 63), new Stalker());
    }

    @Override
    public void run() {
        long iTime = System.nanoTime();
        long upsLock = 1000000000 / 60;

        long secondTimer = System.currentTimeMillis();

        int ups = 0;
        int fps = 0;

        while (running){
            if (System.nanoTime()-iTime>=upsLock){ //ticks 60 times a second
                tick();
                ups++;

                if (fpsLock){ //if fps locked at 60
                    repaint();
                    fps++;
                }

                iTime = System.nanoTime();
            }

            if (!fpsLock){ //if fps not locked at 60
                repaint();
                fps++;
            }

            if (System.currentTimeMillis()-secondTimer>=1000){ //every 1 second
                System.out.println("ups: "+ ups +" fps: "+ fps);

                ups = 0; fps = 0;

                secondTimer = System.currentTimeMillis();

                System.out.println(npc.getX() +" "+ npc.getY());
            }
        }
    }

    public void tick(){ //update game data
        screen.tick(frame,world,player);
        player.tick(this, world, screen);

        npc.tick(this, world, player, screen);
    }

    public void update(Graphics window){
        paint(window);
    }
    public void paint(Graphics window){ //paints game to screen
        //panel
        window.setColor(Color.MAGENTA);
        window.fillRect(0,0,(int)frame.getWidth(), (int)frame.getHeight());

        //game
        world.draw(frame, screen.getX(), screen.getY(),window);

        player.draw(screen.getX(),screen.getY(), window);
        npc.draw(screen.getX(),screen.getY(), window);
    }
}