package edu.ntnu.poker_simulator_ntnu_edition.Domain;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class HandOfCardsTest {

    @Test
    void testThatHandOfCardsThrowExceptionIfInputArrayListIsEmpty(){
        ArrayList<PlayingCard> playingCards = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> {HandOfCards hand = new HandOfCards(playingCards);}
        );
    }

    @Nested
    class testIsFlush {
        @Test
        @DisplayName("Test that isFlush return true when true")
        void testThatIsFlushReturnTrueWhenTrue() {

            ArrayList<PlayingCard> handOfCards = new ArrayList<>();
            for (int i = 1; i < 6; i++) {
                handOfCards.add(new PlayingCard('S', i));
            }
            HandOfCards hand = new HandOfCards(handOfCards);

            assertTrue(hand.isFlush());
        }

        @Test
        @DisplayName("Test that isFlush return false when false")
        void testThatIsFlushReturnFalseWhenFalse() {
            ArrayList<PlayingCard> handOfCards = new ArrayList<>();
            for (int i = 1; i < 5; i++) {
                handOfCards.add(new PlayingCard('S', i));
            }
            handOfCards.add(new PlayingCard('H', 10));

            HandOfCards hand = new HandOfCards(handOfCards);

            assertFalse(hand.isFlush());
        }
    }
    @Nested
    class testIsFourOfAKind{
        @Test
        @DisplayName("Test that isFourOfAKind returns true when true")
        void testThatIsFourOfAKindReturnsTrueWhenTrue(){
            ArrayList<PlayingCard> handOfCards = new ArrayList<>();

            handOfCards.add(new PlayingCard('H',2));
            handOfCards.add(new PlayingCard('H',2));
            handOfCards.add(new PlayingCard('H',5));
            handOfCards.add(new PlayingCard('H',2));
            handOfCards.add(new PlayingCard('H',2));

            handOfCards.add(new PlayingCard('D',3));
            handOfCards.add(new PlayingCard('S',4));
            handOfCards.add(new PlayingCard('C',5));
            handOfCards.add(new PlayingCard('D',6));

            HandOfCards hand = new HandOfCards(handOfCards);

            assertTrue(hand.isFourOfAKind());
        }

        @Test
        void testThatIsFourOfAKindReturnsFalseWhenFalse(){
            ArrayList<PlayingCard> handOfCards = new ArrayList<>();
            for (int i = 1; i < 6; i++){
                handOfCards.add(new PlayingCard('S',i));
            }
            HandOfCards hand = new HandOfCards(handOfCards);

            assertFalse(hand.isFourOfAKind());
        }
    }

    @Nested
    class testIsThreeOfAKind{
        @Test
        @DisplayName("Test that isThreeOfAKind returns true when true")
        void testThatIsThreeOfAKindReturnsTrueWhenTrue(){
            ArrayList<PlayingCard> handOfCards = new ArrayList<>();

            handOfCards.add(new PlayingCard('H',2));
            handOfCards.add(new PlayingCard('H',5));
            handOfCards.add(new PlayingCard('H',2));
            handOfCards.add(new PlayingCard('H',2));

            handOfCards.add(new PlayingCard('D',3));
            handOfCards.add(new PlayingCard('S',4));
            handOfCards.add(new PlayingCard('C',5));
            handOfCards.add(new PlayingCard('D',6));

            HandOfCards hand = new HandOfCards(handOfCards);

            assertTrue(hand.isThreeOfAKind());
        }

        @Test
        void testThatIsThreeOfAKindReturnsFalseWhenFalse(){
            ArrayList<PlayingCard> handOfCards = new ArrayList<>();
            for (int i = 1; i < 6; i++){
                handOfCards.add(new PlayingCard('S',i));
            }
            HandOfCards hand = new HandOfCards(handOfCards);

            assertFalse(hand.isThreeOfAKind());
        }
    }

    @Nested
    class testIsTwoPair{

        @Test
        void testThatIsTwoPairReturnsTrueWhenTrue(){
            ArrayList<PlayingCard> playingCards = new ArrayList<>();
            playingCards.add(new PlayingCard('S',3));
            playingCards.add(new PlayingCard('H',3));
            playingCards.add(new PlayingCard('D',8));
            playingCards.add(new PlayingCard('D',9));
            playingCards.add(new PlayingCard('C',9));

            HandOfCards hand = new HandOfCards(playingCards);

            hand.isTwoPair();

            assertTrue(hand.isTwoPair());
        }

        @Test
        void testThatIsTwoPairReturnsFalseWhenFalse(){
            ArrayList<PlayingCard> playingCards = new ArrayList<>();
            playingCards.add(new PlayingCard('S',3));
            playingCards.add(new PlayingCard('H',3));
            playingCards.add(new PlayingCard('H',7));
            playingCards.add(new PlayingCard('D',8));
            playingCards.add(new PlayingCard('C',9));
            HandOfCards hand = new HandOfCards(playingCards);

            assertFalse(hand.isTwoPair());
        }
    }

    @Nested
    class testIsFullHouse{

        @Test
        void testThatIsFullHouseReturnsTrueWhenTrue(){
            ArrayList<PlayingCard> playingCards = new ArrayList<>();
            playingCards.add(new PlayingCard('S',3));
            playingCards.add(new PlayingCard('H',3));
            playingCards.add(new PlayingCard('H',8));
            playingCards.add(new PlayingCard('D',8));
            playingCards.add(new PlayingCard('C',8));
            HandOfCards hand = new HandOfCards(playingCards);

            assertTrue(hand.isFullHouse());
        }

        @Test
        void testThatIsFullHouseReturnsFalseWhenFalse(){
            ArrayList<PlayingCard> playingCards = new ArrayList<>();
            playingCards.add(new PlayingCard('S',3));
            playingCards.add(new PlayingCard('H',3));
            playingCards.add(new PlayingCard('H',8));
            playingCards.add(new PlayingCard('D',8));
            playingCards.add(new PlayingCard('C',7));
            HandOfCards hand = new HandOfCards(playingCards);

            assertFalse(hand.isFullHouse());
        }

    }

    @Nested
    class testsForMethodsUsedForControllerClass{

        @Test
        void testThatCheckPokerHandReturnsAHand(){
            ArrayList<PlayingCard> handOfCards = new ArrayList<>();
            for (int i = 1; i < 6; i++){
                handOfCards.add(new PlayingCard('S',i));
            }
            HandOfCards hand = new HandOfCards(handOfCards);

            assertEquals("Flush", hand.checkPokerHand());
        }

        @Test
        void testThatCheckPokerHandReturnsWhenNoCombination(){
            ArrayList<PlayingCard> handOfCards = new ArrayList<>();

            handOfCards.add(new PlayingCard('H',2));
            handOfCards.add(new PlayingCard('H',5));
            handOfCards.add(new PlayingCard('H',1));

            handOfCards.add(new PlayingCard('D',3));
            handOfCards.add(new PlayingCard('C',6));

            HandOfCards hand = new HandOfCards(handOfCards);


            assertEquals("No Combination", hand.checkPokerHand());
        }

        @Test
        void testThatSumOfFacesOnHandReturnCorrectValue(){
            ArrayList<PlayingCard> handOfCards = new ArrayList<>();
            for (int i = 1; i < 6; i++){
                handOfCards.add(new PlayingCard('S',i));
            }
            HandOfCards hand = new HandOfCards(handOfCards);

            assertEquals(15,hand.sumOfFacesOnHand());
        }

        @Test
        void testThatGetCardsOfHeartsReturnExpectedStringResult(){
            ArrayList<PlayingCard> handOfCards = new ArrayList<>();
            for (int i = 1; i < 6; i++){
                handOfCards.add(new PlayingCard('H',i));
            }
            HandOfCards hand = new HandOfCards(handOfCards);

            assertEquals("H1 H2 H3 H4 H5", hand.getCardsOfHearts());
        }

        @Test
        void testThatGetCardsOfHeartsReturnExpectedStringWhenNoCardsOfHearts(){
            ArrayList<PlayingCard> handOfCards = new ArrayList<>();
            for (int i = 1; i < 6; i++){
                handOfCards.add(new PlayingCard('S',i));
            }
            HandOfCards hand = new HandOfCards(handOfCards);

            assertEquals("No Hearts", hand.getCardsOfHearts());
        }

        @Test
        void testThatIsQueenOfSpadesOnHandReturnFalseWhenFalse(){
            ArrayList<PlayingCard> handOfCards = new ArrayList<>();
            for (int i = 1; i < 6; i++){
                handOfCards.add(new PlayingCard('S',i));
            }
            HandOfCards hand = new HandOfCards(handOfCards);

            assertFalse(hand.isQueenOfSpadesOnHand());
        }

        @Test
        void testThatIsQueenOfSpadesOnHandReturnTrueWhenTrue(){
            ArrayList<PlayingCard> handOfCards = new ArrayList<>();
            for (int i = 9; i < 13; i++){
                handOfCards.add(new PlayingCard('S',i));
            }
            HandOfCards hand = new HandOfCards(handOfCards);
            assertTrue(hand.isQueenOfSpadesOnHand());
        }
    }
}