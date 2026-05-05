package model;

import javafx.scene.control.Label;

public class PlayerArea {
    private Player player;
    private Label labelAmount;
    private boolean[] playersOwnedPlaces;

    public PlayerArea(Player player, int initialAmount, Label labelAmount, int boardSize) {
        this.player = player;
        this.player.setMoney(initialAmount);
        this.labelAmount = labelAmount;
        this.playersOwnedPlaces = new boolean[boardSize]; // boardSize should be 41 (0-40)
    }

    public int getPlayer_amount() {
        return player.getMoney();
    }

    public void setPlayer_amount(int playerAmount) {
        player.setMoney(playerAmount);
    }

    public Label getLabel_amount() {
        return labelAmount;
    }

    public boolean[] getPlayers_owned_places() {
        return playersOwnedPlaces;
    }

    public Player getPlayer() {
        return player;
    }
}
