package net.magnusfrater.terrene.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Keyboard implements KeyListener {

    private static ArrayList<Integer> keyTyped = new ArrayList<>();
    private static ArrayList<Integer> keyDown = new ArrayList<>();
    private static ArrayList<Integer> keyReleased = new ArrayList<>();

    public static void tick(){
        keyTyped.clear();
        keyReleased.clear();
    }

    public static boolean isKeyTyped(int key){
        return keyTyped.contains(key);
    }
    public static boolean isKeyDown(int key){
        return keyDown.contains(key);
    }
    public static boolean isKeyReleased(int key){
        return keyReleased.contains(key);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!keyDown.contains(e.getKeyCode())){
            keyDown.add(e.getKeyCode());
            keyTyped.add(e.getKeyCode());
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        keyDown.remove(keyDown.indexOf(e.getKeyCode()));
        keyReleased.add(e.getKeyCode());
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
}
