package utils;

import java.util.Random;

public class DiceUtil {
    private static final Random random = new Random();

    public static class DiceResult{
        public int d1;
        public int d2;
        public int total;

        public DiceResult(int d1, int d2){
            this.d1 = d1;
            this.d2 = d2;
            this.total = d1 + d2;
        }
    }
    public static DiceResult rollDice(){
        int d1 = random.nextInt(6)+1;
        int d2 = random.nextInt(6)+1;
        return new DiceResult(d1, d2);
    }

}
