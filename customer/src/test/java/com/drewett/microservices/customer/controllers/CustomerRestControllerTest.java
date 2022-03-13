package com.drewett.microservices.customer.controllers;

import com.drewett.microservices.customer.api.CreateCustomerRequest;
import com.drewett.microservices.customer.api.CreateCustomerResponse;
import com.drewett.microservices.customer.services.CustomerService;
import com.drewett.microservices.customer.services.CustomerServiceTest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.AssertionErrors;


@Slf4j
@ExtendWith(SpringExtension.class)
public class CustomerRestControllerTest {


    CustomerRestController customerRestController;

    @MockBean
    CustomerService customerService;

    @BeforeEach
    public void init() {
        customerRestController = new CustomerRestController(customerService);
    }

    @Test
    public void createCustomer() {
        log.info("createCustomer");

        Mockito.when(customerService.createCustomer(null)).thenReturn(1L);

        final ResponseEntity<CreateCustomerResponse> response = customerRestController.createCustomer(null);

        AssertionErrors.assertEquals("Response must not be SUCCESS", HttpStatus.OK, response.getStatusCode());
        AssertionErrors.assertEquals("Response must have id", 1L, response.getBody().getId());
    }

}
