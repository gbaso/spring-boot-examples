package com.github.gbaso.examples.jpa.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Customer extends BaseEntity {

    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CustomerOrder> orders = new HashSet<>();

    @ToString.Include
    private String orders() {
        return this.orders.stream()
                .map(CustomerOrder::getId)
                .map(String::valueOf)
                .collect(Collectors.joining(",", "[", "]"));
    }

}
