package controller;

import model.PlayerArea;
import MediaClass.PlayNewMedia;
import Transitions.DiceTransition;
import Transitions.DominoTransitions;
import application.Main;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.net.URL;
import java.util.ResourceBundle;

import model.Board;
import model.Chance;
import model.Dice;
import model.Player;
import model.Property;
import model.Square;
import model.SquareType;
import utils.Constants;
import FXML_Controllers.PoPupPage;
import FXML_Controllers.Chance_page;

public class SecondPage implements Initializable {

    // Board
    @FXML private ImageView boardImageView;
    @FXML private ImageView topBar;
    @FXML private ImageView bottomBar;

    // Player info
    @FXML private Label Label_player1, Label_player2, Label_player3, Label_player4;
    @FXML private Label Label_player5, Label_player6, Label_player7, Label_player8;
    @FXML private Label Label_Amount_player1, Label_Amount_player2, Label_Amount_player3, Label_Amount_player4;
    @FXML private Label Label_Amount_player5, Label_Amount_player6, Label_Amount_player7, Label_Amount_player8;
    @FXML private Label Label_player1_Amount, Label_player2_Amount, Label_player3_Amount, Label_player4_Amount;
    @FXML private Label Label_player5_Amount, Label_player6_Amount, Label_player7_Amount, Label_player8_Amount;
    @FXML private Line Line_Player1, Line_Player2, Line_Player3, Line_Player4;
    @FXML private Line Line_Player5, Line_Player6, Line_Player7;
    @FXML private ImageView Player1_symbol, Player2_symbol, Player3_symbol, Player4_symbol;
    @FXML private ImageView Player5_symbol, Player6_symbol, Player7_symbol, Player8_symbol;
    @FXML private TextField textfield_player1, textfield_player2, textfield_player3, textfield_player4;
    @FXML private TextField textfield_player5, textfield_player6, textfield_player7, textfield_player8;

    // Arrows
    @FXML private ImageView Arrow_1, Arrow_2, Arrow_3, Arrow_4;
    @FXML private ImageView Arrow_5, Arrow_6, Arrow_7, Arrow_8;

    // Dominos
    @FXML private ImageView Domino_1, Domino_2, Domino_3, Domino_4;
    @FXML private ImageView Domino_5, Domino_6, Domino_7, Domino_8;

    // Dice
    @FXML private ImageView Dice_var1, Dice_var2;
    @FXML private ImageView Dice1, Dice2, Dice3, Dice4, Dice5, Dice6;

    // Owned areas
    @FXML private ImageView Area_2, Area_4, Area_7, Area_9, Area_10;
    @FXML private ImageView Area_12, Area_14, Area_15, Area_17, Area_19, Area_20;
    @FXML private ImageView Area_22, Area_24, Area_25, Area_27, Area_28, Area_30;
    @FXML private ImageView Area_32, Area_33, Area_35, Area_38, Area_40;
    @FXML private ImageView Area_5, Area_6, Area_8, Area_11, Area_13;
    @FXML private ImageView Area_16, Area_18, Area_21, Area_23, Area_26;
    @FXML private ImageView Area_29, Area_31, Area_34, Area_37, Area_39;

    // House images
    @FXML private ImageView House_1, House_2, House_3, House_4, House_5, House_6, House_7, House_8, House_9, House_10;
    @FXML private ImageView House_11, House_12, House_13, House_14, House_15, House_16, House_17, House_18, House_19, House_20;
    @FXML private ImageView House_21, House_22, House_23, House_24, House_25, House_26, House_27, House_28, House_29, House_30;
    @FXML private ImageView House_31, House_32, House_33, House_34, House_35, House_36, House_37, House_38, House_39, House_40;

    // Hotel images
    @FXML private ImageView Hotel_1, Hotel_2, Hotel_3, Hotel_4, Hotel_5, Hotel_6, Hotel_7, Hotel_8, Hotel_9, Hotel_10;
    @FXML private ImageView Hotel_11, Hotel_12, Hotel_13, Hotel_14, Hotel_15, Hotel_16, Hotel_17, Hotel_18, Hotel_19, Hotel_20;
    @FXML private ImageView Hotel_21, Hotel_22, Hotel_23, Hotel_24, Hotel_25, Hotel_26, Hotel_27, Hotel_28, Hotel_29, Hotel_30;
    @FXML private ImageView Hotel_31, Hotel_32, Hotel_33, Hotel_34, Hotel_35, Hotel_36, Hotel_37, Hotel_38, Hotel_39, Hotel_40;

    //  Data
    public static int numberOfPlayers;
    public static int startMoney;

    public static int player_loop = 0;

