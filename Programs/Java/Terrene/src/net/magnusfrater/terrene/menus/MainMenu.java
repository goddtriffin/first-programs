package net.magnusfrater.terrene.menus;

import net.magnusfrater.terrene.input.Keyboard;
import net.magnusfrater.terrene.tools.StringWriter;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MainMenu extends Menu{

    public MainMenu(){
        super(new String[]{"New","Old","Multi","Info","Exit"});
        selected = 0;
    }

    @Override
    public void tick(){
        cycle();

        if (Keyboard.isKeyTyped(KeyEvent.VK_SPACE)){
            switch(options[selected]){
                case "New":
                    return;
                case "Old":
                    return;
                case "Multi":
                    return;
                case "Info":
                    return;
                case "Exit":
                    return;
                default:
                    break;
            }
        }
    }

    @Override
    public void draw(Graphics g){
        StringWriter.draw(g, "Terrene",Color.WHITE,230,100,5,"Verdana",1,120);
        StringWriter.draw(g, "By: Todd Griffin",Color.WHITE,326,160,3,"Verdana",1,40);
    }
}
