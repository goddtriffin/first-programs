package net.magnusfrater.pong.game;

import net.magnusfrater.pong.entity.Ball;
import net.magnusfrater.pong.entity.Player;
import net.magnusfrater.pong.gui.Level;
import net.magnusfrater.pong.gui.menu.*;
import net.magnusfrater.pong.gui.menu.Menu;
import net.magnusfrater.pong.input.Keyboard;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //game
    public static boolean running;
    public static boolean fpsLock;

    public static boolean paused;

    //input
    public Keyboard kb;

    //gui
    public static Menu m;
    public static Level l;

    //entity
    public static Player p1,p2;
    public static Ball b;

    public GamePanel(){
        setFocusable(true);

        //game
        running = true;
        fpsLock = true;

        //input
        kb = new Keyboard();
        addKeyListener(kb);

        setMainMenu();

        new Thread(this, "PONG: By Todd Griffin").start();
    }

    @Override
    public void run() { //called when instance created
        long iTimeNS = System.nanoTime();
        long ns = 1000000000 / 60;

        int ups = 0;
        int fps = 0;

        long iTimeS = System.nanoTime();
        long s = 1000000000;

        while (running){
            long fTimeNS = System.nanoTime();
            if (fTimeNS-iTimeNS>=ns){ //ticks 60 times a second
                tick();
                ups++;

                if (fpsLock){
                    repaint();
                    fps++;
                }

                iTimeNS = fTimeNS;
            }

            if (!fpsLock){
                repaint();
                fps++;
            }

            long fTimeS = System.nanoTime();
            if (fTimeS-iTimeS>=s){ //ticks once a second
                System.out.println("ups: "+ ups +"\tfps: "+ fps);

                ups = 0;
                fps = 0;

                iTimeS = fTimeS;
            }
        }

        System.exit(0);
    }

    public void tick(){ //updates game info 60 times a second
        //keyboard
        kb.tick();

        //gui
        if (m != null)
            m.tick();

        if (!paused){
            //gui
            if (l != null)
                l.tick(p1,p2);

            //entity
            if (p1 != null)
                p1.tick(b);
            if (p2 != null)
                p2.tick(b);
            if (b != null)
                b.tick(p1,p2);
        }
    }

    @Override
    public void update(Graphics g){
        paint(g);
    }
    @Override
    public void paint(Graphics g){ //draws everything to screen
        Graphics2D g2d = (Graphics2D) g;

        //screen
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,GameFrame.WIDTH, GameFrame.HEIGHT);

        //gui
        if (m != null)
            m.draw(g2d);
        if (l != null)
            l.draw(g2d,p1,p2);

        //entity
        if (p1 != null)
            p1.draw(g2d);
        if (p2 != null)
            p2.draw(g2d);
        if (b != null)
            b.draw(g2d);
    }

    public static void setMainMenu(){
        Keyboard.clear();

        //gui
        m = new Main();
        l = null;

        //entity
        p1 = null;
        p2 = null;
        b = null;
    }

    public static void setAboutMenu(){
        Keyboard.clear();

        //gui
        m = new About();
        l = null;

        //entity
        p1 = null;
        p2 = null;
        b = null;
    }

    public static void setNewGame(int playerCount){
        Keyboard.clear();

        //game
        paused = false;

        //gui
        m = new PauseGame();
        l = new Level();

        //entity
        if (playerCount == 1){
            p1 = new Player(1);
            p2 = new Player(3);
        }else{
            p1 = new Player(1);
            p2 = new Player(2);
        }
        b = new Ball();
    }

    public static void setGameOver(){
        Keyboard.clear();

        //gui
        if (p1.score > p2.score){ //who won
            if (p2.playerNum == 2){ //1-PLAYER or 2-PLAYER
                m = new GameOver(1,2);
            }else{
                m = new GameOver(1,1);
            }
        }else{
            if (p2.playerNum == 2){ //1-PLAYER or 2-PLAYER
                m = new GameOver(2,2);
            }else{
                m = new GameOver(2,1);
            }
        }
        l = null;

        //entity
        p1 = null;
        p2 = null;
        b = null;
    }
}
