package utils;

public class MoneyUtil {
    public static int add(int balance, int amount){
        return balance+amount;
    }
    public static int subtract(int balance, int amount){
        return balance-amount;
    }
    public static boolean canAfford(int balance, int amount){
        return balance >= amount;
    }
}
