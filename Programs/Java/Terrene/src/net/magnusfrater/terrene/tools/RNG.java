package net.magnusfrater.terrene.tools;

import java.util.Random;

public class RNG {

    private Random r;

    public RNG(){
        r = new Random();
    }

    public void setSeed(long seed){
        r = new Random(seed);
    }

    public int getRandInt(int min, int max) {
        return r.nextInt((max - min) + 1) + min;
    }
}
