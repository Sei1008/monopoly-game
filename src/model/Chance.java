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
    deck.add(topCard);
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
            System.out.println(player.getName() + " passed GO -> Received $200");
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
                System.out.println(player.getName() + " advances to Boardwalk.");
                break;

            case 1: // Advance to Go (Collect $200)
                player.setPosition(0);
                player.addMoney(200);
                System.out.println(player.getName() + " advances to GO and collects $200.");
                break;

            case 2: // Advance to Illinois Avenue
                moveTo(player, 24);
                System.out.println(player.getName() + " advances to Illinois Avenue.");
                break;

            case 3: // Advance to St. Charles Place
                moveTo(player, 11);
                System.out.println(player.getName() + " advances to St. Charles Place.");
                break;

            case 4: // case 4 = case 5
            case 5: // Advance to nearest Railroad
                int nearestRR = findNearestRailroad(player.getPosition());
                moveTo(player, nearestRR);
                System.out.println(player.getName() + " advances to the nearest Railroad.");
                
                Property rrProperty = board.getSquare(nearestRR).getProperty();
                if (!rrProperty.isOwned()) {
                    System.out.println("This Railroad is unowned. You may buy it from the Bank for $" + rrProperty.getPrice());
                } else {
                    int doublRent = rrProperty.getRent() * 2;
                    Player owner = rrProperty.getOwner();
                    player.deductMoney(doublRent);
                    owner.addMoney(doublRent);
                    System.out.println(player.getName() + " pays " + owner.getName() + " $" + doublRent + " (double rent).");
                }
                break;

            case 6: // Advance to nearest Utility
                int nearestUtil = findNearestUtility(player.getPosition());
                moveTo(player, nearestUtil);
                System.out.println(player.getName() + " advances to the nearest Utility.");
                
                Property utilProperty = board.getSquare(nearestUtil).getProperty();
                if (!utilProperty.isOwned()) {
                    System.out.println("This Utility is unowned. You may buy it from the Bank for $" + utilProperty.getPrice());
                } else {
                    dice.roll();
                    int diceTotal = dice.getTotal();
                    int utilityRent = diceTotal * 10;
                    Player owner = utilProperty.getOwner();
                    player.deductMoney(utilityRent);
                    owner.addMoney(utilityRent);
                    System.out.println(player.getName() + " rolled " + diceTotal + " and pays " + owner.getName() + " $" + utilityRent);
                }
                break;

            case 7: // Bank pays dividend of $50
                player.addMoney(50);
                System.out.println("Bank pays " + player.getName() + " dividend of $50.");
                break;

            case 8: // Get Out of Jail Free
                System.out.println(player.getName() + " gets a Get Out of Jail Free card!");
                player.addJailFreeCard();
                break;

            case 9: // Go Back 3 Spaces
                int backPosition = (player.getPosition() - 3 + 40) % 40;
                player.setPosition(backPosition);
                System.out.println(player.getName() + " goes back 3 spaces to position " + backPosition);
                // Handle the square they land on
                break;

            case 10: // Go to Jail
                player.setPosition(10);
                player.setInJail(true);
                System.out.println(player.getName() + " goes to Jail!");
                break;

            case 11: // Make general repairs
                int totalRepairCost = 0;
                int totalHouses = 0;
                int totalHotels = 0;
    
                for (Property prop : player.getProperties()) {
                totalHouses += prop.getHouses();
                totalHotels += prop.getHotels();
                totalRepairCost += prop.getRepairCost();
            }
                player.deductMoney(totalRepairCost);
                System.out.println(player.getName() + " makes general repairs:");
                System.out.println("  - Houses repaired: " + totalHouses + " x $25 = $" + (totalHouses * 25));
                System.out.println("  - Hotels repaired: " + totalHotels + " x $100 = $" + (totalHotels * 100));
                System.out.println("  - Total repair cost: $" + totalRepairCost);
                System.out.println("  - New balance: $" + player.getMoney());
                break;

            case 12: // Speeding fine $15
                player.deductMoney(15);
                System.out.println(player.getName() + " pays speeding fine of $15.");
                break;

            case 13: // Take a trip to Reading Railroad
                moveTo(player, 5);
                System.out.println(player.getName() + " takes a trip to Reading Railroad.");
                
                Property readingRR = board.getSquare(5).getProperty();
                if (!readingRR.isOwned()) {
                    System.out.println("Reading Railroad is unowned. You may buy it from the Bank for $" + readingRR.getPrice());
                } else {
                    int rent = readingRR.getRent();
                    Player owner = readingRR.getOwner();
                    player.deductMoney(rent);
                    owner.addMoney(rent);
                    System.out.println(player.getName() + " pays " + owner.getName() + " $" + rent);
                }
                break;

            case 14: // Chairman of the Board - Pay each player $50
                for (Player otherPlayer : allPlayers) {
                    if (!otherPlayer.equals(player)) {
                        player.deductMoney(50);
                        otherPlayer.addMoney(50);
                        System.out.println(player.getName() + " pays " + otherPlayer.getName() + " $50.");
                    }
                }
                break;

            case 15: // Building loan matures - Collect $150
                player.addMoney(150);
                System.out.println("Your building loan matures. " + player.getName() + " collects $150.");
                break;

            default:
                break;
        }
    }
}