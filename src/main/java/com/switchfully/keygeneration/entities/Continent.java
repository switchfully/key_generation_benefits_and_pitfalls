package com.switchfully.keygeneration.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity(name = "CONTINENT")
public final class Continent {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private UUID id;

    @Column(name = "NAME")
    private String name;

    public Continent() {
    }

    public Continent(String name) {
        this.name = name;
    }

    public Continent(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Continent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Continent continent = (Continent) o;
        return Objects.equals(id, continent.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
