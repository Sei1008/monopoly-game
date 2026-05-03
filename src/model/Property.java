package model;
import config.Constants;
public class Property {
    private String name;
    private int price;
    private int rent;
    private Player owner;
    private String color;
    private int houses;     
    private int hotels;      
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
    public int getHouses() {
        return houses;
    }

    public int getHotels() {
        return hotels;
    }

    public void addHouse() {
        if (houses < 4) {
            houses++;
        }
    }

    public void addHotel() {
        if (houses == 4 && hotels < 1) {
            houses = 0;  // (4 houses = 1 hotel)
            hotels = 1;
        }
    }

    public int getRepairCost() {
        return (houses * Constants.HOUSE_REPAIR_COST) + (hotels * Constants.HOTEL_REPAIR_COST);
    }

    public static int getHouseRepairCost() {
        return Constants.HOUSE_REPAIR_COST;
    }

    public static int getHotelRepairCost() {
        return Constants.HOTEL_REPAIR_COST;
    }
}
