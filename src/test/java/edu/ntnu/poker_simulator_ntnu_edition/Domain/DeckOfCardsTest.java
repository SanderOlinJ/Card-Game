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
}