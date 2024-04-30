package com.switchfully.keygeneration.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity(name = "STREET")
public final class Street {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private UUID id;

    @Column(name = "NAME")
    private String name;

    public Street() {
    }

    public Street(String name) {
        this.name = name;
    }

    public Street(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Street{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
