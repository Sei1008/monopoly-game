package utils;

public class NextPosition {
    
    public static int whereNext(int current, int move, int boardSize){
        return (current + move)%boardSize;
        // ví dụ current ở ô 36, move 6, boardSize = 40 
        // => R = 2
    }

    public static boolean passedGo(int current, int move, int boardSize){
        return (current+move)>=boardSize;

        // nếu đi qua ô GO > trả về true
    }
}
