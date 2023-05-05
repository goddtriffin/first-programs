package net.magnusfrater;

import Tools.RandomNumberGenerator;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Player implements KeyListener, MouseListener {

    //general
    private RandomNumberGenerator rng = new RandomNumberGenerator();

    //player
    private String pName;
    private double pX,pY;
    private double pV;
    private int pSize;
    private Color pC;

    private Tile pTileOn;

    //keys
    private boolean pUp, pDown, pLeft, pRight;

    //mouse
    private Point mPoint;
    private Point mWorldPosition;
    private Tile mTileOn;

    //resources
    private Tool pTool;
    private int trees, mountains;
    private String pSelectedResource;

    public Player(String pName, double pX, double pY, int pSize, Color pC){
        this.pName = pName;
        this.pX = pX;
        this.pY = pY;
        this.pSize = pSize;
        this.pC = pC;

        pTool = new Tool("weapon");
        trees = 0;
        mountains = 0;
        pSelectedResource = "mountain";
    }
    public Player(String pName, World w, int pSize, Color pC){
        this.pName = pName;
        this.pX = w.getCenter()-pSize;
        this.pY = w.getCenter()-pSize;
        this.pSize = pSize;
        this.pC = pC;

        pTool = new Tool("weapon");
        trees = 0;
        mountains = 0;
        pSelectedResource = "mountain";
    }

    protected void tick(Component c, World w, Screen s){
        if (c.getMousePosition() != null){
            mPoint = c.getMousePosition(); //update mouse position relative to the component
            mWorldPosition = new Point((int)(mPoint.getX()+s.getX()), (int)(mPoint.getY()+s.getY())); //update mouse position relative to the world
            mTileOn = w.getTile((int)mWorldPosition.getX()/w.getTileSize(),(int)mWorldPosition.getY()/w.getTileSize()); //update tile that the mouse is hovering
        }

        int pTileX = (int)((pX+(pSize/2))/w.getTileSize());
        int pTileY = (int)((pY+(pSize/2))/w.getTileSize());
        pTileOn = w.getTile(pTileX, pTileY); //update tile that player is hovering

        switch (pTileOn.getType()){ //change player's speed depending on tile hovering
            case "ocean": pV = 0.5;
                break;
            case "shore": pV = 1.5;
                break;
            case "beach": pV = 3;
                break;
            case "grass": pV = 5;
                break;
            case "tree": pV = 5;
                break;
            case "mountain": pV = 5;
                break;
            case "snowcap": pV = 5;
                break;
        }

        if (pUp) pY-=pV; //move player set velocity via key input
        if (pDown) pY+=pV;
        if (pLeft) pX-=pV;
        if (pRight) pX+=pV;

        if (pX < 0) pX = 0; //player within world width
        if (pX+pSize+5 > w.getWorldSize()*w.getTileSize()) pX = w.getWorldSize()*w.getTileSize() - pSize-5;
        if (pY < 0) pY = 0; //player within world height
        if (pY+pSize+28 > w.getWorldSize()*w.getTileSize()) pY = w.getWorldSize()*w.getTileSize() - pSize-28;
    }

    protected void draw(double sX,double sY, Graphics window){
        window.setColor(pC);
        window.fillRect((int)(pX-sX),(int)(pY-sY), pSize, pSize); //draw player to the screen
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //NOT USED
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W: pUp = true; //walking up
                break;
            case KeyEvent.VK_S: pDown = true; //walking down
                break;
            case KeyEvent.VK_A: pLeft = true; //walking left
                break;
            case KeyEvent.VK_D: pRight = true; //walking right
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W: pUp = false; //walking up
                break;
            case KeyEvent.VK_S: pDown = false; //walking down
                break;
            case KeyEvent.VK_A: pLeft = false; //walking left
                break;
            case KeyEvent.VK_D: pRight = false; //walking right
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){ //left click: hit tiles
            switch (mTileOn.hit(pTool)){ //hit : if destroy, get resource
                case "tree": trees++;
                    break;
                case "mountain": mountains++;
                    break;
                case "snowcap": mountains++;
                    break;
            }
        }else if (e.getButton() == MouseEvent.BUTTON3){ //right click: place tiles
            if (mTileOn.getType().equals("grass")){ //if grass tile, place selected resource
                mTileOn.setType(pSelectedResource);
            }
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }

    public double getX() {
        return pX;
    }

    public double getY() {
        return pY;
    }

    public double getV() {
        return pV;
    }

    public int getSize() {
        return pSize;
    }
}