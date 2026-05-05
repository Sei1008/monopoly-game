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
import config.Constants;

public class Jail_page extends Main implements Initializable {

    @FXML private Label PopUp_Label;
    @FXML private Button Button_Yes;
    @FXML private Button Button_No;
    @FXML private Button Button_OK;

    private PlayerArea[] Gamer = new PlayerArea[9];

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        PopUp_Label.setText(Main.Static_PopUp_Label.getText());
        Gamer[1] = Main.Gamer1; Gamer[2] = Main.Gamer2;
        Gamer[3] = Main.Gamer3; Gamer[4] = Main.Gamer4;
        Gamer[5] = Main.Gamer5; Gamer[6] = Main.Gamer6;
        Gamer[7] = Main.Gamer7; Gamer[8] = Main.Gamer8;

        if (Main.static_player_place == 30) { // GO_JAIL
            Button_Yes.setVisible(false);
            Button_No.setVisible(false);
            Button_OK.setVisible(true);
        } else { // JAIL visiting
            Button_Yes.setVisible(true);
            Button_No.setVisible(true);
            Button_OK.setVisible(false);
        }
    }

    @FXML
    private void Click_On_OK(MouseEvent event) {
        closeWindow(event);
    }

    @FXML
    private void Click_ON_NO(MouseEvent event) {
        closeWindow(event);
    }

    @FXML
    private void Click_On_Yes(MouseEvent event) {
        // Pay $50 to get out of jail
        int jailFine = Constants.JAIL_FINE;
        int newAmount = Gamer[Main.player_turn].getPlayer().getMoney() - jailFine;
        Gamer[Main.player_turn].getPlayer().setMoney(newAmount);
        Gamer[Main.player_turn].getPlayer().setInJail(false);
        Main.freeParkingPool += jailFine;

        Gamer[Main.player_turn].getLabel_amount().setText(newAmount + " $");
        FadeTransition ft = new FadeTransition(Duration.millis(100),
                Gamer[Main.player_turn].getLabel_amount());
        ft.setFromValue(1); ft.setToValue(0);
        ft.setCycleCount(12); ft.setAutoReverse(true); ft.play();

        new PlayNewMedia("/sound/Jail Free.mp3").run();
        closeWindow(event);
    }

    private void closeWindow(MouseEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
