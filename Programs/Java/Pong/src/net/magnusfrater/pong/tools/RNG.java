package net.magnusfrater.pong.tools;

import java.util.Random;

public class RNG {

    public static Random r;

    public RNG(){
        r = new Random();
    }

    public static int getRandInt(int min, int max) {
        return r.nextInt((max - min) + 1) + min;
    }
}
