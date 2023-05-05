package com.saintsquireen.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    final long firstTime = System.currentTimeMillis(); //saves time when program starts

    private int WIDTH,HEIGHT; //width/height of game
    private boolean running; //if game is running

    private boolean fps60; //if fps is locked at 60

    private BallTool bt; //tool for all ballsy relating things
    private Player player;

    final private int floorHeight = 55; //height of floor above bottom of window

    //STATS
    private boolean showStats; //if stats should be shown
    private int instUPS,instFPS; //instantaneous ups/fps every second

    public GamePanel(int WIDTH, int HEIGHT){
        setFocusable(true);
        setBackground(Color.WHITE);
        setVisible(true);

        this.WIDTH = WIDTH; this.HEIGHT = HEIGHT;

        init();

        addKeyListener(this);
        new Thread(this).start();
    }

    public void init(){
        running = true;

        fps60 = true;

        bt = new BallTool(WIDTH,HEIGHT,HEIGHT-floorHeight);
        player = new Player(WIDTH,HEIGHT-floorHeight);

        //STATS
        showStats = false;
        instUPS = 0; instFPS = 0;
    }

    public void run(){
            long lastTime = System.nanoTime(); //system time
            long oneSecondTimer = System.currentTimeMillis();
            long fiveSecondTimer = System.currentTimeMillis();
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

                    if (player.invTimer > 0) player.invTimer -= 1;
                }

                if (System.currentTimeMillis() - fiveSecondTimer > 5000){ //called once every 5 seconds
                    fiveSecondTimer = System.currentTimeMillis();

                    for (int i=0;i<bt.getNumSpawnBalls();i++) bt.addBall(WIDTH);
                }
            }
    }

    //gets called 60 times a second
    private void tick(){ //update all block's, powerup's, and player's position and collisions
        if( bt.checkAllBallCollisions(new Rectangle(player.x,player.y,player.width,player.height)) ){ //ball collisions: if returned true: player is hit by a ball(s)
            if (player.invTimer == 0){ //if player is not invincible
                player.health -=1;
                player.invTimer += 2; //makes the player invincible for a short period of time (2 seconds)
            }
        }
        bt.moveAllBalls(); //move balls

        player.move(); //move player
    }

    public void update(Graphics window){
        paint(window);
    }

    public void paint(Graphics window){
        //resets background
        window.setColor(Color.WHITE);
        window.fillRect(0, 0, WIDTH, HEIGHT);

        //floor
        window.setColor(Color.GRAY);
        window.fillRect(0, HEIGHT - floorHeight, WIDTH, floorHeight);

        //player
        player.draw(window);

        //draw all blocks
        bt.drawAllBalls(window);

        //STATS
        if (showStats){
            int x=15, y=15, deltaY = 15;

            window.setColor(Color.BLACK);
            //general
            window.drawString("ups: "+ instUPS +", fps: "+ instFPS, x,y); y+=deltaY;
            y+=deltaY;

            //balls
            window.drawString("BALLS", x,y); y+=deltaY;
            window.drawString("-----", x,y); y+=deltaY;
            window.drawString("bSize: "+ bt.getBSize(), x,y); y+=deltaY;
            window.drawString("bSpeed: "+ bt.getBSpeed(), x,y); y+=deltaY;
            window.drawString("numSpawnBalls: "+ bt.getNumSpawnBalls(), x,y); y+=deltaY;
            window.drawString("numBalls: "+ bt.getNumBalls(), x,y); y+=deltaY;
            window.drawString("ballsColliding: "+ bt.getBallsColliding(), x,y); y+=deltaY;
            window.drawString("walls: "+ bt.getWalls(), x,y); y+=deltaY;
            y+=deltaY;

            //player
            window.drawString("PLAYER", x,y); y+=deltaY;
            window.drawString("-----_", x,y); y+=deltaY;
            window.drawString("health: "+ player.health, x,y); y+=deltaY;
            y+=deltaY;
        }
    }

    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A: player.setRunLeft(true);
                break;
            case KeyEvent.VK_D: player.setRunRight(true);
                break;
            case KeyEvent.VK_LEFT: player.setRunLeft(true);
                break;
            case KeyEvent.VK_RIGHT: player.setRunRight(true);
                break;
        }
    }
    public void keyTyped(KeyEvent e){

    }
    public void keyReleased(KeyEvent e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_I: showStats = !showStats;
                break;
            case KeyEvent.VK_F: fps60 = !fps60;
                break;

            case KeyEvent.VK_A: player.setRunLeft(false);
                break;
            case KeyEvent.VK_D: player.setRunRight(false);
                break;
            case KeyEvent.VK_LEFT: player.setRunLeft(false);
                break;
            case KeyEvent.VK_RIGHT: player.setRunRight(false);
                break;
        }
    }
}

/*
private int bSize; //ball size
    private int bSpeed; //ball speed
    private int numSpawnBalls; //number of balls spawned (every 5s)
    private int numBalls; //total balls
    private Ball[] balls;

    //ball powerups
    private boolean ballsColliding; //if balls should collide with each other
    private boolean walls; //if balls should collide with left/right walls
 */

/*
/\/\/\POWERUPS/\/\/\
====================
~'BALL'~
--------
colliding balls
remove walls
reverse ball velocity directions
speed
size
number spawned (every 5 seconds)

~'PLAYER'~
----------
size
speed
jump ability
  -height
fly ability
shield
hearts
change meaning of keys (left=right)
 */