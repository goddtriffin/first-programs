package net.magnusfrater.pong.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Keyboard implements KeyListener {

    public static ArrayList<Integer> keyDown;
    public static ArrayList<Integer> keyUp;

    public Keyboard(){
        keyDown = new ArrayList<>();
        keyUp = new ArrayList<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (keyDown.indexOf(e.getKeyCode()) == -1){
            keyDown.add(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (keyUp.indexOf(e.getKeyCode()) == -1){
            keyUp.add(e.getKeyCode());
        }
    }

    public void tick(){
        for (int i=0; i<keyUp.size(); i++){ //find keyUp in keyDown, and remove
            keyDown.remove(keyUp.get(i));
        }

        keyUp.clear(); //clear keyUp
    }

    public static boolean isKeyDown(int key){
        return keyDown.contains(key);
    }

    public static boolean isKeyUp(int key){
        return keyUp.contains(key);
    }

    public static void clear(){
        keyDown.clear();
        keyUp.clear();
    }
}
