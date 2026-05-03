package service;

import view.GameView;
import java.util.List;

import model.Dice;
import model.Player;
import model.Property;

public class GameLogic {
    public boolean canBuyProperty(Player player, Property property){
        return player.getMoney()>=property.getPrice() && !property.isOwned();
    }
    public int calculateRent(Property property,Dice dice){
        if(property.getColor().equals("utility")){
            return dice.getTotal()*10;
        }
        return property.getRent();
    }
    public int calculateTax(String taxType){
        return ((taxType.equals("Luxury Tax"))?100:200);
    }
    public boolean isGameOver(List<Player> players){
        int active=0;
        for(Player player:players){
            if(!player.isBankrupt()) active++;
        }
        return active<=1;
    }
    public Player getWinner(List<Player> players){
        for(Player player: players){
            if(!player.isBankrupt()) return player;
        }
        return null;
    }
}
