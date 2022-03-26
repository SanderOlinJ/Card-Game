package edu.ntnu.poker_simulator_ntnu_edition.Domain;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


class DeckOfCardsTest {
    @Nested
    class testingDeckOfCardsConstructor {
        @Test
        @DisplayName("Test that DeckOfCards get correct number of cards during initialization")
        void testThatDeckOfCardsGetCorrectNumberOfCardsDuringInitialization() {
            DeckOfCards deckOfCards = new DeckOfCards();

            assertEquals(52, deckOfCards.getPlayingCards().size());
        }

        @Test
        @DisplayName("Test that DeckOfCards get correct number of spades during initialization")
        void testThatDeckOfCardsGetCorrectNumberOfSpadesDuringInitialization() {
            DeckOfCards deckOfCards = new DeckOfCards();

            List<PlayingCard> spades =
                    deckOfCards.getPlayingCards().stream()
                            .filter(playingCard -> playingCard.getSuit() == 'S')
                            .collect(Collectors.toList());

            assertEquals(13, spades.size());
        }

        @Test
        @DisplayName("Test that DeckOfCards get correct number of hearts during initialization")
        void testThatDeckOfCardsGetCorrectNumberOfHeartsDuringInitialization() {
            DeckOfCards deckOfCards = new DeckOfCards();

            List<PlayingCard> hearts =
                    deckOfCards.getPlayingCards().stream()
                            .filter(playingCard -> playingCard.getSuit() == 'H')
                            .collect(Collectors.toList());

            assertEquals(13, hearts.size());
        }

        @Test
        @DisplayName("Test that DeckOfCards get correct number of diamonds during initialization")
        void testThatDeckOfCardsGetCorrectNumberOfDiamondsDuringInitialization() {
            DeckOfCards deckOfCards = new DeckOfCards();

            List<PlayingCard> diamonds =
                    deckOfCards.getPlayingCards().stream()
                            .filter(playingCard -> playingCard.getSuit() == 'D')
                            .collect(Collectors.toList());

            assertEquals(13, diamonds.size());
        }

        @Test
        @DisplayName("Test that DeckOfCards get correct number of clubs during initialization")
        void testThatDeckOfCardsGetCorrectNumberOfClubsDuringInitialization() {
            DeckOfCards deckOfCards = new DeckOfCards();

            List<PlayingCard> clubs =
                    deckOfCards.getPlayingCards().stream()
                            .filter(playingCard -> playingCard.getSuit() == 'C')
                            .collect(Collectors.toList());

            assertEquals(13, clubs.size());
        }
    }

    @Nested
    class testsForDealHandMethod {

        @Test
        @DisplayName("Test that DealHand deals expected number of cards")
        void testThatDealHandDealsExpectedNumberOfCards() {
            DeckOfCards deckOfCards = new DeckOfCards();

            ArrayList<PlayingCard> playingCards = deckOfCards.dealHand(5);
            assertEquals(5, playingCards.size());
        }

        @Test
        @DisplayName("Test that DealHand does not deal duplicates")
        void testThatDealHandDoesNotDealDuplicates() {
            DeckOfCards deckOfCards = new DeckOfCards();
            ArrayList<PlayingCard> playingCards = deckOfCards.dealHand(52);

            for (int i = 0; i < playingCards.size(); i++) {
                for (int j = i + 1; j < playingCards.size(); j++) {
                    if (playingCards.get(i).equals(playingCards.get(j))) {
                        fail();
                    }
                }
            }
        }

        @Test
        @DisplayName("Test that DealHand removes cards from deck")
        void testThatDealHandRemovesCardsFromDeck(){
            DeckOfCards deckOfCards = new DeckOfCards();
            ArrayList<PlayingCard> playingCards = deckOfCards.dealHand(5);

            assertEquals(47, deckOfCards.getPlayingCards().size());
        }
    }
}