package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Queue;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Chance {
    private Map<Integer, String> chanceCards;
    private int lastDrawnCardID;
    private Queue<Integer> deck;
    public Chance() {
        chanceCards = new HashMap<>();
        deck = new LinkedList<>();
        initializeChance(); 
    }

    private void initializeChance() {
        chanceCards.put(0, "Advance to Boardwalk.");
        chanceCards.put(1, "Advance to Go (Collect $200).");
        chanceCards.put(2, "Advance to Illinois Avenue. If you pass Go, collect $200.");
        chanceCards.put(3, "Advance to St. Charles Place. If you pass Go, collect $200.");
        chanceCards.put(4, "Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay owner twice the rental to which they are otherwise entitled.");
        chanceCards.put(5, "Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay owner twice the rental to which they are otherwise entitled.");
        chanceCards.put(6, "Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times amount thrown.");
        chanceCards.put(7, "Bank pays you dividend of $50.");
        chanceCards.put(8, "Get Out of Jail Free.");
        chanceCards.put(9, "Go Back 3 Spaces.");
        chanceCards.put(10, "Go to Jail. Go directly to Jail, do not pass Go, do not collect $200.");
        chanceCards.put(11, "Make general repairs on all your property. For each house pay $25. For each hotel pay $100.");
        chanceCards.put(12, "Speeding fine $15.");
        chanceCards.put(13, "Take a trip to Reading Railroad. If you pass Go, collect $200.");
        chanceCards.put(14, "You have been elected Chairman of the Board. Pay each player $50.");
        chanceCards.put(15, "Your building loan matures. Collect $150.");

        List <Integer> deckList = new ArrayList<>();
        for (int i = 0 ; i < 16; i++){
            deckList.add(i);
        }
        //Shuffle dlist
        Collections.shuffle(deckList);
        //add to deck
        deck.addAll(deckList);
    }
public int drawCard(){
    int topCard = deck.poll();
    if (topCard != 8){
        deck.add(topCard);
    }
    lastDrawnCardID = topCard;
    return topCard;
    }
    public String getCardDescription() {
        return chanceCards.get(lastDrawnCardID);
    }

    private void moveTo(Player player, int targetPosition) {
        int currentPos = player.getPosition();
        player.setPosition(targetPosition);

        // If target position is less than current position, player passed GO
        if (targetPosition < currentPos) {
            player.addMoney(200);
        }
    }

    private int findNearestRailroad(int currentPosition) {
        // Railroads are at positions: 5, 15, 25, 35
        int[] railroads = {5, 15, 25, 35};
        
        for (int rr : railroads) {
            if (rr > currentPosition) {
                return rr;
            }
        }
        // If no railroad ahead, go to the first one (wrap around)
        return railroads[0];
    }

    private int findNearestUtility(int currentPosition) {
        // Utilities are at positions: 12, 28
        int[] utilities = {12, 28};
        
        for (int util : utilities) {
            if (util > currentPosition) {
                return util;
            }
        }
        // If no utility ahead, go to the first one (wrap around)
        return utilities[0];
    }

    public void applyCardEffect(Player player, Board board, Dice dice, List<Player> allPlayers) {
        int cardID = lastDrawnCardID;
        
        switch (cardID) {
            case 0: // Advance to Boardwalk
                moveTo(player,39);
                break;

            case 1: // Advance to Go (Collect $200)
                player.setPosition(0);
                player.addMoney(200);
                break;

            case 2: // Advance to Illinois Avenue
                moveTo(player, 24);
                break;

            case 3: // Advance to St. Charles Place
                moveTo(player, 11);;
                break;

            case 4: // case 4 = case 5
            case 5: // Advance to nearest Railroad
                int nearestRR = findNearestRailroad(player.getPosition());
                moveTo(player, nearestRR);
                
                Property rrProperty = board.getSquare(nearestRR).getProperty();
                if (rrProperty.isOwned()) {
                    int doublRent = rrProperty.getRent() * 2;
                    Player owner = rrProperty.getOwner();
                    player.deductMoney(doublRent);
                    owner.addMoney(doublRent);
                }
                break;

            case 6: // Advance to nearest Utility
                int nearestUtil = findNearestUtility(player.getPosition());
                moveTo(player, nearestUtil);
                
                Property utilProperty = board.getSquare(nearestUtil).getProperty();
                if (utilProperty.isOwned()) {
                    dice.roll();
                    int diceTotal = dice.getTotal();
                    int utilityRent = diceTotal * 10;
                    Player owner = utilProperty.getOwner();
                    player.deductMoney(utilityRent);
                    owner.addMoney(utilityRent);
                }
                break;

            case 7: // Bank pays dividend of $50
                player.addMoney(50);
                break;

            case 8: // Get Out of Jail Free
                player.addJailFreeCard();
                break;

            case 9: // Go Back 3 Spaces
                int backPosition = (player.getPosition() - 3 + 40) % 40;
                player.setPosition(backPosition);
                // Handle the square they land on
                break;

            case 10: // Go to Jail
                player.setPosition(10);
                player.setInJail(true);
                break;

            case 11: // Make general repairs
                int totalRepairCost = 0;

                for (Property prop : player.getProperties()) {
                totalRepairCost += prop.getRepairCost();
            }
                player.deductMoney(totalRepairCost);
                break;

            case 12: // Speeding fine $15
                player.deductMoney(15);
                break;

            case 13: // Take a trip to Reading Railroad
                moveTo(player, 5);
                Property readingRR = board.getSquare(5).getProperty();
                if (readingRR.isOwned()) {
                    int rent = readingRR.getRent();
                    Player owner = readingRR.getOwner();
                    player.deductMoney(rent);
                    owner.addMoney(rent);
                }
                break;

            case 14: // Chairman of the Board - Pay each player $50
                for (Player otherPlayer : allPlayers) {
                    if (!otherPlayer.equals(player)) {
                        player.deductMoney(50);
                        otherPlayer.addMoney(50);
                    }
                }
                break;

            case 15: // Building loan matures - Collect $150
                player.addMoney(150);
                break;

            default:
                break;
        }
    }
}