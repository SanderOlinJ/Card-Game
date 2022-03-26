package edu.ntnu.poker_simulator_ntnu_edition.Controllers;
import edu.ntnu.poker_simulator_ntnu_edition.CardGameApplication;
import edu.ntnu.poker_simulator_ntnu_edition.Domain.DeckOfCards;
import edu.ntnu.poker_simulator_ntnu_edition.Domain.HandOfCards;
import edu.ntnu.poker_simulator_ntnu_edition.Domain.PlayingCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class PlayGameController {
    @FXML
    private Button checkHandButton;

    @FXML
    private Button dealHandButton;

    @FXML
    private Button goBackButton;

    @FXML
    private Button newDeckButton;

    @FXML
    private ImageView cardDeckImage;

    private DeckOfCards deckOfCards;

    @FXML
    private ImageView randomCard1;

    @FXML
    private ImageView randomCard2;

    @FXML
    private ImageView randomCard3;

    @FXML
    private ImageView randomCard4;

    @FXML
    private ImageView randomCard5;

    @FXML
    private Text newDeckText;


    @FXML
    void goBackButtonPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(CardGameApplication
                .class.getResource("scenes/start-screen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onDealHandButtonPressed(ActionEvent event) {
        checkHandButton.setDisable(false);
        randomCard1.setVisible(true);
        randomCard2.setVisible(true);
        randomCard3.setVisible(true);
        randomCard4.setVisible(true);
        randomCard5.setVisible(true);
        try {
            HandOfCards hand = new HandOfCards(deckOfCards.dealHand(5));
            randomCard1.setImage(new Image(getPathToCardImages(hand.getHand().get(0).getAsString())));
            randomCard2.setImage(new Image(getPathToCardImages(hand.getHand().get(1).getAsString())));
            randomCard3.setImage(new Image(getPathToCardImages(hand.getHand().get(2).getAsString())));
            randomCard4.setImage(new Image(getPathToCardImages(hand.getHand().get(3).getAsString())));
            randomCard5.setImage(new Image(getPathToCardImages(hand.getHand().get(4).getAsString())));
        } catch (IllegalArgumentException exception){
            exception.getMessage();

        }
        if (deckOfCards.getPlayingCards().size()<5){
            dealHandButton.setDisable(true);
            newDeckButton.setDisable(false);
            newDeckText.setVisible(true);
        }

    }

    @FXML
    void onNewDeckButtonPressed(ActionEvent event) {
        newDeckText.setVisible(false);
        deckOfCards = new DeckOfCards();
        newDeckButton.setDisable(true);
        dealHandButton.setDisable(false);
        cardDeckImage.setVisible(true);
        randomCard1.setVisible(false);
        randomCard2.setVisible(false);
        randomCard3.setVisible(false);
        randomCard4.setVisible(false);
        randomCard5.setVisible(false);
    }

    private String getPathToCardImages(String cardAsString){
        return String.format("file:" +
                "src/main/resources/edu/ntnu/poker_simulator_ntnu_edition/scenes" +
                "/jpgs_and_pngs/playingcardimages/%s", cardAsString) +".png";
    }



}
