package utils;

public class IsValidUtil {
    public static boolean isValidPlayerCount(int count){
        return count >= 2 && count <=4;
    }

    public static boolean isPositive(int count){
        return count >=0;
    }
}
