package com.github.gbaso.examples.jpa.entities;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
public class CustomerOrder extends BaseEntity {

    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private String sku;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    private Instant created;

    public CustomerOrder(Customer customer, String sku) {
        this.customer = Objects.requireNonNull(customer);
        this.sku = sku;
        customer.getOrders().add(this);
    }

}
