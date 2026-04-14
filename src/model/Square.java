package model;

public class Square {
    private String name;
    private SquareType type;
    private Property property;

    public Square(String name, SquareType type){
        this.name=name;
        this.type=type;
        this.property=null;
    }
    public Square(String name, SquareType type, Property property){
        this.name=name;
        this.type=type;
        this.property=property;
    }
    public SquareType getType(){return type;}
    public String getName(){return name;}
    public Property getProperty(){return property;}
}
