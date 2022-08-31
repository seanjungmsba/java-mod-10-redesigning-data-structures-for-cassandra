package com.example.deck.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Deck {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;

    private Long position;

    // Should use OneToOne in theory. There is a bug preventing for this specific implementation however.
    @ManyToOne
    @JoinColumn(name="uuid", referencedColumnName="uuid", insertable=false, updatable=false)
    private Card card;

    protected Deck() {}

    public Deck(Card card, Long position) {
        setCard(card);
        setPosition(position);
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
        this.uuid = card.getUUID();
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}




