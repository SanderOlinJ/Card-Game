package edu.ntnu.poker_simulator_ntnu_edition.Domain;

import javafx.scene.image.Image;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

    @Test
    void testThatGetHandReturnsHand(){
        ArrayList<PlayingCard> handOfCards = new ArrayList<>();
        for (int i = 1; i < 6; i++){
            handOfCards.add(new PlayingCard('S',i));
        }
        HandOfCards hand = new HandOfCards(handOfCards);

        ArrayList<PlayingCard> returnHand = hand.getHand();

        assertEquals("S1",returnHand.get(0).getAsString());
    }
}