package edu.ntnu.poker_simulator_ntnu_edition.Domain;

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
}