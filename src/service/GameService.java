package service;
import config.GameConfig;
import java.util.ArrayList;
import java.util.List;
import model.Board;
import model.Dice;
import model.Player;

public class GameService {
     Board board;
    List<Player> players = new ArrayList<>();
    Dice dice;
    int currentPlayer;
   public void initGame(GameConfig config) {
       players = new ArrayList<>();
        board = new Board();   
        config.loadConfig();
        dice= new Dice();

    }
    

}
