package com.if.serviceability.repository;


import com.if.serviceability.model.Bike;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class BikeRepository extends SimpleJpaRepository<Bike, String> {
    private final EntityManager em;
    public BikeRepository(EntityManager em) {
        super(Bike.class, em);
        this.em = em;
    }
    @Override
    public List<Bike> findAll() {
        return em.createNativeQuery("Select * from \"serviceability\".\"Bike\"", Bike.class).getResultList();
    }
}