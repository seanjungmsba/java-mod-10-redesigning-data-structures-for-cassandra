package com.example.deck.repository;

import com.example.deck.model.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Long> {}
