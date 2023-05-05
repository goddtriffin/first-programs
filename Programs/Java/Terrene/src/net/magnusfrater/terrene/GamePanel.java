package net.magnusfrater.terrene;

import net.magnusfrater.terrene.input.Keyboard;
import net.magnusfrater.terrene.menus.MainMenu;
import net.magnusfrater.terrene.menus.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable {

    //game
    public static boolean running;
    private static boolean fpsLock;

    //input
    private Keyboard keyboard;

    //menu
    public static Menu menu;

    public GamePanel(){
        setFocusable(true);

        init();

        new Thread(this, "Terrene").start();
    }

    private void init(){
        //game
        running = true;
        fpsLock = false;

        //input
        keyboard = new Keyboard();
        addKeyListener(keyboard);

        //menu
        menu = new MainMenu();
    }

    @Override
    public void run() {
        long iTime = System.nanoTime();
        long ns = 1000000000 / 60;

        int ups = 0;
        int fps = 0;

        long iSecond = System.currentTimeMillis();
        long ms = 1000;

        while (running){
            long fTime = System.nanoTime();
            if (fTime-iTime >= ns){ //60 times a second
                tick();
                ups++;

                if (fpsLock){
                    repaint();
                    fps++;
                }

                iTime = fTime;
            }

            if (!fpsLock){
                repaint();
                fps++;
            }

            long fSecond = System.currentTimeMillis();
            if (fSecond-iSecond >= ms){
                System.out.println("ups: "+ ups +" | fps: "+ fps);

                ups = 0;
                fps = 0;

                iSecond = fSecond;

                System.out.println(Menu.selected);
            }
        }
    }

    public void tick(){
        Keyboard.tick();

        if (menu != null)
            menu.tick();
    }

    public void update(Graphics g){
        paint(g);
    }
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0, GameFrame.size.width, GameFrame.size.height);

        if (menu != null)
            menu.draw(g);
    }
}
