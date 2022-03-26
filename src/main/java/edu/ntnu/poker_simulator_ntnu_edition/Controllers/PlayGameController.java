package edu.ntnu.poker_simulator_ntnu_edition.Controllers;
import edu.ntnu.poker_simulator_ntnu_edition.CardGameApplication;
import edu.ntnu.poker_simulator_ntnu_edition.Domain.DeckOfCards;
import edu.ntnu.poker_simulator_ntnu_edition.Domain.HandOfCards;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;

public class PlayGameController {

    private DeckOfCards deckOfCards;
    private HandOfCards hand;
    @FXML private Button checkHandButton;
    @FXML private Button dealHandButton;
    @FXML private Button goBackButton;
    @FXML private Button newDeckButton;
    @FXML private ImageView cardDeckImage;
    @FXML private ImageView randomCard1;
    @FXML private ImageView randomCard2;
    @FXML private ImageView randomCard3;
    @FXML private ImageView randomCard4;
    @FXML private ImageView randomCard5;
    @FXML private Text newDeckText;
    @FXML private TextField cardsOfHeartsTextField;
    @FXML private TextField s12TextField;
    @FXML private TextField pokerHandTextField;
    @FXML private TextField sumFacesTextField;


    /**
     * goBackButtonPressed()
     * Returns the user back to the start screen
     * @param event
     * @throws IOException
     */
    @FXML
    void goBackButtonPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(CardGameApplication
                .class.getResource("scenes/start-screen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * onDealHandButton()
     * Calls on the dealHand() method to get hand of 5 cards.
     * Sends each card's image to the GUI
     * Disables itself if deck is about to run out of cards
     * @param event
     */
    @FXML
    void onDealHandButtonPressed(ActionEvent event) {
        setTextFieldsToBlank();
        checkHandButton.setDisable(false);
        randomCard1.setVisible(true);
        randomCard2.setVisible(true);
        randomCard3.setVisible(true);
        randomCard4.setVisible(true);
        randomCard5.setVisible(true);

        try {
            hand = new HandOfCards(deckOfCards.dealHand(5));
            randomCard1.setImage(new Image(getPathToCardImages(hand.getHand().get(0).getAsString())));
            randomCard2.setImage(new Image(getPathToCardImages(hand.getHand().get(1).getAsString())));
            randomCard3.setImage(new Image(getPathToCardImages(hand.getHand().get(2).getAsString())));
            randomCard4.setImage(new Image(getPathToCardImages(hand.getHand().get(3).getAsString())));
            randomCard5.setImage(new Image(getPathToCardImages(hand.getHand().get(4).getAsString())));
        } catch (IllegalArgumentException exception){
            exception.getMessage();

        }
        if (hand.isFlush()){
            dealHandButton.setDisable(true);
        }
        if (deckOfCards.getPlayingCards().size()<5){
            dealHandButton.setDisable(true);
            newDeckButton.setDisable(false);
            newDeckText.setVisible(true);
            cardDeckImage.setVisible(false);

        }

    }

    /**
     * onNewDeckButtonPressed()
     * Instantiates a new deck to the scene
     * @param event
     */
    @FXML
    void onNewDeckButtonPressed(ActionEvent event) {
        newDeckText.setVisible(false);
        deckOfCards = new DeckOfCards();
        dealHandButton.setDisable(false);
        checkHandButton.setDisable(true);
        cardDeckImage.setVisible(true);
        randomCard1.setVisible(false);
        randomCard2.setVisible(false);
        randomCard3.setVisible(false);
        randomCard4.setVisible(false);
        randomCard5.setVisible(false);

        setTextFieldsToBlank();
    }

    /**
     * getPathToCardImages()
     * Method gets the path for each card's image
     * @param cardAsString
     * @return path to the image file, String
     */
    private String getPathToCardImages(String cardAsString){
        return String.format("file:" +
                "src/main/resources/edu/ntnu/poker_simulator_ntnu_edition/scenes" +
                "/jpgs_and_pngs/playingcardimages/%s", cardAsString) +".png";
    }

    /**
     * onCheckHandButtonPressed()
     * Calls on checkPokerHand(), sumOfFacesOnHand(), getCardsOfHearts() and isQueenOfSpadesOnHand()
     * And returns each answer to the GUI
     * @param event
     */
    @FXML
    void onCheckHandButtonPressed(ActionEvent event){
        pokerHandTextField.setText(hand.checkPokerHand());
        sumFacesTextField.setText("" + hand.sumOfFacesOnHand());
        cardsOfHeartsTextField.setText(hand.getCardsOfHearts());
        String str = hand.isQueenOfSpadesOnHand() + "";
        str = Character.toUpperCase(str.charAt(0)) + str.substring(1);
        s12TextField.setText(str);
        checkHandButton.setDisable(true);
    }

    /**
     * setTextFieldsToBlank()
     * Method sets the text fields relating to the check hand button, blank
     */
    private void setTextFieldsToBlank(){
        sumFacesTextField.setText("");
        s12TextField.setText("");
        cardsOfHeartsTextField.setText("");
        pokerHandTextField.setText("");
    }


}
