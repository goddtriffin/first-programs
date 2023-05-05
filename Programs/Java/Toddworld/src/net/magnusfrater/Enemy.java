package net.magnusfrater;

import Tools.RandomNumberGenerator;

import java.awt.*;

public class Enemy {

    //general
    private RandomNumberGenerator rng = new RandomNumberGenerator();

    //enemy
    private double eX,eY;
    private double eV;
    private int eSize;
    private Color eC;

    public Enemy(double eX,double eY, double eV, int eSize, Color eC){
        this.eX = eX;
        this.eY = eY;
        this.eV = eV;
        this.eSize = eSize;
        this.eC = eC;
    }

    protected void tick(World w, Player p){
        if (p.getX()<eX) eX-=eV; //follows player's xPos
        if (p.getX()>eX) eX+=eV;
        if (p.getY()<eY) eY-=eV; //follows player's yPos
        if (p.getY()>eY) eY+=eV;

        if (eX < 0) eX = 0; //player within world width
        if (eX+eSize+5 > w.getWorldSize()*w.getTileSize()) eX = w.getWorldSize()*w.getTileSize() - eSize-5;
        if (eY < 0) eY = 0; //player within world height
        if (eY+eSize+28 > w.getWorldSize()*w.getTileSize()) eY = w.getWorldSize()*w.getTileSize() - eSize-28;
    }

    protected void draw(double sX,double sY, Graphics window){
        window.setColor(eC);
        window.fillRect((int)(eX-sX),(int)(eY-sY), eSize, eSize);
    }

    public double getX() {
        return eX;
    }

    public double getY() {
        return eY;
    }

    public double getV() {
        return eV;
    }

    public int getSize() {
        return eSize;
    }
}
