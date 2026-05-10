package FXML_Controllers;

import application.Main;
import MediaClass.PlayNewMedia;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import model.PlayerArea;

public class Tax_page extends Main implements Initializable {

    @FXML private Label PopUp_Label;
    @FXML private Button Button_OK;
    @FXML private ImageView Background_Image;

    private PlayerArea[] Gamer = new PlayerArea[9];

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        PopUp_Label.setText(Main.Static_PopUp_Label.getText());

        if (Main.static_player_place == 5) {
            Background_Image.setImage(new Image("/images/backgroundPopUp_IncomeTax.png"));
        } else if (Main.static_player_place == 39) {
            Background_Image.setImage(new Image("/images/backgroundPopUp_LuxuryTax.png"));
        }

        Gamer[1] = Main.Gamer1; Gamer[2] = Main.Gamer2;
        Gamer[3] = Main.Gamer3; Gamer[4] = Main.Gamer4;
        Gamer[5] = Main.Gamer5; Gamer[6] = Main.Gamer6;
        Gamer[7] = Main.Gamer7; Gamer[8] = Main.Gamer8;
    }

    @FXML
    private void Click_On_OK(MouseEvent event) {
        new PlayNewMedia("/sound/Tax.mp3").run();
        closeWindow(event);
    }

    private void closeWindow(MouseEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
}