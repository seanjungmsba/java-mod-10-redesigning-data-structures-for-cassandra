package com.example.deck.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
public class Card implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;

    private String name;
    private String suit;
    private Long deckNumber;

    // Should use OneToOne in theory however there is a bug preventing for this specific implementation
    @OneToMany(mappedBy = "card")
    private List<Deck> deckList;

    @ManyToOne
    @JoinColumn(name = "name", referencedColumnName = "name", insertable = false, updatable = false)
    private Value value;

    protected Card() {
    }

    public Card(UUID uuid, String name, String suit, Long deckNumber) {
        setUUID(uuid);
        setName(name);
        setSuit(suit);
        setDeckNumber(deckNumber);
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public Long getDeckNumber() {
        return deckNumber;
    }

    public void setDeckNumber(Long deckNumber) {
        this.deckNumber = deckNumber;
    }

    public Value getValue() {
        return this.value;
    }

}