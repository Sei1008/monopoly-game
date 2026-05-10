package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CommunityChest {

    private Map<Integer, String> cards;   // ID → mô tả lá bài
    private int lastDrawnCardID;          // ID lá vừa rút
    private Queue<Integer> deck;          // hàng đợi đã xáo trộn

    public CommunityChest() {
        cards = new HashMap<>();
        deck  = new LinkedList<>();
        initializeCards();
    }

    // ── Khởi tạo 16 lá bài ──────────────────────────────────────────────────
    private void initializeCards() {
        cards.put(0,  "Bank error in your favor.\nCollect $200.");
        cards.put(1,  "Life insurance matures.\nCollect $100.");
        cards.put(2,  "Doctor's fee.\nPay $50.");
        cards.put(3,  "From sale of stock.\nCollect $50.");
        cards.put(4,  "Beauty contest prize.\nCollect $20.");
        cards.put(5,  "It's your birthday!\nCollect $10 from each player.");
        cards.put(6,  "Income tax refund.\nCollect $20.");
        cards.put(7,  "Grand Opera Night.\nCollect $50 from each player.");
        cards.put(8,  "Holiday Fund matures.\nCollect $100.");
        cards.put(9,  "Receive for services.\nCollect $25.");
        cards.put(10, "Go to Jail.\nGo directly to Jail, do not pass Go, do not collect $200.");
        cards.put(11, "Advance to Go.\nCollect $200.");
        cards.put(12, "Get Out of Jail Free.\nThis card may be kept until needed.");
        cards.put(13, "Pay hospital fees.\nPay $100.");
        cards.put(14, "Pay school tax.\nPay $150.");
        cards.put(15, "You have won second prize in a beauty contest.\nCollect $10.");

        // Xáo trộn 1 lần đầu game, dùng Queue vòng tròn như Chance
        List<Integer> deckList = new ArrayList<>();
        for (int i = 0; i < 16; i++) deckList.add(i);
        Collections.shuffle(deckList);
        deck.addAll(deckList);
    }

    // ── Rút 1 lá (Queue vòng tròn) ──────────────────────────────────────────
    public int drawCard() {
        int topCard = deck.poll(); // lấy đầu Queue
        deck.add(topCard);         // cho lại cuối (vòng tròn)
        lastDrawnCardID = topCard;
        return topCard;
    }

    public String getCardDescription() {
        return cards.get(lastDrawnCardID);
    }

    // ── Áp dụng hiệu ứng lá bài ─────────────────────────────────────────────
    public void applyCardEffect(Player player, int freeParkingPool,
                                List<Player> allPlayers, int[] poolRef) {
        switch (lastDrawnCardID) {

            case 0: // Bank error — collect $200
                player.addMoney(200);
                System.out.println(player.getName() + " collects $200 (bank error).");
                break;

            case 1: // Life insurance — collect $100
                player.addMoney(100);
                System.out.println(player.getName() + " collects $100 (life insurance).");
                break;

            case 2: // Doctor's fee — pay $50
                player.deductMoney(50);
                poolRef[0] += 50; // tiền vào Free Parking pool
                System.out.println(player.getName() + " pays doctor's fee $50.");
                break;

            case 3: // Sale of stock — collect $50
                player.addMoney(50);
                System.out.println(player.getName() + " collects $50 (stock).");
                break;

            case 4: // Beauty contest — collect $20
                player.addMoney(20);
                System.out.println(player.getName() + " collects $20 (beauty contest).");
                break;

            case 5: // Birthday — collect $10 from each player
                for (Player other : allPlayers) {
                    if (!other.equals(player)) {
                        other.deductMoney(10);
                        player.addMoney(10);
                        System.out.println(player.getName() + " collects $10 from " + other.getName());
                    }
                }
                break;

            case 6: // Income tax refund — collect $20
                player.addMoney(20);
                System.out.println(player.getName() + " collects $20 (tax refund).");
                break;

            case 7: // Grand Opera Night — collect $50 from each player
                for (Player other : allPlayers) {
                    if (!other.equals(player)) {
                        other.deductMoney(50);
                        player.addMoney(50);
                        System.out.println(player.getName() + " collects $50 from " + other.getName());
                    }
                }
                break;

            case 8: // Holiday Fund — collect $100
                player.addMoney(100);
                System.out.println(player.getName() + " collects $100 (holiday fund).");
                break;

            case 9: // Services — collect $25
                player.addMoney(25);
                System.out.println(player.getName() + " collects $25 (services).");
                break;

            case 10: // Go to Jail
                player.setPosition(10); // 0-based: ô Jail = 10
                player.setInJail(true);
                System.out.println(player.getName() + " goes to Jail!");
                break;

            case 11: // Advance to Go — collect $200
                player.setPosition(0);
                player.addMoney(200);
                System.out.println(player.getName() + " advances to GO and collects $200.");
                break;

            case 12: // Get Out of Jail Free
                player.addJailFreeCard();
                System.out.println(player.getName() + " gets a Get Out of Jail Free card!");
                break;

            case 13: // Hospital fees — pay $100
                player.deductMoney(100);
                poolRef[0] += 100;
                System.out.println(player.getName() + " pays hospital fees $100.");
                break;

            case 14: // School tax — pay $150
                player.deductMoney(150);
                poolRef[0] += 150;
                System.out.println(player.getName() + " pays school tax $150.");
                break;

            case 15: // Second prize beauty contest — collect $10
                player.addMoney(10);
                System.out.println(player.getName() + " collects $10 (2nd prize beauty contest).");
                break;

            default:
                break;
        }
    }
}