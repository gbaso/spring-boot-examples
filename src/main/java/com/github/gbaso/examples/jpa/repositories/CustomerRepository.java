package com.github.gbaso.examples.jpa.repositories;

import com.github.gbaso.examples.jpa.entities.Customer;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends BaseEntityRepository<Customer> {
    
}
