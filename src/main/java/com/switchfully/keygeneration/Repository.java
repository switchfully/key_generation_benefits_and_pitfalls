package com.switchfully.keygeneration;

import com.switchfully.keygeneration.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@org.springframework.stereotype.Repository
public class Repository {

    @PersistenceContext
    private final EntityManager entityManager;

    public Repository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void clearDatabase() {
        entityManager.createQuery("DELETE FROM CONTINENT");
        entityManager.createQuery("DELETE FROM COUNTRY");
        entityManager.createQuery("DELETE FROM CITY");
        entityManager.createQuery("DELETE FROM REGION");
        entityManager.createQuery("DELETE FROM STREET");
    }

    @Transactional
    public Continent merge(Continent continent) {
        return entityManager.merge(continent);
    }

    @Transactional
    public Country merge(Country country) {
        return entityManager.merge(country);
    }

    @Transactional
    public Region merge(Region region) {
        return entityManager.merge(region);
    }

    @Transactional
    public City merge(City city) {
        return entityManager.merge(city);
    }

    @Transactional
    public Street merge(Street street) {
        return entityManager.merge(street);
    }
}
