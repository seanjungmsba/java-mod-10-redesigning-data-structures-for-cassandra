package com.example.deck.repository;

import com.example.deck.model.Deck;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DeckRepository extends CrudRepository<Deck, Long> {
    Optional<Deck> findFirstByOrderByPositionDesc();
}
