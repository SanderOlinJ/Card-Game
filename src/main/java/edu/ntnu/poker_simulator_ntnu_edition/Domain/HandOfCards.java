package edu.ntnu.poker_simulator_ntnu_edition.Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    /**
     * isFlush()
     * Method uses streams to check if hand holds a flush (5 cards in sequential order of the same suit)
     * @return true if Flush, false if not
     */
    public boolean isFlush(){
        return hand.stream().filter(playingCard -> playingCard.getSuit() == 'S').count() > 4 ||
                hand.stream().filter(playingCard -> playingCard.getSuit() == 'H').count() > 4 ||
                hand.stream().filter(playingCard -> playingCard.getSuit() == 'D').count() > 4 ||
                hand.stream().filter(playingCard -> playingCard.getSuit() == 'C').count() > 4;
    }

    /**
     * isFourOfAKind()
     * Method uses streams and mostOccurringFaceInHand to find the most frequent occurring face in hand.
     * Then checks if it's equals to 4.
     * @return true if four of a kind, false if not
     */
    public boolean isFourOfAKind(){
        return hand.stream().filter(playingCard -> playingCard.getFace() == mostOccurringFaceInHand())
                .count() == 4;
    }

    /**
     * isThreeOfAKind()
     * Method uses streams and mostOccurringFaceInHand to find the most frequent occurring face in hand.
     * Then checks if it's equals to 3.
     * @return true if three of a kind, false if not
     */
    public boolean isThreeOfAKind(){
        return hand.stream().filter(playingCard -> playingCard.getFace() == mostOccurringFaceInHand())
                .count() == 3;
    }

    /**
     * mostOccurringFaceInHand()
     * Method uses streams to collect the most frequent face in hand
     * @return most frequent face in hand, int
     */
    public int mostOccurringFaceInHand(){
        List<Integer> faces = hand.stream().map(PlayingCard::getFace).collect(Collectors.toList());

        return faces.stream().collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting())).entrySet()
                .stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(0);
    }

    /**
     * getHand()
     * @return hand, ArrayList
     */
    public ArrayList<PlayingCard> getHand() {
        return hand;
    }


    /**
     * checkPokerHand()
     * Method checks your hand for each the possible poker hand combinations
     * @return poker hand combination, String
     */
    public String checkPokerHand(){
        if (isFlush()){
            return "Flush";
        }else if (isFourOfAKind()){
            return "Four Of A Kind";
        }else if (isThreeOfAKind()){
            return "Three Of A Kind";
        }else{
            return "No Combination";
        }
    }

    /**
     * sumOfFacesOnHand()
     * Method adds all faces on your hand
     * @return sum of all faces on hand, int
     */
    public int sumOfFacesOnHand() {
        return hand.stream().map(PlayingCard::getFace).reduce(Integer::sum).get();
    }

    /**
     * getCardsOfHearts()
     * Method uses StringBuilder and stream to append each card of hearts as string to another string
     * @return all cards of hearts on hand (String), returns "No Hearts" if none
     */
    public String getCardsOfHearts(){
        StringBuilder stringBuilder = new StringBuilder();

        hand.stream().filter(playingCard -> playingCard.getSuit() == 'H').forEach(playingCard ->
                stringBuilder.append(playingCard.getAsString()).append(" "));

        if (stringBuilder.isEmpty()){
            stringBuilder.append("No Hearts ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    /**
     * isQueenOfSpadesOnHand()
     * Method uses streams and anyMatch to check if Queen of Spades is on hand
     * @return true If Queen of Spades is on hand, false if not
     */
    public boolean isQueenOfSpadesOnHand(){
        return hand.stream().anyMatch(playingCard -> Objects.equals(playingCard.getAsString(), "S12"));
    }
}
