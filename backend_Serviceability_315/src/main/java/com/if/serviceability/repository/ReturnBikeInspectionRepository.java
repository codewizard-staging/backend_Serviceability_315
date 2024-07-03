package com.if.serviceability.repository;


import com.if.serviceability.model.ReturnBikeInspection;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class ReturnBikeInspectionRepository extends SimpleJpaRepository<ReturnBikeInspection, String> {
    private final EntityManager em;
    public ReturnBikeInspectionRepository(EntityManager em) {
        super(ReturnBikeInspection.class, em);
        this.em = em;
    }
    @Override
    public List<ReturnBikeInspection> findAll() {
        return em.createNativeQuery("Select * from \"serviceability\".\"ReturnBikeInspection\"", ReturnBikeInspection.class).getResultList();
    }
}