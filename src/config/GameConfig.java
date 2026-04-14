package config;

public class GameConfig {
   int startingmoney;
   int maxPlayers;
   int boardSize; 
   public void loadConfig() {
    this.startingmoney = 1500;
    this.maxPlayers = 4;
    this.boardSize = 40;
}
}