    private int diceClickCount = 0;
    private boolean hasRolled = false;
    private final Image[] ownedImages = new Image[9];
    private final FadeTransition[] arrowFades = new FadeTransition[9];
    private DiceTransition diceTransition;
    private PlayNewMedia footstepPlayer;
    private Board board;
    private Chance chance;
    private Dice dice;

    // gamers[1..8] — each PlayerArea stores its own position, money label, etc.
    private final PlayerArea[] gamers = new PlayerArea[9];

    // Board layout
    private final double sX = 1440.0 / 1000.0;
    private final double sY = 970.0 / 750.0;
    private final double boardOffsetX = 255.0;
    private final double boardOffsetY = 100.0;
    private final double dominoW = 53.0/2.0;
    private final double dominoH = 54.0/2.0;



    final double[] boardX = new double[41];
    final double[] boardY = new double[41];

    // Initialize
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numberOfPlayers = FirstPage.numberOfPlayers;
        startMoney = FirstPage.startMoney;
        player_loop = 0; // always start with Player 1

        boardImageView.setFitWidth(1440);
        boardImageView.setFitHeight(970);
        boardImageView.setPreserveRatio(true);
        boardImageView.setLayoutX(240 + (1440 - 1000.0*(970.0/750.0))/2.0);
        boardImageView.setLayoutY(110);

        topBar.setFitWidth(1440);
        topBar.setLayoutX(240);

        bottomBar.setFitWidth(1440);
        bottomBar.setLayoutX(240);
        bottomBar.setLayoutY(1080);

        loadOwnedImages();
        setupBoardPositions();

        if (Main.Static_PopUp_Label == null) {
            Main.Static_PopUp_Label = new Label();
        }

        board = new Board();
        chance = new Chance();
        dice = new Dice();

        // Wire building/house/hotel overlay arrays
        for (int i = 0; i <= 40; i++) {
            Main.images_owned_buildings[i] = getArea(i);
            Main.images_house_buildings[i] = getHouse(i);
            Main.images_hotel_buildings[i] = getHotel(i);
        }
        for (int i = 1; i <= 40; i++) {
            ImageView area = getArea(i);
            if (area != null) { area.setLayoutX(boardX[i]); area.setLayoutY(boardY[i]); }

            ImageView house = getHouse(i);
            if (house != null) {
                house.setFitWidth(40);
                house.setFitHeight(30);
                house.setPreserveRatio(true);
                house.setScaleX(1.0);
                house.setScaleY(1.0);
                house.setLayoutX(boardX[i] + 7);
                house.setLayoutY(boardY[i] + 12);
            }

            ImageView hotel = getHotel(i);
            if (hotel != null) {
                hotel.setFitWidth(40);
                hotel.setFitHeight(30);
                hotel.setPreserveRatio(true);
                hotel.setScaleX(1.0);
                hotel.setScaleY(1.0);
                hotel.setLayoutX(boardX[i] + 7);
                hotel.setLayoutY(boardY[i] + 12);
            }
        }

        // Hide all dominos
        for (int i = 1; i <= 8; i++) getDomino(i).setVisible(false);

        // Create PlayerArea for each active player, place domino at GO (square 1)
        for (int i = 1; i <= numberOfPlayers; i++) {
            Player player = new Player("Player " + i);
            player.setMoney(startMoney);
            gamers[i] = new PlayerArea(player, startMoney, getLabelAmount(i), 41);
            gamers[i].getPlayer().setPosition(1); // start at GO (1-based)
            setMainGamer(i, gamers[i]);

            ImageView domino = getDomino(i);
            domino.setLayoutX(boardX[1]);
            domino.setLayoutY(boardY[1]);
            domino.setVisible(true);
        }

        setupPlayersInfo();
        setupArrows();

