package utils;
import java.util.Random;

// DÙNG ĐỂ RÚT BÀI

public class RandomUtil {
    private static final Random random = new Random();

    public static int getInt(int min, int max){
        return random.nextInt(max-min+1)+min;
    }
}
