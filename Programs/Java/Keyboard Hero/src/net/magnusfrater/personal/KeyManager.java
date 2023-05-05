package net.magnusfrater.personal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    boolean[] keys;

    public KeyManager(){
        keys = new boolean[199];
    }

    public void keyPressed(KeyEvent e){
        keys[e.getKeyCode()] = true;
    }
    public void keyTyped(KeyEvent e){

    }
    public void keyReleased(KeyEvent e){
        keys[e.getKeyCode()] = false;
    }

    protected boolean[] getKeys(){
        return this.keys;
    }

    @Override
    public String toString(){
        String s = "KeyManager: [";

        for (int i=0; i<keys.length; i++){
            if (keys[i]){
                s+=(KeyEvent.getKeyText(i) +" ("+ i +")");
                if (i<keys.length-2)
                    s+=", ";
            }
        }

        s+= "]";

        return s;
    }
}