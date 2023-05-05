package net.magnusfrater.tds.game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //panel
    private Thread thread;
    private static boolean running;
    private boolean fpsLock;

    public GamePanel () {
        //panel
        thread = new Thread(this, "Time Based Fast Paced Top Down Shooter Demo");
        running = true;
        fpsLock = true;

        thread.start();
    }

    public void run () {
        long iTimeNS = System.nanoTime();
        int tickRate = 60;
        long ns = 1000000000 / tickRate;

        int ups = 0;
        int fps = 0;

        long iTimeS = iTimeNS;
        long s = 1000000000;

        while (running) {
            long fTime = System.nanoTime();

            if (fTime - iTimeNS >= ns){
                iTimeNS = fTime;

                tick();
                ups++;

                if (fpsLock){
                    repaint();
                    fps++;
                }
            }

            if (!fpsLock){
                repaint();
                fps++;
            }

            if (fTime - iTimeS >= s){
                iTimeS = fTime;

                System.out.println("ups: " + ups + "\tfps: " + fps);

                ups = 0;
                fps = 0;
            }
        }

        System.exit(0);
    }

    public void tick () {

    }

    @Override
    public void update (Graphics g) {
        paint(g);
    }

    @Override
    public void paint (Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,GameFrame.width, GameFrame.height);
    }
}
