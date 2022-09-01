package com.example.deck.controller;

import com.example.deck.model.Card;
import com.example.deck.model.Deck;
import com.example.deck.model.Value;
import com.example.deck.repository.CardRepository;
import com.example.deck.repository.DeckRepository;
import com.example.deck.repository.ValueRepository;
import com.example.deck.service.ValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static java.util.UUID.randomUUID;

@RestController
public class DeckController {

    // posCounter keeps track of position of the deck
    private Long posCounter = 1L;

    @Autowired
    private DeckRepository deckRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ValueRepository valueRepository;

    @Autowired
    private ValueService valueService;

    /* A. NEW endpoint */
    @GetMapping("/new")
    public String newDeck(@RequestParam(value = "decks", defaultValue = "1") Long decks) {

        // A1. drop tables and start new deck
        deckRepository.deleteAll();
        cardRepository.deleteAll();
        valueRepository.deleteAll();

        // A2. initialize values table
        valueRepository.save(new Value("Two", 2L));
        valueRepository.save(new Value("Three", 3L));
        valueRepository.save(new Value("Four", 4L));
        valueRepository.save(new Value("Five", 5L));
        valueRepository.save(new Value("Six", 6L));
        valueRepository.save(new Value("Seven", 7L));
        valueRepository.save(new Value("Eight", 8L));
        valueRepository.save(new Value("Nine", 9L));
        valueRepository.save(new Value("Ten", 10L));
        valueRepository.save(new Value("Jack", 10L));
        valueRepository.save(new Value("Queen", 10L));
        valueRepository.save(new Value("King", 10L));
        valueRepository.save(new Value("Ace", 11L));

        // A3. initialize card table
        List<String> suits = Arrays.asList("Clubs", "Hearts", "Spades", "Diamonds");
        List<String> names = Arrays.asList("Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
                "Nine", "Ten", "Jack", "Queen", "King", "Ace");

        // A4. nested for-loops to define cards
        for (Long deck = 1L; deck <= decks; deck++) {
            for (String suit : suits) {
                for (String name : names) {
                    UUID uuid = randomUUID();
                    Card card = new Card(uuid, name, suit, deck);
                    Value value = valueService.getValueByName(name);
                    card.setPoints(value.getPoints());
                    cardRepository.save(card);
                }
            }
        }

        // A5. initialize deck table
        Long position = 1L;
        Iterable<Card> cards = cardRepository.findAll();
        for (Card card : cards) {
            Deck deckItem = new Deck(card, position);
            deckRepository.save(deckItem);
            position++;
        }

        // A6. return decks
        return String.format("New Deck using %s decks.", decks);
    }

    /* B. SHUFFLE endpoint */
    @GetMapping("/shuffle")
    public String shuffleDeck() {

        // B1. read order of cards
        Iterable<Deck> deck = deckRepository.findAll();
        List<Long> order = new ArrayList<Long>();
        for (Deck deckItem : deck) {
            order.add(deckItem.getPosition());
        }

        // B2. shuffle order
        Collections.shuffle(order);

        // B3. write new order of cards
        ListIterator<Long> orderItr = order.listIterator();
        for (Deck deckItem : deck) {
            deckItem.setPosition(orderItr.next());
            deckRepository.save(deckItem);
        }

        // B4. position counter reset
        posCounter = 1L;

        // B5. return status
        return "Deck shuffled.";
    }

    /* C. DEAL endpoint */
    @GetMapping("/deal")
    public String dealCard() {

        // C1. deal the card that is on the current position (make sure to increment the index afterwards)
        Deck deckItem = deckRepository.findByPosition(posCounter++).orElseGet(null);

        // C2. delete deck from repository
        deckRepository.delete(deckItem);

        // C3. return card info
        return String.format("Dealt %s of %s: Worth %s points.",
                deckItem.getCardName(),
                deckItem.getSuit(),
                deckItem.getPoints());
    }

}