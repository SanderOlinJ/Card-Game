package edu.ntnu.poker_simulator_ntnu_edition.Domain;

import java.util.ArrayList;
import java.util.Random;

public class DeckOfCards {
    private ArrayList<PlayingCard> playingCards;

    public DeckOfCards(){
        this.playingCards = new ArrayList<>();
        fillDeck();
    }

    private void fillDeck(){
        for (int i = 1; i < 14; i++){
            this.playingCards.add(new PlayingCard('S',i));
            this.playingCards.add(new PlayingCard('H',i));
            this.playingCards.add(new PlayingCard('D',i));
            this.playingCards.add(new PlayingCard('C',i));
        }
    }

    public ArrayList<PlayingCard> getPlayingCards() {
        return playingCards;
    }

    @Override
    public String toString() {
        return "DeckOfCards{" +
                "playingCards=" + playingCards +
                '}';
    }

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
