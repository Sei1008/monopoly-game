package model;
import java.util.List;
import java.util.ArrayList;

public class Player {
    private String name;
    private int money;
    private boolean inJail;
    private int jailTurns = 0;
    private int currentPosition;
    private List<Property> list;
    private int getOutOfJailCard = 0;

    public Player(String name) {
        this.name = name;
        this.money = 1500;
        this.currentPosition = 0;
        this.inJail = false;
        this.jailTurns = 0;
        this.list = new ArrayList<>();
    }

    public void move(int steps) {
        currentPosition = (currentPosition + steps) % 40;
    }
    public void addMoney(int money) {
        this.money += money;
    }
    public void deductMoney(int money) {
        this.money -= money;
    }
    public boolean isBankrupt() {
        return money < 0;
    }
    public boolean isInJail() {
        return inJail;
    }
    public void setInJail(boolean inJail) {
        this.inJail = inJail;
        if (!inJail) this.jailTurns = 0;
    }
    public int getJailTurns() {
        return jailTurns;
    }
    public void incrementJailTurns() {
        jailTurns++;
    }
    public void addProperty(Property property) {
        list.add(property);
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPosition() {
        return currentPosition;
    }
    public void setPosition(int position) {
        this.currentPosition = position;
    }
    public List<Property> getProperties() {
        return list;
    }
    public int getPropertiesCount() {
        return list.size();
    }
    public void addJailFreeCard() {
        getOutOfJailCard++;
    }
    public boolean hasJailFreeCard() {
        return getOutOfJailCard > 0;
    }
    public boolean UseJailFreeCard() {
        if (hasJailFreeCard()) {
            getOutOfJailCard--;
            setInJail(false);
            System.out.println(getName() + " used a Get Out of Jail Free card!");
            return true;
        }
        return false;
    }
}