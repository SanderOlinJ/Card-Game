package edu.ntnu.poker_simulator_ntnu_edition.Controllers;
import edu.ntnu.poker_simulator_ntnu_edition.CardGameApplication;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.*;
import java.io.IOException;
import java.util.Objects;

public class StartScreenController {

    @FXML
    public Button playButton;

    /**
     * Redirects to the "Play Game" Screen
     * @param event
     * @throws IOException
     */
    @FXML
    void buttonPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(CardGameApplication
                .class.getResource("scenes/play-game.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMinWidth(1220);
        stage.setMinHeight(820);
        stage.setMaxWidth(1220);
        stage.setMaxHeight(820);
        stage.show();
    }

}
