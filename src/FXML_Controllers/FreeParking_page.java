package FXML_Controllers;

import application.Main;
import MediaClass.PlayNewMedia;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import model.PlayerArea;

public class FreeParking_page extends Main implements Initializable {

    @FXML private Label PopUp_Label;
    @FXML private Button Button_OK;

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
    private void Click_On_OK(MouseEvent event) {
        int newAmount = Gamer[Main.player_turn].getPlayer().getMoney() + Main.freeParkingPool;
        Gamer[Main.player_turn].getPlayer().setMoney(newAmount);
        Gamer[Main.player_turn].getLabel_amount().setText(newAmount + " $");

        FadeTransition ft = new FadeTransition(Duration.millis(100),
                Gamer[Main.player_turn].getLabel_amount());
        ft.setFromValue(1); ft.setToValue(0);
        ft.setCycleCount(12); ft.setAutoReverse(true); ft.play();

        Main.freeParkingPool = 0;
        new PlayNewMedia("/sound/Free Parking.mp3").run();
        closeWindow(event);
    }

    private void closeWindow(MouseEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
}