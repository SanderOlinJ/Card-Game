package edu.ntnu.poker_simulator_ntnu_edition;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CardGameApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(CardGameApplication.class.getResource("scenes/start-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Poker Simulator: NTNU Edition");
        stage.setMinWidth(1220);
        stage.setMinHeight(820);
        stage.setMaxWidth(1220);
        stage.setMaxHeight(820);
        stage.setScene(scene);
        stage.show();
    }
}
