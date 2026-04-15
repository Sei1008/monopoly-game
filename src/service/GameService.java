package service;
import config.GameConfig;
import java.util.ArrayList;
import java.util.List;
import model.*;

import model.*;
import view.GameView;
import java.util.ArrayList;
import java.util.List;

public class GameService {
<<<<<<< HEAD
    private Board board;
    private List<Player> players;
    private GameLogic gameLogic;
    private Dice dice;
    private int currentPlayerPosition;
    private GameView view;
    private Chance chanceDeck;

    public GameService(Board board,List<Player> players,GameView view){
        this.board=board;
        this.players=players;
        this.view=view;
        this.dice=new Dice();
        this.gameLogic=new GameLogic();
        this.chanceDeck=new Chance();
    }

    public void playTurn(Player player){
        view.showMessage(player.getName()+"'s turn");
        dice.roll();
        view.showDiceRoll(dice.getDice1(), dice.getDice2(), dice.getTotal());
        int newPosition=(player.getPosition() + dice.getTotal()) % 40;
        if (newPosition < player.getPosition() && !player.isInJail()) {
            player.addMoney(200);
            view.showMessage(player.getName() + " go through GO, received $200");
            view.updatePlayerMoney(player);
        }
        player.setPosition(newPosition);
        Square square=board.getSquare(newPosition);
        view.updatePlayerPosition(player,square.getName());
        handleSquareAction(player, square);
    }

    public void handleSquareAction(Player player,Square square){
        switch (square.getType()){
            case PROPERTY:
                handleProperty(player, square.getProperty());
                break;
            case TAX:
                handleTax(player, square);
                break;
            case CHANCE:
                handleChance(player);
                break;
            case GO_JAIL:
                handleGoJail(player);
                break;
            default: break;

        }
    }
    private void handleProperty(Player player,Property prop){
        if(!prop.isOwned()){
            if(gameLogic.canBuyProperty(player, prop)){
                boolean confirmBuy=view.askToBuyProperty(player, prop);
                if(confirmBuy){
                    player.deductMoney(prop.getPrice());
                    prop.setOwner(player);
                    player.addProperty(prop);
                    view.showMessage("Buy successful");
                    view.updatePlayerMoney(player);
                }
            }else{
                view.showMessage("Dont have enough money");
            }
        }else if(prop.getOwner() != player){
            int rent=gameLogic.calculateRent(prop, dice);
            player.deductMoney(rent);
            prop.getOwner().addMoney(rent);
            view.showMessage("Pay $"+rent+"to "+prop.getOwner().getName());
        }
    }
    private void handleTax(Player player,Square square){
        int tax=gameLogic.calculateTax(square.getName());
        player.deductMoney(tax);
        view.showMessage("Pay "+tax);
        view.updatePlayerMoney(player);
    }
    private void handleChance(Player player){
        chanceDeck.drawCard();
        view.showMessage("Chance Card: " + chanceDeck.getCardDescription());
        chanceDeck.applyCardEffect(player, board, dice, players);
        Square newSquare = board.getSquare(player.getPosition());
        view.updatePlayerPosition(player, newSquare.getName());
        view.updatePlayerMoney(player);
    }
    private void handleGoJail(Player player) {
        view.showMessage("Go directly to Jail! Do not pass GO.");
        player.setPosition(10);
        player.setInJail(true);
        view.updatePlayerPosition(player, "Jail");
    }
    public void startGame() {
        view.showMessage("===Welcome to Monopoly===");
        
        while (!gameLogic.isGameOver(players)) { 
            for (Player currentPlayer : players) {
                if(gameLogic.isGameOver(players)) break;
                view.showMessage("\nPress Enter to start " + currentPlayer.getName() + "...");
                new java.util.Scanner(System.in).nextLine(); 
                playTurn(currentPlayer);
                if(currentPlayer.isBankrupt()) view.showMessage(currentPlayer.getName()+"is Bankrupt!");
            }
        }
        Player winner = gameLogic.getWinner(players);
        if(winner != null) view.showMessage(winner.getName()+"is winner!");
    }
=======
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
    

>>>>>>> c88df04 (first commit)
}
