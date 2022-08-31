package com.example.deck.repository;

import com.example.deck.model.Value;
import org.springframework.data.repository.CrudRepository;

public interface ValueRepository extends CrudRepository<Value, Long> {
    Value findValueByName(String name);
}
