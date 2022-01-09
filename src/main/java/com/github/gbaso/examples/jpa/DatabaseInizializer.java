package com.github.gbaso.examples.jpa;

import com.github.gbaso.examples.jpa.entities.Customer;
import com.github.gbaso.examples.jpa.entities.CustomerOrder;
import com.github.gbaso.examples.jpa.repositories.CustomerRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DatabaseInizializer {

    private final TransactionTemplate transactionTemplate;

    @Bean
    CommandLineRunner addCustomers(CustomerRepository customerRepository) {
        return args -> transactionTemplate.executeWithoutResult(status -> {
            var bob = new Customer();
            bob.setFirstName("Bob");
            bob.setLastName("Ross");
            new CustomerOrder(bob, "van dyke brown");
            customerRepository.save(bob);

            var sarah = new Customer();
            sarah.setFirstName("Sarah");
            sarah.setLastName("Butt");
            new CustomerOrder(sarah, "prussian blue");
            new CustomerOrder(sarah, "burnt amber");
            new CustomerOrder(sarah, "alizarin crimson");
            customerRepository.save(sarah);
        });
    }

}
