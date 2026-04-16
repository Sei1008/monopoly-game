package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    // ===== DATA =====
    private int expectedPlayers = 0;
    private List<String> playerNames = new ArrayList<>();

    private boolean rollClicked = false;
    private Boolean buyDecision = null;

    // ===== UI =====
    @FXML
    private TextField numPlayersField;

    @FXML
    private TextField playerNameField;

    @FXML
    private ListView<String> playerListView;

    @FXML
    private Label statusLabel;

    @FXML
    private Label currentPlayerLabel;

    @FXML
    private Label diceLabel;

    // ===== INIT =====
    public void initialize() {
        statusLabel.setText("Enter number of players");
    }

    // ===== 1. nhập số người =====
    @FXML
    public void handleSetPlayers() {
        try {
            int n = Integer.parseInt(numPlayersField.getText());

            if (n < 2 || n > 6) {
                statusLabel.setText("Players must be 2-6!");
                return;
            }

            expectedPlayers = n;
            statusLabel.setText("Add player names");

        } catch (Exception e) {
            statusLabel.setText("Invalid number!");
        }
    }

    public int getExpectedPlayers() {
        return expectedPlayers;
    }

    // ===== 2. thêm player =====
    @FXML
    public void handleAddPlayer() {

        if (playerNames.size() >= expectedPlayers) {
            statusLabel.setText("Enough players!");
            return;
        }

        String name = playerNameField.getText().trim();

        if (name.isEmpty()) {
            statusLabel.setText("Enter name!");
            return;
        }

        playerNames.add(name);
        playerListView.getItems().add(name);

        playerNameField.clear();
        statusLabel.setText("Added: " + name);
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }

    // ===== 3. start game =====
    @FXML
    public void handleStartGame() {
        if (playerNames.size() < expectedPlayers) {
            statusLabel.setText("Not enough players!");
            return;
        }

        statusLabel.setText("Game Started!");
    }

    // ===== 4. roll dice =====
    @FXML
    public void handleRollDice() {
        rollClicked = true;
        statusLabel.setText("Roll clicked");
    }

    public boolean isRollClicked() {
        return rollClicked;
    }

    public void resetRoll() {
        rollClicked = false;
    }

    // ===== 5. buy decision =====
    @FXML
    public void handleBuyYes() {
        buyDecision = true;
        statusLabel.setText("Chose YES");
    }

    @FXML
    public void handleBuyNo() {
        buyDecision = false;
        statusLabel.setText("Chose NO");
    }

    public Boolean getBuyDecision() {
        return buyDecision;
    }

    public void resetBuyDecision() {
        buyDecision = null;
    }

    // ===== UI UPDATE (file khác gọi) =====

    public void updateCurrentPlayer(String name) {
        currentPlayerLabel.setText("Current Player: " + name);
    }

    public void updateDice(int dice) {
        diceLabel.setText("Dice: " + dice);
    }

    public void updateStatus(String msg) {
        statusLabel.setText(msg);
    }
}