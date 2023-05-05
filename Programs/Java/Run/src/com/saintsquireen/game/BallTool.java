package com.saintsquireen.game;

import java.awt.*;
import java.util.Random;

public class BallTool {
    CollisionTool ct;

    Random r;

    private int screenWidth, screenHeight;
    private int floorY; //floor height

    private int bSize; //ball size
    private int bSpeed; //ball speed
    private int numSpawnBalls; //number of balls spawned (every 5s)
    private int numBalls; //total balls
    private Ball[] balls;

    //ball powerups
    private boolean ballsColliding; //if balls should collide with each other
    private boolean walls; //if balls should collide with left/right walls

    public BallTool(int screenWidth, int screenHeight, int floorY){
        ct = new CollisionTool();

        r = new Random();

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.floorY = floorY;

        bSize = 30;
        bSpeed = 5;
        numSpawnBalls = 1;
        numBalls = 0;
        balls = new Ball[numBalls];

        ballsColliding = false;
        walls = true;

        addBall(this.screenWidth);
    }

    public void addBall(int screenWidth){ //adds a new ball to 'balls' array
        Ball[] temp = balls; //temporary array stores old 'balls' data

        numBalls++; //increment number of balls
        balls = new Ball[numBalls]; //reset size of 'balls'

        for (int i=0;i<numBalls-1;i++){ //adds back old 'balls' data to new 'balls'
            balls[i] = temp[i];
        }
        balls[numBalls-1] = new Ball(screenWidth, bSpeed,bSpeed,bSize, Integer.toString(numBalls)); //adds newest ball to 'balls'
    }

    public void moveAllBalls(){ //moves all balls in 'ball' array
        for (int i=0; i<numBalls;i++){
            balls[i].move();
        }
    }

    public void drawAllBalls(Graphics window){ //draws all balls in 'ball' array
        for (int i=0; i<numBalls;i++){
            balls[i].draw(window);
        }
    }

    public boolean checkAllBallCollisions(Rectangle playerRect){ //checks all balls for a collision
        boolean collidesPlayer = false;

        for (int i=0; i<numBalls;i++){
            if (ct.isCollidingTop(balls[i].y, 0)) balls[i].vY*=-1; //collision: ceiling
            if (ct.isCollidingBot(balls[i].y, balls[i].height, floorY)) balls[i].vY*=-1; //collision: floor

            if (walls){ //if balls collide with left/right walls
                if (ct.isCollidingLeft(balls[i].x,0)) balls[i].vX *= -1; //collision: left wall
                if (ct.isCollidingRight(balls[i].x,balls[i].width,screenWidth)) balls[i].vX*=-1; //collision: right wall
            } else{
                if (ct.isCollidingLeft(balls[i].x,-17)) balls[i].x = screenWidth-bSize + 17; //collision: left wall - spawns next to right wall
                if (ct.isCollidingRight(balls[i].x,balls[i].width,screenWidth + 17)) balls[i].x = -17; //collision: right wall - spawns next to left wall
            }

            if (ballsColliding){ //if balls collide with each other
                for (int j=0; j<numBalls;j++){
                    if (ct.isCollidingRects(balls[i].x,balls[i].y,balls[i].width,balls[i].height, balls[j].x,balls[j].y,balls[j].width,balls[j].height)){
                        if (!balls[i].tag.equals(balls[j].tag)){
                            balls[i].setVX(balls[i].getVX()*-1);
                            balls[i].setVY(balls[i].getVY()*-1);
                        }
                    }
                }
            }

            if (ct.isCollidingRects(playerRect.x,playerRect.y,playerRect.width,playerRect.height, balls[i].x,balls[i].y, balls[i].width,balls[i].height)) collidesPlayer = true; //collision: player
        }

        return collidesPlayer;
    }

    public void flipAllBallDirections(){ //flips (*-1) vX/vY of all balls
        for (int i=0; i<numBalls;i++){
            balls[i].setVX(balls[i].getVX()*-1);
            balls[i].setVY(balls[i].getVY() * -1);
        }
    }

    private int rndInt(int low, int high){ //returns random integer between specified min and max
        return r.nextInt(high) + low;
    }

    public int getBSize(){ return this.bSize; } //gets-sets bSize
    public void setBSize(int bSize){ this.bSize = bSize; }
    public int getBSpeed(){ return this.bSpeed; } //gets-sets bSpeed
    public void setBSpeed(int bSpeed){ this.bSpeed = bSpeed; }
    public int getNumSpawnBalls(){ return this.numSpawnBalls; } //gets-sets numSpawnBalls
    public void setNumSpawnBalls(int numSpawnBalls){ this.numSpawnBalls = numSpawnBalls; }
    public int getNumBalls(){ return this.numBalls; } //gets numBalls

    //powerups
    public boolean getBallsColliding() { return this.ballsColliding; } //gets-sets ballsColliding
    public void setBallsColliding(boolean ballsColliding){ this.ballsColliding = ballsColliding; }
    public boolean getWalls() { return this.walls; } //gets-sets walls
    public void setWalls(boolean walls){ this.walls = walls; }
}