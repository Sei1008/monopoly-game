package model;

public class Property {
    private String name;
    private int price;
    private int rent;
    private Player owner;
    private String color;

    public Property(String name,int price,int rent, String color){
        this.name=name;
        this.price=price;
        this.rent=rent;
        this.color=color;
    }
    public void setOwner(Player owner){
        this.owner=owner;
    }
    public Player getOwner(){
        return owner;
    }
    public boolean isOwned(){
        return owner != null;
    }
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    public String getColor(){
        return color;
    }
    public int getRent(){
        return (owner != null) ? rent : 0;
    }
}
