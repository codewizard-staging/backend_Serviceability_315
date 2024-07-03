package com.if.serviceability.repository;


import com.if.serviceability.model.Customer;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class CustomerRepository extends SimpleJpaRepository<Customer, String> {
    private final EntityManager em;
    public CustomerRepository(EntityManager em) {
        super(Customer.class, em);
        this.em = em;
    }
    @Override
    public List<Customer> findAll() {
        return em.createNativeQuery("Select * from \"serviceability\".\"Customer\"", Customer.class).getResultList();
    }
}