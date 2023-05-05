package com.saintsquireen.unit5;

import javax.swing.JFrame;

public class GraphicsRunner extends JFrame
{
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public GraphicsRunner()
    {
        super("MAKE YOUR OWN SHAPE");

        setSize(WIDTH,HEIGHT);

        getContentPane().add(new MovingShapePanel(WIDTH, HEIGHT));

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main( String args[] )
    {
        GraphicsRunner run = new GraphicsRunner();
    }
}