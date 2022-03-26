package edu.ntnu.poker_simulator_ntnu_edition.Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public boolean isFourOfAKind(){
        return hand.stream().filter(playingCard -> playingCard.getFace() == mostOccurringFaceInHand())
                .count() == 4;
    }

    public boolean isThreeOfAKind(){

        return hand.stream().filter(playingCard -> playingCard.getFace() == mostOccurringFaceInHand())
                .count() == 3;
    }

    public int mostOccurringFaceInHand(){
        List<Integer> faces = hand.stream().map(playingCard -> playingCard.getFace()).collect(Collectors.toList());

        return faces.stream().collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting())).entrySet()
                .stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(0);
    }
}
