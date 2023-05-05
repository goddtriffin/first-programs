package net.magnusfrater.project;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel implements Runnable {

    //GENERAL
    Thread t;
    private boolean fps60; //if fps is locked at 60

    //GAME
    private boolean running; //if game is running

    private GameManager gm;
    private KeyManager km;

    //STATS
    private boolean showStats;
    private int instUPS,instFPS;

    public MainPanel(){
        setFocusable(true);
        setBackground(Color.WHITE);
        setVisible(true);

        init();

        addKeyListener(km);
        t = new Thread(this);
        t.start();
    }

    public void init(){
        //GENERAL
        fps60 = true;

        //GAME
        running = true;

        gm = new GameManager(getWidth(),getHeight());
        km = new KeyManager();

        //STATS
        showStats = false;
        instUPS = 0; instFPS = 0;
    }

    public void run(){ //main game loop: tick and draw controller
        long lastTime = System.nanoTime(); //system time
        long oneSecondTimer = System.currentTimeMillis();
        //long fiveSecondTimer = System.currentTimeMillis();
        final double ticks = 60D; //number of updates per second wanted
        double ns = 1000000000 / ticks; //Set definition of how many ticks per 1000000000 ns or 1 sec
        double delta = 0; //change in time, counted by 'ns'

        int ups = 0, fps = 0;

        while(running){
            long now = System.nanoTime(); //Update the time
            delta += (now - lastTime) / ns; //calculate change in time since last known time
            lastTime = now; //update last known time

            if (delta >= 1){ //continue while delta is less than or equal to 1
                tick(); //updates stuff
                ups++; //increments updates

                if (fps60){ //fps locked at 60
                    repaint(); //repaints everything
                    fps++; //increments frames
                }

                delta--; //decrement delta
            }
            if (!fps60){ //fps not locked at 60
                repaint(); //repaints everything
                fps++; //increments frames
            }

            if (System.currentTimeMillis() - oneSecondTimer > 1000){ //called once every 1 second
                oneSecondTimer = System.currentTimeMillis();

                System.out.println("ups: "+ ups +", fps: "+ fps);

                instUPS = ups; instFPS = fps;
                ups = 0; fps = 0;

                //do something here that gets called 1 time per second
            }

           /*if (System.currentTimeMillis() - fiveSecondTimer > 5000){ //called once every 5 seconds
               fiveSecondTimer = System.currentTimeMillis();

               //do something here that gets called 1 times per 5 seconds
           }*/
        }
    }

    private void tick(){ //updates game data (60ups)
        if (km.getKeys()[70]){
            fps60 = false;
        }else{
            fps60 = true;
        }
        if (km.getKeys()[73]) {
            showStats = true;
        }else{
            showStats = false;
        }

        gm.tick(km.getKeys());
    }

    public void update(Graphics window){ paint(window); }
    public void paint(Graphics window){
        //resets background
        window.setColor(Color.WHITE);
        window.fillRect(0,0,getWidth(),getHeight());

        //paint everything here
        gm.draw(window);

        //STATS
        if (showStats){
            window.setColor(Color.BLACK);

            int sX=15, sY=15, deltaSY=15; //'CSS' for stats text

            //GENERAL
            window.drawString("ups: "+ instUPS +", fps: "+ instFPS, sX,sY); sY+=deltaSY;
            sY+=deltaSY;


            //gamemanager
            window.drawString(gm.toString(),sX,sY);sY+=deltaSY;
            sY+=deltaSY;

            //keymanager
            window.drawString(km.toString(),sX,sY);sY+=deltaSY;
            sY+=deltaSY;
        }
    }
}
