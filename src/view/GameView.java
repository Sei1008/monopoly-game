package view;
import model.*;

public interface GameView {
    void showMessage(String message);
    void showDiceRoll(int dice1,int dice2,int total);
    void updatePlayerPosition(Player player,String squareName);
    void updatePlayerMoney(Player player);
    boolean askToBuyProperty(Player player,Property property);
}
