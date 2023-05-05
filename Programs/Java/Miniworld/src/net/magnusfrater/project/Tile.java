package net.magnusfrater.project;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {

    //DATA
    private int ID;
    private int size;

    //GRAPHICS
    private Color c;
    private BufferedImage texture;

    public Tile(int size, int ID){
        this.ID = ID;
        this.size = size;
    }

    protected void draw(Graphics window, int x,int y, double xOffset,double yOffset, boolean simpleGraphics){ //draws tile
        if (texture == null)
            initTextures();;

        int xLoc = (int)((x*size)+xOffset);
        int yLoc = (int)((y*size)+yOffset);

        if (xLoc+size>0 && xLoc<1000){ //within frame (horizontally)
            if (yLoc+size>0 && yLoc<(1000/16*10)){ //within frame (vertically)

                if (simpleGraphics){ //mono-color representation of tile
                    window.setColor(c);
                    window.fillRect(xLoc,yLoc,size,size);
                }else{ //graphic representation of tile
                    window.drawImage(texture,xLoc,yLoc,size,size,null);
                }

            }
        }
    }

    private void initTextures(){
        switch(ID){
            case 0: //hole
                c = Color.BLACK;
                setTexture("/hole.PNG");
                break;
            case 1: //grass
                c = new Color(19, 141,0);
                setTexture("/grass.PNG");
                break;
            case 2: //dirt
                c = new Color(93, 46, 3);
                setTexture("/dirt.PNG");
                break;
            case 3: //shore
                c = new Color(72, 252, 255);
                setTexture("/shore.PNG");
                break;
            case 4: //ocean
                c = new Color(47, 73, 255);
                setTexture("/ocean.PNG");
                break;
            default: //nontexture
                c = new Color(255,0, 156);
                setTexture("/nontexture.PNG");
                break;
        }
    }

    private void setTexture(String path){
        try{
            texture = ImageIO.read(getClass().getResourceAsStream(path));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
