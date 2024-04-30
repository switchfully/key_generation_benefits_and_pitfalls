package com.switchfully.keygeneration.entities;

import jakarta.persistence.*;

import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(name = "REGION")
public final class Region {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    public Region() {
    }

    public Region(String name) {
        this.name = name;
    }

    public Region(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer id() {
        return id;
    }
}
