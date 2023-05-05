package net.magnusfrater;

import Tools.RandomNumberGenerator;

import java.awt.*;

public class NPC{

    //general
    private RandomNumberGenerator rng = new RandomNumberGenerator();

    //player
    private String npcName;
    private double npcX,npcY;
    private int npcSize;
    private Color npcC;

    private Tile npcTileOn;

    private AI ai;

    public NPC(String npcName, double npcX, double npcY, int npcSize, Color npcC, AI ai){
        this.npcName = npcName;
        this.npcX = npcX;
        this.npcY = npcY;
        this.npcSize = npcSize;
        this.npcC = npcC;

        this.ai = ai;
    }
    public NPC(String npcName, World w, int npcSize, Color npcC, AI ai){
        this.npcName = npcName;
        this.npcX = w.getCenter()-npcSize;
        this.npcY = w.getCenter()-npcSize;
        this.npcSize = npcSize;
        this.npcC = npcC;

        this.ai = ai;
    }

    protected void tick(Component c, World w, Player p, Screen s){
        int npcTileX = (int)((npcX+(npcSize/2))/w.getTileSize());
        int npcTileY = (int)((npcY+(npcSize/2))/w.getTileSize());
        npcTileOn = w.getTile(npcTileX, npcTileY); //update tile that NPC is hovering

        ai.update(w, p, this);
        move();

        if (npcX < 0) npcX = 0; //NPC within world width
        if (npcX+npcSize+5 > w.getWorldSize()*w.getTileSize()) npcX = w.getWorldSize()*w.getTileSize() - npcSize-5;
        if (npcY < 0) npcY = 0; //NPC within world height
        if (npcY+npcSize+28 > w.getWorldSize()*w.getTileSize()) npcY = w.getWorldSize()*w.getTileSize() - npcSize-28;
    }

    private void move(){
        double npcV;

        switch (npcTileOn.getType()){ //change NPC's speed depending on tile hovering
            case "ocean": npcV = 0.5;
                break;
            case "shore": npcV = 1.5;
                break;
            case "beach": npcV = 3;
                break;
            default: npcV = 5;
                break;
        }

        if (ai.getUp()) npcY-=npcV; //move NPC set velocity via key input
        if (ai.getDown()) npcY+=npcV;
        if (ai.getLeft()) npcX-=npcV;
        if (ai.getRight()) npcX+=npcV;
    }

    protected void draw(double sX,double sY, Graphics window){
        window.setColor(npcC);
        window.fillRect((int)(npcX-sX),(int)(npcY-sY), npcSize, npcSize); //draw NPC to the screen
    }

    public double getX() {
        return npcX;
    }

    public double getY() {
        return npcY;
    }

    public int getSize() {
        return npcSize;
    }
}