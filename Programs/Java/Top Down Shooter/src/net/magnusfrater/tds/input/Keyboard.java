package net.magnusfrater.tds.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Keyboard implements KeyListener {

    private static ArrayList<Integer> keyDown;
    private static ArrayList<Integer> keyUp;

    private static ArrayList<Integer> recentlyUp;
    private static long iTime;
    private static long recentlyUpRefreshRate;
    private static long ns;

    public Keyboard () {
        keyDown = new ArrayList<>();
        keyUp = new ArrayList<>();

        recentlyUp = new ArrayList<>();
        iTime = System.nanoTime();
        recentlyUpRefreshRate = 4;
        ns = 1000000000 / recentlyUpRefreshRate;
    }

    @Override
    public void keyTyped (KeyEvent e) {
        // UNUSED
    }

    @Override
    public void keyPressed (KeyEvent e) {
        if (keyDown.indexOf(e.getKeyCode()) == -1){
            keyDown.add(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased (KeyEvent e) {
        if (keyUp.indexOf(e.getKeyCode()) == -1){
            keyUp.add(e.getKeyCode());
        }
    }

    public static boolean isKeyDown (int key) {
        return keyDown.contains(key);
    }

    public static boolean isKeyUp (int key) {
        return keyUp.contains(key);
    }

    public static boolean isRecentlyUp (int key) {
        if ( recentlyUp.contains(key) ){
            recentlyUp.remove(key);
            return true;
        } else {
            return false;
        }
    }

    public static void tick () {
        for (int i=0; i<keyUp.size(); i++){ //find keyUp in keyDown, and remove
            keyDown.remove(keyUp.get(i));
        }

        keyUp.clear(); //clear keyUp

        long fTime = System.nanoTime();
        if (fTime - iTime >= ns) {
            iTime = fTime;

            recentlyUp.clear();
        }
    }

    public static void clear () {
        keyDown.clear();
        keyUp.clear();
    }
}