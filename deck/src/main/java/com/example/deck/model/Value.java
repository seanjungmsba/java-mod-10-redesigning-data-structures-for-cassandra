package com.example.deck.model;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

@Table
public class Value implements Serializable {
    private UUID id;

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private String name;
    private Long points;

    protected Value() {
    }

    public Value(String name, Long points) {
        setName(name);
        setPoints(points);
        this.id = UUID.randomUUID();
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