package com.saintsquireen.unit5;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.Random;

public class MovingShapePanel extends JPanel implements Runnable
{
    private int numShapes = 100;
    private Shape[] sh = new Shape[numShapes];

    private int shapeSize = 35;

    Random r = new Random();

    public MovingShapePanel(int panelWidth, int panelHeight){
        setBackground(Color.WHITE);
        setVisible(true);

        for(int i=0; i<numShapes;i++){
            int red = rndInt(0,255);
            int blue = rndInt(0,255);
            int green = rndInt(0,255);
            Color col = new Color(red,green,blue);

            sh[i] = new Shape(rndInt(10,panelWidth-shapeSize-10),rndInt(10,panelHeight-shapeSize-10), shapeSize,shapeSize, col, returnRandomNeg(5),5);
        }

        new Thread(this).start();
    }

    public void update(Graphics window)
    {
        paint(window);
    }

    public void paint(Graphics window){
        window.setColor(Color.WHITE);
        window.fillRect(0,0,getWidth(), getHeight());

        for (int i=0; i<numShapes;i++){
            sh[i].moveAndDraw(window);

            //this code handles the left and right walls
            if(!(sh[i].getX()>=0 && sh[i].getX()<=getWidth()-10)){
                sh[i].setXSpeed(-sh[i].getXSpeed());
            }

            //this code handles the top and bot walls
            if(!(sh[i].getY()>=0 && sh[i].getY()<=getHeight()-10)){
                sh[i].setYSpeed(-sh[i].getYSpeed());
            }
        }
    }

    public void run(){
        try
        {
            while(true)
            {
                Thread.currentThread().sleep(10);
                repaint();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    private int returnRandomNeg(int num){
        int rand = r.nextInt(2)+1;

        if (rand == 2) rand = -1;

        return rand*num;
    }

    private int rndInt(int low, int high){
        return r.nextInt(high) + low;
    }
}