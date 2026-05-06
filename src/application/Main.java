package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import model.Board;
import model.Chance;
import model.Dice;
import model.Player;
import model.PlayerArea;
import model.Property;
import java.util.List;
import java.util.ArrayList;

public class Main extends Application {

    //SHARED GAME STATE
    public static Label Static_PopUp_Label;
    public static int player_turn = 1;              // 1-8
    public static int static_player_place = 0;     // 1-40
    public static Property currentProperty;
    public static int freeParkingPool = 0;

    //IMAGE VIEW ARRAYS FOR BUILDINGS
    public static ImageView[] images_owned_buildings = new ImageView[41];
    public static ImageView[] images_house_buildings = new ImageView[41];
    public static ImageView[] images_hotel_buildings = new ImageView[41];

    //PLAYER AREAS
    public static PlayerArea Gamer1;
    public static PlayerArea Gamer2;
    public static PlayerArea Gamer3;
    public static PlayerArea Gamer4;
    public static PlayerArea Gamer5;
    public static PlayerArea Gamer6;
    public static PlayerArea Gamer7;
    public static PlayerArea Gamer8;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML_files/FirstPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
