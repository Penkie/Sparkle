package helper;

import java.util.Random;

public class randomizer {
    public static int random(int min, int max) {
        Random r = new Random();
        return r.nextInt(max-min) + min;
    }
}
