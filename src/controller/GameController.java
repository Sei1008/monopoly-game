package controller;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.*;

public class GameController {

    // NHẬP SỐ NG CHƠI
    public int inputNumberOfPlayers() {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog("Enter number of players:");
                int n = Integer.parseInt(input);

                if (n > 1) return n;

                JOptionPane.showMessageDialog(null, "Must be at least 2 players!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid number!");
            }
        }
    }

    // NHẬP TÊN NG CHƠI
    public List<Player> inputPlayerNames(int n) {
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String name;

            while (true) {
                name = JOptionPane.showInputDialog("Enter name of player " + (i + 1));

                if (name != null && !name.trim().isEmpty()) break;

                JOptionPane.showMessageDialog(null, "Name cannot be empty!");
            }

            players.add(new Player(name));
        }

        return players;
    }

    // ROLL DICE CONFIRM
public boolean waitForRoll(String playerName) {
    int choice = JOptionPane.showConfirmDialog(
            null, 
            playerName + "'s turn. Do you want to roll the dice?", 
            "Your Turn", 
            JOptionPane.YES_NO_OPTION
    );
    return choice == JOptionPane.YES_OPTION;
}

    // BUY PROPERTY
    public boolean askBuyProperty(String propertyName, int price) {
        int choice = JOptionPane.showConfirmDialog(
                null,
                "Buy " + propertyName + " for $" + price + "?",
                "Buy Property",
                JOptionPane.YES_NO_OPTION
        );

        return choice == JOptionPane.YES_OPTION;
    }

    // HIỆN THÔNG BÁO GÌ ĐÓ
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}