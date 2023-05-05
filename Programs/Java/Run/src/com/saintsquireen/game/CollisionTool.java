package com.saintsquireen.game;

import java.awt.*;

public class CollisionTool {

    protected boolean isCollidingTop(int y, int topY){ //if top of object above specified threshold
        if (y<topY) return true;
        return false;
    }
    protected boolean isCollidingBot(int y, int height, int botY){ //if bottom of object is below specified threshold
        if(y+height>botY) return true;
        return false;
    }
    protected boolean isCollidingLeft(int x, int leftX){ //if left of object is leftward of specified threshold
        if (x<leftX) return true;
        return false;
    }
    protected boolean isCollidingRight(int x, int width, int rightX){ //if right of object is rightward of specified threshold
        if (x+width>rightX) return true;
        return false;
    }

    protected boolean isCollidingRects(int x1,int y1,int width1,int height1, int x2,int y2,int width2,int height2){ //returns true if rectangle collision detected
        Rectangle r1 = new Rectangle(x1,y1,width1,height1);
        Rectangle r2 = new Rectangle(x2,y2,width2,height2);

        return r1.intersects(r2);
    }
    protected boolean isCollidingRects(int x1,int y1,int size1, int x2,int y2,int size2){ //r1:width=height - r2:width=height
        return isCollidingRects(x1, y1, size1, size1, x2, y2, size2, size2);
    }
    protected boolean isCollidingRects(int x1,int y1,int width1,int height1, int x2,int y2,int size2){ //r1:width!=height - r2:width=height
        return isCollidingRects(x1, y1, width1, height1, x2, y2, size2, size2);
    }
}