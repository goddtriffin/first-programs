package Tools;

import java.util.Random;

public class RandomNumberGenerator {

    Random r;

    public RandomNumberGenerator(){
        r = new Random();
    }

    public int getRandInt(int min, int max) {
        return r.nextInt((max - min) + 1) + min;
    }
}