package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CommunityChest {
    private Map<Integer, String> chestCards;
    private int lastDrawnCardID;
    private Queue<Integer> deck;

    public CommunityChest() {
        chestCards = new HashMap<>();
        deck = new LinkedList<>();
        initializeChest(); 
    }

    private void initializeChest() {
        chestCards.put(0, "Advance to Go (Collect $200).");
        chestCards.put(1, "Bank error in your favor. Collect $200.");
        chestCards.put(2, "Doctor's fee. Pay $50.");
        chestCards.put(3, "From sale of stock you get $50.");
        chestCards.put(4, "Get Out of Jail Free.");
        chestCards.put(5, "Go to Jail. Go directly to jail, do not pass Go, do not collect $200.");
        chestCards.put(6, "Holiday fund matures. Receive $100.");
        chestCards.put(7, "Income tax refund. Collect $20.");
        chestCards.put(8, "It is your birthday. Collect $10 from every player.");
        chestCards.put(9, "Life insurance matures. Collect $100.");
        chestCards.put(10, "Pay hospital fees of $100.");
        chestCards.put(11, "Pay school fees of $50.");
        chestCards.put(12, "Receive $25 consultancy fee.");
        chestCards.put(13, "You are assessed for street repairs. $40 per house. $115 per hotel.");
        chestCards.put(14, "You have won second prize in a beauty contest. Collect $10.");
        chestCards.put(15, "You inherit $100.");

        List<Integer> deckList = new ArrayList<>();
        for (int i = 0 ; i < 16; i++){
            deckList.add(i);
        }
        Collections.shuffle(deckList);
        deck.addAll(deckList);
    }

    public int drawCard() {
        int topCard = deck.poll();
        if(topCard != 4){
            deck.add(topCard);
    }
        lastDrawnCardID = topCard;
        return topCard;
    }

    public String getCardDescription() {
        return chestCards.get(lastDrawnCardID);
    }

    public void applyCardEffect(Player player, Board board, Dice dice, List<Player> allPlayers) {
        int cardID = lastDrawnCardID;
        
        switch (cardID) {
            case 0: // Advance to Go
                player.setPosition(0);
                player.addMoney(200);
                break;

            case 1: // Bank error
                player.addMoney(200);
                break;

            case 2: // Doctor's fee
                player.deductMoney(50);
                break;

            case 3: // Stock sale
                player.addMoney(50);
                break;

            case 4: // Get Out of Jail Free
                player.addJailFreeCard();
                break;

            case 5: // Go to Jail
                player.setPosition(10);
                player.setInJail(true);
                break;

            case 6: // Holiday fund
                player.addMoney(100);
                break;

            case 7: // Tax refund
                player.addMoney(20);
                break;

            case 8: // Birthday - Collect $10 from everyone
                for (Player otherPlayer : allPlayers) {
                    // other player (not include yourself)
                    if (!otherPlayer.equals(player)) {
                        otherPlayer.deductMoney(10);
                        player.addMoney(10);
                    }
                }
                break;

            case 9: // Life insurance matures
                player.addMoney(100);
                break;

            case 10: // Hospital fees
                player.deductMoney(100);
                break;

            case 11: // School fees
                player.deductMoney(50);
                break;

            case 12: // Consultancy fee
                player.addMoney(25);
                break;

            case 13: // Street repairs
                int totalRepairCost = 0;
                int totalHouses = 0;
                int totalHotels = 0;
                
                //40$ per house and 115$ per hotel
                for (Property prop : player.getProperties()) {
                    totalHouses += prop.getHouses();
                    totalHotels += prop.getHotels();
                }
                
                totalRepairCost = (totalHouses * 40) + (totalHotels * 115);
                player.deductMoney(totalRepairCost);
                break;

            case 14: // Beauty contest
                player.addMoney(10);
                break;

            case 15: // Inherit $100
                player.addMoney(100);
                break;

            default:
                break;
        }
    }
}