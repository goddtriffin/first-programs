package net.magnusfrater.terrene.menus;

import net.magnusfrater.terrene.input.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Menu {

    protected String[] options;
    public static int selected;

    public Menu(String[] options){
        this.options = options;
        selected = 0;
    }

    /*
        Move through menu options if up/down or W/S are pressed.
        Selected is locked in range of options.
     */
    public void cycle(){
        //if (options.length<1)
            //return;

        if (Keyboard.isKeyTyped(KeyEvent.VK_UP) || Keyboard.isKeyTyped(KeyEvent.VK_W)){
            selected--;
        }
        if (Keyboard.isKeyTyped(KeyEvent.VK_DOWN) || Keyboard.isKeyTyped(KeyEvent.VK_S)){
            selected++;
        }
        //if (selected<0) selected = options.length-1;
        //if (selected>=options.length) selected = 0;
    }

    /*
        Handles selected options.
     */
    public void tick(){
        cycle();
    }

    /*
        Renders options to the screen.
     */
    public void draw(Graphics g){
    }
}
