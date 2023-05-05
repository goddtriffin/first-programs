package net.magnusfrater;


import Tools.RandomNumberGenerator;

import java.awt.*;

public class Tile {

    //general
    private RandomNumberGenerator rng = new RandomNumberGenerator();

    //tile
    private double tX,tY;
    private int tSize;

    private String tType;

    private int tHitPoints;

    public Tile(double tX,double tY, int tSize, String tType){
        this.tX=tX;
        this.tY=tY;
        this.tSize=tSize;
        this.tType = tType;

        initHitPoints();
    }

    private void initHitPoints(){
        switch (tType){
            case "ocean": tHitPoints = Integer.MIN_VALUE;
                break;
            case "shore": tHitPoints = Integer.MIN_VALUE;
                break;
            case "beach": tHitPoints = Integer.MIN_VALUE;
                break;
            case "grass": tHitPoints = Integer.MIN_VALUE;
                break;
            case "tree": tHitPoints = 4;
                break;
            case "mountain": tHitPoints = 9;
                break;
            case "snowcap": tHitPoints = 9;
                break;
        }
    }

    protected String hit(Tool tool){
        if (tHitPoints != Integer.MIN_VALUE){

            switch (tType){
                case "tree": tHitPoints -= tool.getWoodDamage();
                    break;
                case "mountain": tHitPoints -= tool.getStoneDamage();
                    break;
                case "snowcap": tHitPoints -= tool.getStoneDamage();
                    break;
            }

            if (tHitPoints <= 0){
                String preType = tType;

                setType("grass");

                return preType;
            }
        }

        return "";
    }

    protected void tick(){
        //unused
    }

    protected void draw(double sX,double sY, Graphics window){
        window.setColor(Color.pink);

        if (tType.equals("ocean"))
            window.setColor(new Color(68, 110, 255));
        if (tType.equals("shore"))
            window.setColor(new Color(60, 168, 171));
        if (tType.equals("beach"))
            window.setColor(new Color(215, 175, 137));
        if (tType.equals("grass"))
            window.setColor(new Color(19, 141,0));
        if (tType.equals("tree"))
            window.setColor(new Color(14, 78,0));
        if (tType.equals("mountain"))
            window.setColor(new Color(119, 119, 119));
        if (tType.equals("snowcap"))
            window.setColor(new Color(210, 210, 210));

        window.fillRect((int)(tX-sX),(int)(tY-sY), tSize,tSize);
    }

    protected double getX(){
        return this.tX;
    }

    protected double getY(){
        return this.tY;
    }

    protected int getSize(){
        return this.tSize;
    }

    protected String getType(){
        return this.tType;
    }
    protected void setType(String tType){
        this.tType = tType;
        initHitPoints();
    }

    protected int getHitPoints(){
        return this.tHitPoints;
    }
}