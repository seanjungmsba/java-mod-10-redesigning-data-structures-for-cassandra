package com.example.deck.service;

import com.example.deck.model.Value;
import com.example.deck.repository.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValueService {
    @Autowired
    private ValueRepository valueRepository;

    public Value getValueByName(String name) {
        return valueRepository.findValueByName(name);
    }
}