        // Initial dice display
        Dice_var1.setImage(getDiceImage((int)(Math.random() * 6) + 1));
        Dice_var2.setImage(getDiceImage((int)(Math.random() * 6) + 1));
        Dice_var1.setLayoutX(960);
        Dice_var1.setLayoutY(500);
        Dice_var2.setLayoutX(820);
        Dice_var2.setLayoutY(500);
    }

    // Load images view
    void loadOwnedImages() {
        for (int i = 1; i <= 8; i++) {
            try {
                ownedImages[i] = new Image(
                        getClass().getResource("/images/Owned_" + i + ".jpg").toExternalForm());
            } catch (Exception e) {
                System.out.println("Could not load Owned_" + i + ".jpg");
            }
        }
    }

    // Set/clear owned
    public void setOwned(int areaNumber, int playerIndex) {
        ImageView area = getArea(areaNumber);
        if (area != null && ownedImages[playerIndex] != null) {
            area.setImage(ownedImages[playerIndex]);
            area.setVisible(true);
        }
    }

    public void clearOwned(int areaNumber) {
        ImageView area = getArea(areaNumber);
        if (area != null) area.setVisible(false);
    }

    // Board positions
    void setupBoardPositions() {
        boardX[ 1]= boardOffsetX+933*sX-dominoW;   boardY[ 1]= boardOffsetY+699*sY-dominoH;
        boardX[ 2]= boardOffsetX+825*sX-dominoW;   boardY[ 2]= boardOffsetY+704*sY-dominoH;
        boardX[ 3]= boardOffsetX+747*sX-dominoW;   boardY[ 3]= boardOffsetY+704*sY-dominoH;
        boardX[ 4]= boardOffsetX+665*sX-dominoW;   boardY[ 4]= boardOffsetY+704*sY-dominoH;
        boardX[ 5]= boardOffsetX+581*sX-dominoW;   boardY[ 5]= boardOffsetY+704*sY-dominoH;
        boardX[ 6]= boardOffsetX+500*sX-dominoW;   boardY[ 6]= boardOffsetY+704*sY-dominoH;
        boardX[ 7]= boardOffsetX+417*sX-dominoW;   boardY[ 7]= boardOffsetY+704*sY-dominoH;
        boardX[ 8]= boardOffsetX+337*sX-dominoW;   boardY[ 8]= boardOffsetY+704*sY-dominoH;
        boardX[ 9]= boardOffsetX+255*sX-dominoW;   boardY[ 9]= boardOffsetY+704*sY-dominoH;
        boardX[10]= boardOffsetX+169*sX-dominoW;   boardY[10]= boardOffsetY+704*sY-dominoH;
        boardX[11]= boardOffsetX+72*sX-dominoW;   boardY[11]= boardOffsetY+699*sY-dominoH;
        boardX[12]= boardOffsetX+59*sX-dominoW;   boardY[12]= boardOffsetY+621*sY-dominoH;
        boardX[13]= boardOffsetX+59*sX-dominoW;   boardY[13]= boardOffsetY+559*sY-dominoH;
        boardX[14]= boardOffsetX+59*sX-dominoW;   boardY[14]= boardOffsetY+498*sY-dominoH;
        boardX[15]= boardOffsetX+59*sX-dominoW;   boardY[15]= boardOffsetY+437*sY-dominoH;
        boardX[16]= boardOffsetX+59*sX-dominoW;   boardY[16]= boardOffsetY+374*sY-dominoH;
        boardX[17]= boardOffsetX+59*sX-dominoW;   boardY[17]= boardOffsetY+311*sY-dominoH;
        boardX[18]= boardOffsetX+59*sX-dominoW;   boardY[18]= boardOffsetY+255*sY-dominoH;
        boardX[19]= boardOffsetX+59*sX-dominoW;   boardY[19]= boardOffsetY+189*sY-dominoH;
        boardX[20]= boardOffsetX+59*sX-dominoW;   boardY[20]= boardOffsetY+126*sY-dominoH;
        boardX[21]= boardOffsetX+71*sX-dominoW;   boardY[21]= boardOffsetY+52*sY-dominoH;
        boardX[22]= boardOffsetX+173*sX-dominoW;   boardY[22]= boardOffsetY+49*sY-dominoH;
        boardX[23]= boardOffsetX+254*sX-dominoW;   boardY[23]= boardOffsetY+49*sY-dominoH;
        boardX[24]= boardOffsetX+337*sX-dominoW;   boardY[24]= boardOffsetY+49*sY-dominoH;
        boardX[25]= boardOffsetX+418*sX-dominoW;   boardY[25]= boardOffsetY+49*sY-dominoH;
        boardX[26]= boardOffsetX+500*sX-dominoW;   boardY[26]= boardOffsetY+49*sY-dominoH;
        boardX[27]= boardOffsetX+581*sX-dominoW;   boardY[27]= boardOffsetY+49*sY-dominoH;
        boardX[28]= boardOffsetX+665*sX-dominoW;   boardY[28]= boardOffsetY+49*sY-dominoH;
        boardX[29]= boardOffsetX+746*sX-dominoW;   boardY[29]= boardOffsetY+49*sY-dominoH;
        boardX[30]= boardOffsetX+827*sX-dominoW;   boardY[30]= boardOffsetY+49*sY-dominoH;
        boardX[31]= boardOffsetX+927*sX-dominoW;   boardY[31]= boardOffsetY+47*sY-dominoH;
        boardX[32]= boardOffsetX+938*sX-dominoW;   boardY[32]= boardOffsetY+130*sY-dominoH;
        boardX[33]= boardOffsetX+938*sX-dominoW;   boardY[33]= boardOffsetY+191*sY-dominoH;
        boardX[34]= boardOffsetX+938*sX-dominoW;   boardY[34]= boardOffsetY+248*sY-dominoH;
        boardX[35]= boardOffsetX+938*sX-dominoW;   boardY[35]= boardOffsetY+316*sY-dominoH;
        boardX[36]= boardOffsetX+938*sX-dominoW;   boardY[36]= boardOffsetY+374*sY-dominoH;
        boardX[37]= boardOffsetX+938*sX-dominoW;   boardY[37]= boardOffsetY+434*sY-dominoH;
        boardX[38]= boardOffsetX+938*sX-dominoW;   boardY[38]= boardOffsetY+498*sY-dominoH;
        boardX[39]= boardOffsetX+938*sX-dominoW;   boardY[39]= boardOffsetY+558*sY-dominoH;
        boardX[40]= boardOffsetX+938*sX-dominoW;   boardY[40]= boardOffsetY+620*sY-dominoH;
    }

    // Arrows
    void setupArrows() {
        for (int i = 1; i <= 8; i++) getArrow(i).setVisible(false);
        setActiveArrow(currentPlayerIndex());
    }

    void setActiveArrow(int playerIndex) {
        // Stop all existing fades and hide all arrows
        for (int i = 1; i <= 8; i++) {
            if (arrowFades[i] != null) { arrowFades[i].stop(); arrowFades[i] = null; }
            getArrow(i).setVisible(false);
        }
        if (playerIndex < 1 || playerIndex > numberOfPlayers) return;
        // Start blinking arrow for current player
        getArrow(playerIndex).setVisible(true);
        FadeTransition fade = new FadeTransition(Duration.millis(500), getArrow(playerIndex));
        fade.setFromValue(1); fade.setToValue(0);
        fade.setCycleCount(FadeTransition.INDEFINITE); fade.setAutoReverse(true);
        fade.play();
        arrowFades[playerIndex] = fade;
    }

    // Player info
    void setupPlayersInfo() {
        for (int i = 1; i <= 8; i++) {
            getLabelName(i).setVisible(false);
            getLabelAmountText(i).setVisible(false);
            getLabelAmount(i).setVisible(false);
            getPlayerSymbol(i).setVisible(false);
            if (i <= 7) getLine(i).setVisible(false);
            getArrow(i).setVisible(false);
        }
        for (int i = 1; i <= numberOfPlayers; i++) {
            getLabelName(i).setVisible(true);
            getLabelAmountText(i).setVisible(true);
            getLabelAmount(i).setVisible(true);
            getLabelAmount(i).setText(startMoney + " $");
            getPlayerSymbol(i).setVisible(true);
            if (i <= 7) getLine(i).setVisible(true);
        }
    }

    // Current player
    int currentPlayerIndex() {
        return player_loop + 1;
    }

    // Dice
    @FXML
    private void Click_Dice_1(MouseEvent event) { rollDice(); }

    @FXML
    private void Click_Dice_2(MouseEvent event) { rollDice(); }

    void rollDice() {
        if (hasRolled) return; // block extra clicks mid-turn
        hasRolled = true;

        diceClickCount = (diceClickCount % 10) + 1;

        int result1 = (int)(Math.random() * 6) + 1;
        int result2 = (int)(Math.random() * 6) + 1;
        int total = result1 + result2;

        // Run dice animation
        diceTransition = new DiceTransition(Dice_var1, Dice_var2, diceClickCount);
        diceTransition.run();

        // After animation (3s), show result and move current player
        PauseTransition pause = new PauseTransition(Duration.millis(3000));
        pause.setOnFinished(e -> {
            Dice_var1.setImage(getDiceImage(result1));
            Dice_var2.setImage(getDiceImage(result2));
            movePlayer(currentPlayerIndex(), total);
        });
        pause.play();
    }

    // Move player
    void movePlayer(int playerIndex, int steps) {
        PlayerArea area = getPlayerArea(playerIndex);
        if (area == null) return;


        int currentPos = area.getPlayer().getPosition();
        if (currentPos < 1 || currentPos > 40) currentPos = 1;

        int calc = currentPos + steps;
        boolean passedGo = calc > 40;
        if (passedGo) {
            calc = calc % 40;
            if (calc == 0) calc = 1;
        }
        final int newPos = calc;

        // Collect $200 for passing GO
        if (passedGo) {
            area.getPlayer().setMoney(area.getPlayer().getMoney() + 200);
            area.getLabel_amount().setText(area.getPlayer().getMoney() + " $");
            flashLabel(area.getLabel_amount());
        }

        ImageView domino = getDomino(playerIndex);
        // Offset nhỏ khi nhiều player cùng ô để không chồng lên nhau
        final double offsetX = ((playerIndex - 1) % 2) * 15.0;
        final double offsetY = ((playerIndex - 1) / 2) * 15.0;
        domino.setLayoutX(boardX[currentPos] + offsetX);
        domino.setLayoutY(boardY[currentPos] + offsetY);
        domino.setVisible(true);

        // Footstep sound
        if (footstepPlayer != null) footstepPlayer.dispose();
        footstepPlayer = new PlayNewMedia("/sounds/ollies-footsteps-looped.mp3");
        footstepPlayer.run();

        // Animate domino walking across the board


        new DominoTransitions(domino, currentPos, steps, boardX, boardY, offsetX, offsetY).run();

        // After animation completes, snap and handle the landed square
        long totalDuration = 400L * steps + 800;
        PauseTransition pause = new PauseTransition(Duration.millis(totalDuration));
        pause.setOnFinished(e -> {
            try {
                area.getPlayer().setPosition(newPos); // lưu 1-based
                domino.setLayoutX(boardX[newPos] + offsetX);
                domino.setLayoutY(boardY[newPos] + offsetY);
                System.out.println("[DEBUG] Domino layoutX=" + (boardX[newPos]+offsetX) + " layoutY=" + (boardY[newPos]+offsetY) + " for square " + newPos);
                if (footstepPlayer != null) {
                    footstepPlayer.stop(); footstepPlayer.dispose(); footstepPlayer = null;
                }
                // Use Platform.runLater so showAndWait popup is NOT called inside animation callback
                Platform.runLater(() -> handleSquareEvent(playerIndex, newPos));
            } catch (Exception ex) {
                System.out.println("[ERROR] movePlayer callback crashed: " + ex.getMessage());
                ex.printStackTrace();
                Platform.runLater(() -> nextTurn());
            }
        });
        pause.play();
    }

    // Next turn
    // Advances player_loop and resets hasRolled so next player can roll
    void nextTurn() {
        player_loop = (player_loop + 1) % numberOfPlayers;
        hasRolled = false;
        setActiveArrow(currentPlayerIndex());
        System.out.println("Turn: Player " + currentPlayerIndex());
    }

    // Handle square event
    void handleSquareEvent(int playerIndex, int position) {
        Main.player_turn = playerIndex;
        Main.static_player_place = position;
        try {
            Square square = board.getSquare(position - 1);
            System.out.println("Player " + playerIndex + " landed on " + position + " (" + square.getType() + ")");
            switch (square.getType()) {
                case PROPERTY:      handlePropertyLanding(playerIndex, square.getProperty()); break;
                case CHANCE:        handleChance(playerIndex, position); break;
                case TAX:           handleTax(playerIndex, position); break;
                case JAIL:          handleJail(playerIndex); break;
                case GO_JAIL:       handleGoToJail(playerIndex); break;
                case FREE_PARKING:  handleFreeParking(playerIndex); break;
                default:            nextTurn(); break;
            }
        } catch (Exception ex) {
            System.out.println("[ERROR] handleSquareEvent crashed at pos " + position + ": " + ex.getMessage());
            ex.printStackTrace();
            nextTurn();
        }
    }

    // Property
    void handlePropertyLanding(int playerIndex, Property property) {
        PlayerArea currentArea = getPlayerArea(playerIndex);
        Player currentPlayerObj = currentArea.getPlayer();

        if (!property.isOwned()) {
            // Unowned — show buy popup
            Main.currentProperty = property;
            Main.Static_PopUp_Label.setText(
                    property.getName() + "\nPrice: $" + property.getPrice());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/PoPupPage.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } catch (IOException e) { e.printStackTrace(); }
            nextTurn();

        } else if (!property.getOwner().equals(currentPlayerObj)) {
            // Owned by another player — pay rent silently
            int rent = property.getRent();
            currentArea.getPlayer().setMoney(currentArea.getPlayer().getMoney() - rent);
            currentArea.getLabel_amount().setText(currentArea.getPlayer().getMoney() + " $");
            flashLabel(currentArea.getLabel_amount());
            PlayerArea ownerArea = findPlayerAreaByPlayer(property.getOwner());
            if (ownerArea != null) {
                ownerArea.getPlayer().setMoney(ownerArea.getPlayer().getMoney() + rent);
                ownerArea.getLabel_amount().setText(ownerArea.getPlayer().getMoney() + " $");
            }
            nextTurn();

        } else {
            // Already owned by this player — skip building, just next turn
            nextTurn();
        }
    }

    // Chance
    void handleChance(int playerIndex, int originalPosition) {
        PlayerArea area = getPlayerArea(playerIndex);
        chance.drawCard();
        Main.Static_PopUp_Label.setText(chance.getCardDescription());
        Main.static_player_place = originalPosition;

        chance.applyCardEffect(area.getPlayer(), board, dice, getActivePlayers());

        area.getLabel_amount().setText(area.getPlayer().getMoney() + " $");
        flashLabel(area.getLabel_amount());

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/Chance_page.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) { e.printStackTrace(); }

        int newPos0 = area.getPlayer().getPosition();
        int newPosition = newPos0 + 1;
        if (newPosition == 41) newPosition = 1;

        if (newPosition != originalPosition && newPosition >= 1 && newPosition <= 40) {
            area.getPlayer().setPosition(newPosition);

            int stepsNeeded = (newPosition - originalPosition + 40) % 40;
            if (stepsNeeded == 0) stepsNeeded = 40;
            movePlayer(playerIndex, stepsNeeded);
        } else {
            nextTurn();
        }
    }

    // Tax
    void handleTax(int playerIndex, int position) {
        int taxAmount = (position == 5) ? Constants.INCOME_TAX : Constants.LUXURY_TAX;
        PlayerArea area = getPlayerArea(playerIndex);
        area.getPlayer().setMoney(area.getPlayer().getMoney() - taxAmount);
        area.getLabel_amount().setText(area.getPlayer().getMoney() + " $");
        flashLabel(area.getLabel_amount());
        System.out.println("Player " + playerIndex + " paid tax $" + taxAmount);
        nextTurn();
    }

    // Jail
    void handleJail(int playerIndex) {
        getPlayerArea(playerIndex).getPlayer().setInJail(true);
        System.out.println("Player " + playerIndex + " is visiting Jail.");
        nextTurn();
    }

    // Go to jail
    void handleGoToJail(int playerIndex) {
        PlayerArea area = getPlayerArea(playerIndex);
        area.getPlayer().setPosition(10); // Jail = boardIndex 10 (1-based)
        area.getPlayer().setInJail(true);
        getDomino(playerIndex).setLayoutX(boardX[10]);
        getDomino(playerIndex).setLayoutY(boardY[10]);
        System.out.println("Player " + playerIndex + " sent to Jail.");
        nextTurn();
    }

    // Free parking
    void handleFreeParking(int playerIndex) {
        PlayerArea area = getPlayerArea(playerIndex);
        area.getPlayer().setMoney(area.getPlayer().getMoney() + Main.freeParkingPool);
        area.getLabel_amount().setText(area.getPlayer().getMoney() + " $");
        flashLabel(area.getLabel_amount());
        System.out.println("Player " + playerIndex + " collected Free Parking $" + Main.freeParkingPool);
        Main.freeParkingPool = 0;
        nextTurn();
    }

    // Flash label
    private void flashLabel(Label label) {
        FadeTransition ft = new FadeTransition(Duration.millis(100), label);
        ft.setFromValue(1); ft.setToValue(0);
        ft.setCycleCount(12); ft.setAutoReverse(true);
        ft.play();
    }

    // Player name edit
    @FXML private void Click_On_Player1(MouseEvent e) { editPlayerName(1); }
    @FXML private void Click_On_Player2(MouseEvent e) { editPlayerName(2); }
    @FXML private void Click_On_Player3(MouseEvent e) { editPlayerName(3); }
    @FXML private void Click_On_Player4(MouseEvent e) { editPlayerName(4); }
    @FXML private void Click_On_Player5(MouseEvent e) { editPlayerName(5); }
    @FXML private void Click_On_Player6(MouseEvent e) { editPlayerName(6); }
    @FXML private void Click_On_Player7(MouseEvent e) { editPlayerName(7); }
    @FXML private void Click_On_Player8(MouseEvent e) { editPlayerName(8); }

    void editPlayerName(int i) {
        saveAllTextfields();
        getLabelName(i).setVisible(false);
        getTextField(i).setVisible(true);
        getTextField(i).setText(getLabelName(i).getText());
    }

    @FXML
    private void Click_On_Arena(MouseEvent event) { saveAllTextfields(); }

    void saveAllTextfields() {
        for (int i = 1; i <= 8; i++) {
            if (getTextField(i).isVisible()) {
                getLabelName(i).setText(getTextField(i).getText());
                getLabelName(i).setVisible(true);
                getTextField(i).setVisible(false);
            }
        }
    }

    // Player symbol
    @FXML private void Click_ON_Symbol_Player1(MouseEvent e) { showDomino(1); }
    @FXML private void Click_ON_Symbol_Player2(MouseEvent e) { showDomino(2); }
    @FXML private void Click_ON_Symbol_Player3(MouseEvent e) { showDomino(3); }
    @FXML private void Click_ON_Symbol_Player4(MouseEvent e) { showDomino(4); }
    @FXML private void Click_ON_Symbol_Player5(MouseEvent e) { showDomino(5); }
    @FXML private void Click_ON_Symbol_Player6(MouseEvent e) { showDomino(6); }
    @FXML private void Click_ON_Symbol_Player7(MouseEvent e) { showDomino(7); }
    @FXML private void Click_ON_Symbol_Player8(MouseEvent e) { showDomino(8); }

    void showDomino(int i) {
        ImageView domino = getDomino(i);
        if (domino.isVisible()) {
            FadeTransition fade = new FadeTransition(Duration.millis(50), domino);
            fade.setFromValue(1); fade.setToValue(0);
            fade.setCycleCount(8); fade.setAutoReverse(true); fade.play();
        } else {
            domino.setVisible(true);
            FadeTransition fade = new FadeTransition(Duration.millis(70), domino);
            fade.setFromValue(1); fade.setToValue(0);
            fade.setCycleCount(8); fade.setAutoReverse(true); fade.play();
            PauseTransition pause = new PauseTransition(Duration.millis(70 * 8));
            pause.setOnFinished(e -> domino.setVisible(false));
            pause.play();
        }
    }

    // Back
    @FXML
    private void Click_On_Back(MouseEvent event) throws IOException {
        if (footstepPlayer != null) {
            footstepPlayer.stop(); footstepPlayer.dispose(); footstepPlayer = null;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/FirstPage.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, 600, 700);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setX((screen.getWidth() - 600) / 2);
        stage.setY((screen.getHeight() - 700) / 2);
        stage.show();
        ((Stage) ((ImageView) event.getSource()).getScene().getWindow()).close();
    }

    // Quit
    @FXML
    private void Click_On_Quit(MouseEvent event) {
        ((Stage) ((ImageView) event.getSource()).getScene().getWindow()).close();
    }

    // Internal helpers

    private void setMainGamer(int i, PlayerArea area) {
        switch (i) {
            case 1: Main.Gamer1 = area; break; case 2: Main.Gamer2 = area; break;
            case 3: Main.Gamer3 = area; break; case 4: Main.Gamer4 = area; break;
            case 5: Main.Gamer5 = area; break; case 6: Main.Gamer6 = area; break;
            case 7: Main.Gamer7 = area; break; case 8: Main.Gamer8 = area; break;
        }
    }

    private PlayerArea getPlayerArea(int playerIndex) {
        if (playerIndex >= 1 && playerIndex <= 8) return gamers[playerIndex];
        return null;
    }

    public List<Player> getActivePlayers() {
        List<Player> active = new ArrayList<>();
        for (int i = 1; i <= numberOfPlayers; i++) {
            if (gamers[i] != null) active.add(gamers[i].getPlayer());
        }
        return active;
    }

    private PlayerArea findPlayerAreaByPlayer(Player player) {
        for (int i = 1; i <= numberOfPlayers; i++) {
            if (gamers[i] != null && gamers[i].getPlayer().equals(player)) return gamers[i];
        }
        return null;
    }

    // UI element getters

    Image getDiceImage(int number) {
        switch (number) {
            case 1: return Dice1.getImage(); case 2: return Dice2.getImage();
            case 3: return Dice3.getImage(); case 4: return Dice4.getImage();
            case 5: return Dice5.getImage(); case 6: return Dice6.getImage();
            default: return Dice1.getImage();
        }
    }

    Label getLabelName(int i) {
        switch (i) {
            case 1: return Label_player1; case 2: return Label_player2;
            case 3: return Label_player3; case 4: return Label_player4;
            case 5: return Label_player5; case 6: return Label_player6;
            case 7: return Label_player7; case 8: return Label_player8;
            default: return Label_player1;
        }
    }

    Label getLabelAmountText(int i) {
        switch (i) {
            case 1: return Label_Amount_player1; case 2: return Label_Amount_player2;
            case 3: return Label_Amount_player3; case 4: return Label_Amount_player4;
            case 5: return Label_Amount_player5; case 6: return Label_Amount_player6;
            case 7: return Label_Amount_player7; case 8: return Label_Amount_player8;
            default: return Label_Amount_player1;
        }
    }

    Label getLabelAmount(int i) {
        switch (i) {
            case 1: return Label_player1_Amount; case 2: return Label_player2_Amount;
            case 3: return Label_player3_Amount; case 4: return Label_player4_Amount;
            case 5: return Label_player5_Amount; case 6: return Label_player6_Amount;
            case 7: return Label_player7_Amount; case 8: return Label_player8_Amount;
            default: return Label_player1_Amount;
        }
    }

    ImageView getPlayerSymbol(int i) {
        switch (i) {
            case 1: return Player1_symbol; case 2: return Player2_symbol;
            case 3: return Player3_symbol; case 4: return Player4_symbol;
            case 5: return Player5_symbol; case 6: return Player6_symbol;
            case 7: return Player7_symbol; case 8: return Player8_symbol;
            default: return Player1_symbol;
        }
    }

    ImageView getDomino(int i) {
        switch (i) {
            case 1: return Domino_1; case 2: return Domino_2;
            case 3: return Domino_3; case 4: return Domino_4;
            case 5: return Domino_5; case 6: return Domino_6;
            case 7: return Domino_7; case 8: return Domino_8;
            default: return Domino_1;
        }
    }

    ImageView getArrow(int i) {
        switch (i) {
            case 1: return Arrow_1; case 2: return Arrow_2;
            case 3: return Arrow_3; case 4: return Arrow_4;
            case 5: return Arrow_5; case 6: return Arrow_6;
            case 7: return Arrow_7; case 8: return Arrow_8;
            default: return Arrow_1;
        }
    }

    Line getLine(int i) {
        switch (i) {
            case 1: return Line_Player1; case 2: return Line_Player2;
            case 3: return Line_Player3; case 4: return Line_Player4;
            case 5: return Line_Player5; case 6: return Line_Player6;
            case 7: return Line_Player7; default: return Line_Player1;
        }
    }

    TextField getTextField(int i) {
        switch (i) {
            case 1: return textfield_player1; case 2: return textfield_player2;
            case 3: return textfield_player3; case 4: return textfield_player4;
            case 5: return textfield_player5; case 6: return textfield_player6;
            case 7: return textfield_player7; case 8: return textfield_player8;
            default: return textfield_player1;
        }
    }

    ImageView getArea(int n) {
        switch (n) {
            case 2: return Area_2; case 4: return Area_4; case 5: return Area_5;
            case 6: return Area_6; case 7: return Area_7; case 8: return Area_8;
            case 9: return Area_9; case 10: return Area_10; case 11: return Area_11;
            case 12: return Area_12; case 13: return Area_13; case 14: return Area_14;
            case 15: return Area_15; case 16: return Area_16; case 17: return Area_17;
            case 18: return Area_18; case 19: return Area_19; case 20: return Area_20;
            case 21: return Area_21; case 22: return Area_22; case 23: return Area_23;
            case 24: return Area_24; case 25: return Area_25; case 26: return Area_26;
            case 27: return Area_27; case 28: return Area_28; case 29: return Area_29;
            case 30: return Area_30; case 31: return Area_31; case 32: return Area_32;
            case 33: return Area_33; case 34: return Area_34; case 35: return Area_35;
            case 37: return Area_37; case 38: return Area_38; case 39: return Area_39;
            case 40: return Area_40; default: return null;
        }
    }

    ImageView getHouse(int p) {
        switch (p) {
            case 1: return House_1; case 2: return House_2; case 3: return House_3;
            case 4: return House_4; case 5: return House_5; case 6: return House_6;
            case 7: return House_7; case 8: return House_8; case 9: return House_9;
            case 10: return House_10; case 11: return House_11; case 12: return House_12;
            case 13: return House_13; case 14: return House_14; case 15: return House_15;
            case 16: return House_16; case 17: return House_17; case 18: return House_18;
            case 19: return House_19; case 20: return House_20; case 21: return House_21;
            case 22: return House_22; case 23: return House_23; case 24: return House_24;
            case 25: return House_25; case 26: return House_26; case 27: return House_27;
            case 28: return House_28; case 29: return House_29; case 30: return House_30;
            case 31: return House_31; case 32: return House_32; case 33: return House_33;
            case 34: return House_34; case 35: return House_35; case 36: return House_36;
            case 37: return House_37; case 38: return House_38; case 39: return House_39;
            case 40: return House_40; default: return null;
        }
    }

    ImageView getHotel(int p) {
        switch (p) {
            case 1: return Hotel_1; case 2: return Hotel_2; case 3: return Hotel_3;
            case 4: return Hotel_4; case 5: return Hotel_5; case 6: return Hotel_6;
            case 7: return Hotel_7; case 8: return Hotel_8; case 9: return Hotel_9;
            case 10: return Hotel_10; case 11: return Hotel_11; case 12: return Hotel_12;
            case 13: return Hotel_13; case 14: return Hotel_14; case 15: return Hotel_15;
            case 16: return Hotel_16; case 17: return Hotel_17; case 18: return Hotel_18;
            case 19: return Hotel_19; case 20: return Hotel_20; case 21: return Hotel_21;
            case 22: return Hotel_22; case 23: return Hotel_23; case 24: return Hotel_24;
            case 25: return Hotel_25; case 26: return Hotel_26; case 27: return Hotel_27;
            case 28: return Hotel_28; case 29: return Hotel_29; case 30: return Hotel_30;
            case 31: return Hotel_31; case 32: return Hotel_32; case 33: return Hotel_33;
            case 34: return Hotel_34; case 35: return Hotel_35; case 36: return Hotel_36;
            case 37: return Hotel_37; case 38: return Hotel_38; case 39: return Hotel_39;
            case 40: return Hotel_40; default: return null;
        }
    }
}