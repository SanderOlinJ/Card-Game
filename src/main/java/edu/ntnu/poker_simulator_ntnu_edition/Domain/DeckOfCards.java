package edu.ntnu.poker_simulator_ntnu_edition.Domain;

import java.util.ArrayList;
import java.util.Random;

public class DeckOfCards {
    private ArrayList<PlayingCard> playingCards;

    /**
     * DeckOfCards()
     * Constructor instantiates a deck, also fills it with fillDeck()
     */
    public DeckOfCards(){
        this.playingCards = new ArrayList<>();
        fillDeck();
    }

    /**
     * fillDeck()
     * Method adds all 52 different playing cards to the deck
     */
    private void fillDeck(){
        for (int i = 1; i < 14; i++){
            this.playingCards.add(new PlayingCard('S',i));
            this.playingCards.add(new PlayingCard('H',i));
            this.playingCards.add(new PlayingCard('D',i));
            this.playingCards.add(new PlayingCard('C',i));
        }
    }

    /**
     * getPlayingCards()
     * @return playing cards in deck, ArrayList
     */
    public ArrayList<PlayingCard> getPlayingCards() {
        return playingCards;
    }

    /**
     * toString
     * @return Deck of Cards, String
     */
    @Override
    public String toString() {
        return "DeckOfCards{" +
                "playingCards=" + playingCards +
                '}';
    }

    /**
     * dealHand()
     * Method picks random cards from deck depending on the requested amount of cards.
     * Also removes the cards from the deck after it has been dealt.
     * @param numberOfCards number of cards you want dealt out
     * @return hand of cards, ArrayList
     */
    public ArrayList<PlayingCard> dealHand(int numberOfCards){
        if (numberOfCards < 1 || numberOfCards > 52){
            throw new IllegalArgumentException("numberOfCards has to be between 1 and 52");
        }
        ArrayList<PlayingCard> hand = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numberOfCards; i++){
            PlayingCard playingCard = playingCards.get(random.nextInt(playingCards.size()));
            hand.add(new PlayingCard(playingCard.getSuit(), playingCard.getFace()));
            playingCards.remove(playingCard);
        }
        return hand;
    }
}
