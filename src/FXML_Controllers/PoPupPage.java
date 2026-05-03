package FXML_Controllers;

import application.Main;
import MediaClass.PlayNewMedia;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import model.PlayerArea;

public class PoPupPage extends Main implements Initializable {

    @FXML private Label PopUp_Label;
    @FXML private Button Button_Yes;
    @FXML private Button Button_No;

    private PlayerArea[] Gamer = new PlayerArea[9];

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        PopUp_Label.setText(Main.Static_PopUp_Label.getText());
        Gamer[1] = Main.Gamer1; Gamer[2] = Main.Gamer2;
        Gamer[3] = Main.Gamer3; Gamer[4] = Main.Gamer4;
        Gamer[5] = Main.Gamer5; Gamer[6] = Main.Gamer6;
        Gamer[7] = Main.Gamer7; Gamer[8] = Main.Gamer8;
    }

    @FXML
    private void Click_On_Yes(MouseEvent event) {
        int price = Main.currentProperty.getPrice();
        int current = Gamer[Main.player_turn].getPlayer().getMoney();
        int newAmount = current - price;

        // Deduct price
        Gamer[Main.player_turn].getPlayer().setMoney(newAmount);

        // Set owner
        Main.currentProperty.setOwner(Gamer[Main.player_turn].getPlayer());

        // Add property to player's list
        Gamer[Main.player_turn].getPlayer().addProperty(Main.currentProperty);

        // Mark owned
        Gamer[Main.player_turn].getPlayers_owned_places()[Main.static_player_place] = true;

        // Hiện house image khi mua property
        System.out.println("[BUY] static_player_place=" + Main.static_player_place + " player_turn=" + Main.player_turn);
        if (Main.images_house_buildings[Main.static_player_place] != null) {
            Main.images_house_buildings[Main.static_player_place].setImage(
                    new Image("/images/House_" + Main.player_turn + ".png"));
            Main.images_house_buildings[Main.static_player_place].setVisible(true);
        }
        // Ẩn owned image
        if (Main.images_owned_buildings[Main.static_player_place] != null)
            Main.images_owned_buildings[Main.static_player_place].setVisible(false);

        // Update label
        Gamer[Main.player_turn].getLabel_amount().setText(newAmount + " $");
        FadeTransition ft = new FadeTransition(javafx.util.Duration.millis(100),
                Gamer[Main.player_turn].getLabel_amount());
        ft.setFromValue(1); ft.setToValue(0);
        ft.setCycleCount(12); ft.setAutoReverse(true); ft.play();

        new PlayNewMedia("/sound/Buy.mp3").run();
        closeWindow(event);
    }

    @FXML
    private void Click_ON_NO(MouseEvent event) {
        closeWindow(event);
    }

    private void closeWindow(MouseEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
}