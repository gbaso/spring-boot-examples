package com.github.gbaso.examples.jpa;

import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

import com.github.gbaso.examples.jpa.entities.Customer;
import com.github.gbaso.examples.jpa.entities.CustomerOrder;
import com.github.gbaso.examples.jpa.repositories.CustomerRepository;

@Configuration
public class DatabaseInitializer {

    @Bean
    ApplicationRunner addCustomers(TransactionTemplate transactionTemplate, CustomerRepository customerRepository) {
        return args -> {
            if (customerRepository.count() == 0) {
                transactionTemplate.executeWithoutResult(status -> {
                    List<Customer> customers = createCustomers();
                    customerRepository.saveAll(customers);
                });
            }
        };
    }

    private List<Customer> createCustomers() {
        var bob = new Customer();
        bob.setFirstName("Bob");
        bob.setLastName("Ross");
        new CustomerOrder(bob, "van dyke brown");

        var sarah = new Customer();
        sarah.setFirstName("Sarah");
        sarah.setLastName("Butt");
        new CustomerOrder(sarah, "prussian blue");
        new CustomerOrder(sarah, "burnt amber");
        new CustomerOrder(sarah, "alizarin crimson");
        return List.of(bob, sarah);
    }

}
