package model;

public class Board {
    private Square[] squares=new Square[40];
    
    public Board(){
        initializeBoard();
    }

    public void initializeBoard(){
        squares[0] = new Square("GO",SquareType.GO);
        squares[1] = new Square("Mediterranean Ave",SquareType.PROPERTY,new Property("Mediterranean Ave", 60, 2, "brown"));
        squares[2] = new Square("Community Chest",SquareType.COMMUNITY_CHEST);
        squares[3] = new Square("Baltic Ave",SquareType.PROPERTY,new Property("Baltic Ave", 60, 4, "brown"));
        squares[4] = new Square("Income Tax",SquareType.TAX);
        squares[5] = new Square("Reading Railroad",SquareType.PROPERTY,new Property("Reading Railroad", 200, 25, "railroad"));
        squares[6] = new Square("Oriental Ave",SquareType.PROPERTY,new Property("Oriental Ave", 100, 6, "light blue"));
        squares[7] = new Square("Chance",SquareType.CHANCE);
        squares[8] = new Square("Vermont Ave",SquareType.PROPERTY,new Property("Vermont Ave", 100, 6, "light blue"));
        squares[9] = new Square("Connecticut Ave",SquareType.PROPERTY,new Property("Connecticut Ave",120,8,"light blue"));
        squares[10] = new Square("Jail",SquareType.JAIL);
        squares[11] = new Square("St. Charles Pl", SquareType.PROPERTY, new Property("St. Charles Pl", 140, 10, "pink"));
        squares[12] = new Square("Electric Co", SquareType.PROPERTY, new Property("Electric Co", 150, 4, "utility")); 
        squares[13] = new Square("States Ave", SquareType.PROPERTY, new Property("States Ave", 140, 10, "pink"));
        squares[14] = new Square("Virginia Ave", SquareType.PROPERTY, new Property("Virginia Ave", 160, 12, "pink"));
        squares[15] = new Square("Pennsylvania RR", SquareType.PROPERTY, new Property("Pennsylvania RR", 200, 25, "railroad"));
        squares[16] = new Square("St. James Pl", SquareType.PROPERTY, new Property("St. James Pl", 180, 14, "orange"));
        squares[17] = new Square("Community Chest", SquareType.COMMUNITY_CHEST);
        squares[18] = new Square("Tennessee Ave", SquareType.PROPERTY, new Property("Tennessee Ave", 180, 14, "orange"));
        squares[19] = new Square("New York Ave", SquareType.PROPERTY, new Property("New York Ave", 200, 16, "orange"));
        squares[20] = new Square("Free Parking", SquareType.FREE_PARKING); 
        squares[21] = new Square("Kentucky Ave", SquareType.PROPERTY, new Property("Kentucky Ave", 220, 18, "red"));
        squares[22] = new Square("Chance", SquareType.CHANCE);
        squares[23] = new Square("Indiana Ave", SquareType.PROPERTY, new Property("Indiana Ave", 220, 18, "red"));
        squares[24] = new Square("Illinois Ave", SquareType.PROPERTY, new Property("Illinois Ave", 240, 20, "red"));
        squares[25] = new Square("B&O Railroad", SquareType.PROPERTY, new Property("B&O Railroad", 200, 25, "railroad"));
        squares[26] = new Square("Atlantic Ave", SquareType.PROPERTY, new Property("Atlantic Ave", 260, 22, "yellow"));
        squares[27] = new Square("Ventnor Ave", SquareType.PROPERTY, new Property("Ventnor Ave", 260, 22, "yellow"));
        squares[28] = new Square("Water Works", SquareType.PROPERTY, new Property("Water Works", 150, 4, "utility")); 
        squares[29] = new Square("Marvin Gardens", SquareType.PROPERTY, new Property("Marvin Gardens", 280, 24, "yellow"));
        squares[30] = new Square("Go To Jail", SquareType.GO_JAIL); 
        squares[31] = new Square("Pacific Ave", SquareType.PROPERTY, new Property("Pacific Ave", 300, 26, "green"));
        squares[32] = new Square("North Carolina Ave", SquareType.PROPERTY, new Property("North Carolina Ave", 300, 26, "green"));
        squares[33] = new Square("Community Chest", SquareType.COMMUNITY_CHEST);
        squares[34] = new Square("Pennsylvania Ave", SquareType.PROPERTY, new Property("Pennsylvania Ave", 320, 28, "green"));
        squares[35] = new Square("Short Line RR", SquareType.PROPERTY, new Property("Short Line RR", 200, 25, "railroad"));
        squares[36] = new Square("Chance", SquareType.CHANCE);
        squares[37] = new Square("Park Place", SquareType.PROPERTY, new Property("Park Place", 350, 35, "blue"));
        squares[38] = new Square("Luxury Tax", SquareType.TAX); 
        squares[39] = new Square("Boardwalk", SquareType.PROPERTY, new Property("Boardwalk", 400, 50, "blue"));
    }

    public Square getSquare(int position){
        return squares[position%40];
    }
}
