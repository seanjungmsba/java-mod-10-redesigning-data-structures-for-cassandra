package com.example.deck.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Value implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long points;

    @OneToMany(mappedBy = "value")
    private List<Card> cardList;

    protected Value() {}

    public Value(String name, Long points) {
        setName(name);
        setPoints(points);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

}