package com.github.gbaso.examples.jpa.repositories;

import com.github.gbaso.examples.jpa.entities.CustomerOrder;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends BaseEntityRepository<CustomerOrder> {
    
}
