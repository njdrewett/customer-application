package com.drewett.microservices.customer.domain;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.AssertionErrors;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Optional;

@Slf4j
@DataJpaTest
public class CustomerRepositoryTest {

    CustomerRepository customerRepository;

    @Autowired
    CustomerRepositoryTest(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Test
    public void testSave() {

        Customer customer = new Customer(null, "Neil","Drewett", "James",new GregorianCalendar(1979, Calendar.JANUARY, 21).getTime());

        Customer savedCustomer = customerRepository.save(customer);

        AssertionErrors.assertNotNull("customer cannot have null id" , savedCustomer.getId());

        final Collection<Customer> loadedCustomer = customerRepository.findAll();


        log.info("Loaded {}",loadedCustomer);
    }

    @Test
    public void testSaveAgain() {

        Customer customer = new Customer(null, "Neil","Drewett", "James",new GregorianCalendar(1979, Calendar.JANUARY, 21).getTime());

        Customer savedCustomer = customerRepository.save(customer);

        AssertionErrors.assertNotNull("customer cannot have null id" , savedCustomer.getId());

        final Collection<Customer> loadedCustomer = customerRepository.findAll();


        log.info("Loaded {}",loadedCustomer);
    }

}
