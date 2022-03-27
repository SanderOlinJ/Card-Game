package edu.ntnu.poker_simulator_ntnu_edition.Domain;

import java.util.*;
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
     * isOnePair()
     * Method checks if two cards in hand has the same face value
     * Uses the mostOccurringFaceInHand() to find most frequent face value,
     * then checks if there are exactly two of them.
     * @return true if one pair, false if not
     */
    public boolean isOnePair(){
        return hand.stream().filter(playingCard -> playingCard.getFace() == mostOccurringFaceInHand())
                .count() == 2;
    }

    /**
     * isTwoPair()
     * Method checks if there are two separate pairs of cards in hand
     * Uses the mostOccurringFaceInHand() to find most frequent face value
     * Then it uses removeMostOccurringFaceAndCheckIfThereIsAnotherPairInHand()
     * to check if there is another pair in hand
     * @return true if two pairs, false if not
     */
    public boolean isTwoPair(){
        if (hand.stream().filter(playingCard -> playingCard.getFace() == mostOccurringFaceInHand()).count() != 2){
            return false;
        }

        return removeMostOccurringFaceAndCheckIfThereIsAnotherPairInHand();
    }

    /**
     * isFullHouse()
     * Method checks if there are 3 of one card, and 2 of another in hand
     * Uses mostOccurringFaceInHand() to check if there are exactly 3 of the most frequent face
     * Then uses removeMostOccurringFaceAndCheckIfThereIsAnotherPairInHand()
     * to see if there is another pair in hand
     * @return true if full house, false if not
     */
    public boolean isFullHouse(){
        if (hand.stream().filter(playingCard -> playingCard.getFace() == mostOccurringFaceInHand()).count() != 3){
            return false;
        }
        return removeMostOccurringFaceAndCheckIfThereIsAnotherPairInHand();
    }

    /**
     * removeMostOccurringFaceAndCheckIfThereIsAnotherPairInHand()
     * Method removes the most frequent instance of a face in hand
     * Then checks if there is another pair in hand.
     * @return true if another pair in hand, false if not
     */
    private boolean removeMostOccurringFaceAndCheckIfThereIsAnotherPairInHand() {
        int mostOccurring = mostOccurringFaceInHand();
        List<Integer> playingCardFaces = hand.stream().map(PlayingCard::getFace)
                .collect(Collectors.toList());
        hand.forEach(integer -> playingCardFaces.removeIf(integer1 -> integer1 == mostOccurring));

        Collections.sort(playingCardFaces);

        if (playingCardFaces.size() == 3) {
            if (Objects.equals(playingCardFaces.get(0), playingCardFaces.get(1)) ||
                    Objects.equals(playingCardFaces.get(1), playingCardFaces.get(2))) {
                return true;
            }
        }
        if (playingCardFaces.size() == 2){
            return Objects.equals(playingCardFaces.get(0), playingCardFaces.get(1));
        }
        return false;
    }

    /**
     * isStraight()
     * Methods checks if each card in hand is of sequential order
     * Takes into account that the Ace can be the lowest and highest card
     * @return true if straight, false if not
     */
    public boolean isStraight(){
        List<Integer> playingCardFaces = hand.stream().map(PlayingCard::getFace)
                .sorted().collect(Collectors.toList());
        if (playingCardFaces.get(0) == 1 &&
                playingCardFaces.get(0)+1 != playingCardFaces.get(1)) {
            return (playingCardFaces.get(1)) + 1 == playingCardFaces.get(2) &&
                    (playingCardFaces.get(2)) + 1 == playingCardFaces.get(3) &&
                    (playingCardFaces.get(3)) + 1 == playingCardFaces.get(4) &&
                    (playingCardFaces.get(4) + 1 == playingCardFaces.get(0) + 13);
        } else return (playingCardFaces.get(0)) + 1 == playingCardFaces.get(1) &&
                (playingCardFaces.get(1)) + 1 == playingCardFaces.get(2) &&
                (playingCardFaces.get(2)) + 1 == playingCardFaces.get(3) &&
                (playingCardFaces.get(3)) + 1 == playingCardFaces.get(4);
    }

    /**
     * isStraightFlush()
     * Method checks if cards on hand are flush and straight
     * @return true if Straight flush, false if not
     */
    public boolean isStraightFlush(){
        return isFlush() && isStraight();
    }

    /**
     * isRoyalFlush()
     * Method checks if cards on hand are flush and straight, just as isStraightFlush
     * @return true if Royal flush, false if not
     */
    public boolean isRoyalFlush(){
        List<Integer> playingCardFaces = hand.stream().map(PlayingCard::getFace)
                .sorted().collect(Collectors.toList());
        return playingCardFaces.get(0) == 1 && playingCardFaces.get(0)+1 != playingCardFaces.get(1) &&
        isFlush() && isStraight();
    }

    public boolean isHighCard(){
        List<Integer> playingCardFaces = hand.stream().map(PlayingCard::getFace)
                .sorted().collect(Collectors.toList());
        if (playingCardFaces.get(0) == 1){
            return true;
        }
        return playingCardFaces.get(4) > 9;
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
    public String checkPokerHand() {
        if (isRoyalFlush()) {
            return "Royal Flush";
        } else if (isStraightFlush()) {
            return "Straight Flush";
        } else if (isFourOfAKind()) {
            return "Four Of A Kind";
        } else if (isFullHouse()) {
            return "Full House";
        } else if (isFlush()) {
            return "Flush";
        } else if (isStraight()) {
            return "Straight";
        } else if (isThreeOfAKind()) {
            return "Three Of A Kind";
        } else if (isTwoPair()) {
            return "Two Pair";
        } else if (isOnePair()) {
            return "One Pair";
        } else if (isHighCard()){
            return "High Card";
        } else{
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
