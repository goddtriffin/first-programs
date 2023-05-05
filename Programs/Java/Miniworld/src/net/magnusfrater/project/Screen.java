package net.magnusfrater.project;

public class Screen {

    private double xOffset,yOffset;
    private double xSpeed,ySpeed;

    public Screen(){
        xOffset = yOffset = 0;
        xSpeed = ySpeed = 10;
    }
    public Screen(double xOffset,double yOffset, double xSpeed,double ySpeed){
        this.xOffset = xOffset;
        this.yOffset = yOffset;

        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    protected void tick(boolean[] keys){
        if (keys[87]) //up (w)
            yOffset+=ySpeed;
        if (keys[83]) //down (s)
            yOffset-=ySpeed;
        if (keys[65]) //up (w)
            xOffset+=xSpeed;
        if (keys[68]) //down (s)
            xOffset-=xSpeed;
    }

    protected double getxOffset(){ return xOffset; }
    protected double getyOffset(){ return yOffset; }
}