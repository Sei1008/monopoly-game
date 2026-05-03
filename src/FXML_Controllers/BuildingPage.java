package FXML_Controllers;

import application.Main;
import MediaClass.PlayNewMedia;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import model.PlayerArea;
import utils.Constants;

public class BuildingPage extends Main implements Initializable {

    @FXML private Label PopUp_Label;
    @FXML private Button Button_House;
    @FXML private Button Button_Hotel;
    @FXML private Button Button_Cancel;

    private PlayerArea[] Gamer = new PlayerArea[9];

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        PopUp_Label.setText(Main.Static_PopUp_Label.getText());
        Gamer[1] = Main.Gamer1; Gamer[2] = Main.Gamer2;
        Gamer[3] = Main.Gamer3; Gamer[4] = Main.Gamer4;
        Gamer[5] = Main.Gamer5; Gamer[6] = Main.Gamer6;
        Gamer[7] = Main.Gamer7; Gamer[8] = Main.Gamer8;

        if (Main.currentProperty.getHouses() < 4 && Main.currentProperty.getHotels() == 0) {
            Button_House.setVisible(true);
            Button_Hotel.setVisible(false);
        } else if (Main.currentProperty.getHouses() == 4 && Main.currentProperty.getHotels() == 0) {
            Button_House.setVisible(false);
            Button_Hotel.setVisible(true);
        } else if (Main.currentProperty.getHotels() == 1) {
            Button_House.setVisible(false);
            Button_Hotel.setVisible(false);
        }
    }

    @FXML
    private void Click_On_House(MouseEvent event) {
        int houseCost = Constants.HOUSE_COST;
        int newAmount = Gamer[Main.player_turn].getPlayer().getMoney() - houseCost;
        Gamer[Main.player_turn].getPlayer().setMoney(newAmount);

        Main.currentProperty.addHouse();

        if (Main.images_owned_buildings[Main.static_player_place] != null)
            Main.images_owned_buildings[Main.static_player_place].setVisible(false);
        if (Main.images_house_buildings[Main.static_player_place] != null) {
            Main.images_house_buildings[Main.static_player_place].setImage(
                    new Image("/images/House_" + Main.player_turn + ".png"));
            Main.images_house_buildings[Main.static_player_place].setVisible(true);
        }

        Gamer[Main.player_turn].getLabel_amount().setText(newAmount + " $");
        FadeTransition ft = new FadeTransition(Duration.millis(100),
                Gamer[Main.player_turn].getLabel_amount());
        ft.setFromValue(1); ft.setToValue(0);
        ft.setCycleCount(12); ft.setAutoReverse(true); ft.play();

        new PlayNewMedia("/sound/Buy.mp3").run();
        closeWindow(event);
    }

    @FXML
    private void Click_On_Hotel(MouseEvent event) {
        int hotelCost = Constants.HOTEL_COST;
        int newAmount = Gamer[Main.player_turn].getPlayer().getMoney() - hotelCost;
        Gamer[Main.player_turn].getPlayer().setMoney(newAmount);

        Main.currentProperty.addHotel();

        if (Main.images_house_buildings[Main.static_player_place] != null)
            Main.images_house_buildings[Main.static_player_place].setVisible(false);
        if (Main.images_hotel_buildings[Main.static_player_place] != null) {
            Main.images_hotel_buildings[Main.static_player_place].setImage(
                    new Image("/images/Hotel_" + Main.player_turn + ".png"));
            Main.images_hotel_buildings[Main.static_player_place].setVisible(true);
        }

        Gamer[Main.player_turn].getLabel_amount().setText(newAmount + " $");
        FadeTransition ft = new FadeTransition(Duration.millis(100),
                Gamer[Main.player_turn].getLabel_amount());
        ft.setFromValue(1); ft.setToValue(0);
        ft.setCycleCount(12); ft.setAutoReverse(true); ft.play();

        new PlayNewMedia("/sound/Buy.mp3").run();
        closeWindow(event);
    }

    @FXML
    private void Click_On_Cancel(MouseEvent event) {
        closeWindow(event);
    }

    private void closeWindow(MouseEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
}