package net.magnusfrater.tds.level;

public class Camera {

    private double x;
    private double y;

    public Camera () {
        this.x = 0;
        this.y = 0;
    }

    public Camera (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setPosition (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX () {
        return this.x;
    }

    public double getY () {
        return this.y;
    }
}
