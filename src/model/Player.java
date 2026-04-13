package model;
import java.util.List;
import java.util.ArrayList;

public class Player {
    private String name;
    private int money;
    private boolean inJail;
    private int currentPosition;
    private List<Property> list;   

    public Player(String name){
        this.name=name;
        this.money=1500;
        this.currentPosition=0;
        this.inJail=false;
        this.list=new ArrayList<>();
    }

    public void move(int steps){
        currentPosition = (currentPosition + steps)%40;
    }
    public void addMoney(int money){
        this.money+=money;
    }
    public void deductMoney(int money){
        this.money-=money;
    }
    public boolean isBankrupt(){
        return money<0;
    }
    public boolean isInJail(){
        return inJail;
    }
    public void setInJail(boolean inJail){
        this.inJail=inJail;
    }
    public void addProperty(Property property){
        list.add(property);
    }
    public int getMoney(){
        return money;
    }
    public String getName(){
        return name;
    }
    public int getPosition(){
        return currentPosition;
    }
    public void setPosition(int position){
        this.currentPosition=position;
    }
    public List<Property> getProperties(){
        return list;
    }
    public int getPropertiesCount(){
        return list.size();
    }
}
