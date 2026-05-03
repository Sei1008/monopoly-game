package controller;

import MediaClass.PlayNewMedia;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstPage implements Initializable {

    // UI
    @FXML private AnchorPane mainMenu;
    @FXML private AnchorPane startPane;
    @FXML private ImageView image_start;
    @FXML private ImageView image_exit;
    @FXML private ImageView image_triangle;
    @FXML private TextField textfield_amount;

    // Data
    public static int numberOfPlayers = 1;
    public static int startMoney = 1500;

    private final double[] dotPositions = {172, 210, 242, 277, 314, 349, 384, 418};
    private PlayNewMedia clickSoundPlayer;

    // Init
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startPane.setVisible(false);
        mainMenu.setVisible(true);
        image_triangle.setLayoutX(dotPositions[0]);
    }

    // Main menu
    @FXML
    private void clicked_Start(MouseEvent event) {
        playClickSound();
        mainMenu.setVisible(false);
        startPane.setVisible(true);
    }

    @FXML
    private void exitGame(MouseEvent event) {
        playClickSound();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    // Start pane
    @FXML
    private void backToMenu(MouseEvent event) {
        playClickSound();
        startPane.setVisible(false);
        mainMenu.setVisible(true);
    }

    @FXML
    private void selectPlayer(MouseEvent event) {
        playClickSound();
        ImageView clicked = (ImageView) event.getSource();
        numberOfPlayers = Integer.parseInt(clicked.getUserData().toString());
        image_triangle.setLayoutX(dotPositions[numberOfPlayers - 1]);
        System.out.println("Players selected: " + numberOfPlayers);
    }

    @FXML
    private void ClickedOnnew(MouseEvent event) throws IOException {
        playClickSound();
        try {
            startMoney = Integer.parseInt(textfield_amount.getText());
        } catch (Exception e) {
            startMoney = 1500;
        }

        System.out.println("Players: " + numberOfPlayers);
        System.out.println("Money: " + startMoney);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/SecondPage.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(false);
        scrollPane.setFitToHeight(false);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background: black; -fx-background-color: black;");

        Scene scene = new Scene(scrollPane, screen.getWidth(), screen.getHeight());
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setX(screen.getMinX());
        stage.setY(screen.getMinY());
        stage.setWidth(screen.getWidth());
        stage.setHeight(screen.getHeight());
        stage.show();

        Stage current = (Stage) ((Node) event.getSource()).getScene().getWindow();
        current.close();
    }

    // Hover effect
    @FXML
    private void hoverButton(MouseEvent e) {
        ((ImageView) e.getSource()).setOpacity(0.7);
    }

    @FXML
    private void exitHoverButton(MouseEvent e) {
        ((ImageView) e.getSource()).setOpacity(1.0);
    }

    @FXML
    private void hoverPlayer(MouseEvent e) {
        ImageView img = (ImageView) e.getSource();
        img.setScaleX(1.2);
        img.setScaleY(1.2);
    }

    @FXML
    private void exitHoverPlayer(MouseEvent e) {
        ImageView img = (ImageView) e.getSource();
        img.setScaleX(1.0);
        img.setScaleY(1.0);
    }

    void playClickSound() {
        if (clickSoundPlayer != null) {
            clickSoundPlayer.dispose();
        }
        clickSoundPlayer = new PlayNewMedia("/sounds/undertale-select-sound.mp3");
        clickSoundPlayer.run();
    }
}