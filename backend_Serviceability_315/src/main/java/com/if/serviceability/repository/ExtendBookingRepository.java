package com.if.serviceability.repository;


import com.if.serviceability.model.ExtendBooking;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class ExtendBookingRepository extends SimpleJpaRepository<ExtendBooking, String> {
    private final EntityManager em;
    public ExtendBookingRepository(EntityManager em) {
        super(ExtendBooking.class, em);
        this.em = em;
    }
    @Override
    public List<ExtendBooking> findAll() {
        return em.createNativeQuery("Select * from \"serviceability\".\"ExtendBooking\"", ExtendBooking.class).getResultList();
    }
}