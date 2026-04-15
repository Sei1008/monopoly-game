package service;

import model.*;
import java.util.List;

public class GameLogic {
    private Board board;
    private List<Player> players;
    private Dice dice;
    private int currentPlayerIndex;

    public GameLogic(Board board, List<Player> players){
        this.board=board;
        this.players=players;
        this.currentPlayerIndex=0;
        this.dice=new Dice();
    }

    public void playTurn(Player currentPlayer){
        System.out.println("\n--- " + currentPlayer.getName() + "'s Turn ---");

        dice.roll();
        System.out.println("Rolled: " + dice.getDice1() + " + " + dice.getDice2() + " = " + dice.getTotal());

        int newPosition = (currentPlayer.getPosition() + dice.getTotal()) % 40;
        currentPlayer.setPosition(newPosition);
        System.out.println(currentPlayer.getName() + " moves to position " + newPosition);

        handleSquareLanding(currentPlayer);

        if(dice.isDoubles()){
            System.out.println("Doubles! " + currentPlayer.getName() + " rolls again!");
            playTurn(currentPlayer);
        }
    }
    private void handleSquareLanding(Player player){
        Square square = board.getSquare(player.getPosition());

        System.out.println("Landed on: " + square.getName());
        switch(square.getType()){
            case PROPERTY:
                handlePropertyLanding(player, square.getProperty());
                break;
            case CHANCE:
                handleChance(player);
                break;
            case COMMUNITY_CHEST:
                handleCommunityChest(player);
                break;
            case GO:
                System.out.println("You're at GO! Nothing happens (you get $200 when you PASS Go)");
                break;
            case JAIL:
                System.out.println("You're in Jail - just visiting");
                break;
            case FREE_PARKING:
                System.out.println("Free Parking - Nothing happens");
                break;
            case GO_JAIL:
                handleGoToJail(player);
                player.UseJailFreeCard();
                break;
            case TAX:
                handleTax(player);
                break;
        }
    }
    private void handlePropertyLanding(Player player,Property property){
        if(!property.isOwned()){
            System.out.println(property.getName() + " is unowned!");
            System.out.println("Price: $" + property.getPrice());
        }else if(property.getOwner() != player){
            Player owner=property.getOwner();
            int rent = property.getRent();

            System.out.println("Owned by: " + owner.getName());
            System.out.println("Pay rent: $" + rent);

            player.deductMoney(rent);
            owner.addMoney(rent);

            System.out.println(player.getName() + " now has $" + player.getMoney());
            System.out.println(owner.getName() + " now has $" + owner.getMoney());
        }else {
            System.out.println("You own this property!");
        }
    }
    //Chance do later
    private void handleChance(Player player){
        System.out.println("You drew a Chance card!");
    }
    //Community Chest do later
    private void handleCommunityChest(Player player){
        System.out.println("You drew a Community Chest card!");
    }
    private void handleGoToJail(Player player){
        System.out.println("GO TO JAIL! Do not pass GO, do not collect $200");
        player.setPosition(10);
        player.setInJail(true);
    }
    private void handleTax(Player player){
        int tax=200;
        System.out.println("Pay Income Tax: $" + tax);
        player.deductMoney(tax);
        System.out.println(player.getName() + " now has $" + player.getMoney());
    }
    public Player getNextPlayer(){
        currentPlayerIndex=(currentPlayerIndex+1)%players.size();
        return players.get(currentPlayerIndex);
    }
    public Player getCurrentPlayer(){return players.get(currentPlayerIndex);}
    public boolean isGameOver(){
        int activePlayers=0;
        for(Player player:players){
            if(!player.isBankrupt()) activePlayers++;
        }
        return activePlayers <= 1;
    }
    public Player getWinner(){
        for(Player player:players){
            if(!player.isBankrupt()) return player;
        }
        return null;
    }
    public void displayGameStatus() {
        System.out.println("\n=== Game Status ===");
        for (Player player : players) {
            System.out.println(player.getName() + " - Money: $" + player.getMoney() 
                             + " | Properties: " + player.getPropertiesCount() 
                             + " | Bankrupt: " + player.isBankrupt());
        }
        System.out.println("==================\n");
    }
}
