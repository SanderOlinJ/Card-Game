package edu.ntnu.poker_simulator_ntnu_edition.Domain;

import java.util.ArrayList;

public class HandOfCards {
    private ArrayList<PlayingCard> hand;

    public HandOfCards(ArrayList<PlayingCard> handOfCards){
        if (handOfCards == null || handOfCards.isEmpty()){
            throw new IllegalArgumentException("Hand of cards is null or empty");
        }
        this.hand = new ArrayList<>();
        hand.addAll(handOfCards);
    }

    public boolean isFlush(){
        return hand.stream().filter(playingCard -> playingCard.getSuit() == 'S').count() > 4 ||
                hand.stream().filter(playingCard -> playingCard.getSuit() == 'H').count() > 4 ||
                hand.stream().filter(playingCard -> playingCard.getSuit() == 'D').count() > 4 ||
                hand.stream().filter(playingCard -> playingCard.getSuit() == 'C').count() > 4;
    }
}
