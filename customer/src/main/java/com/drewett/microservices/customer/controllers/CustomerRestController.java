package com.drewett.microservices.customer.controllers;

import com.drewett.microservices.customer.api.*;
import com.drewett.microservices.customer.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "api/v1/customers")
public class CustomerRestController {

    private final CustomerService customerService;

    CustomerRestController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CreateCustomerResponse> createCustomer(@RequestBody final CreateCustomerRequest createCustomerRequest) {
        log.info("createCustomer {}" , createCustomerRequest);

       final Long id = customerService.createCustomer(createCustomerRequest);

        CreateCustomerResponse response = new CreateCustomerResponse(id, Boolean.TRUE);
        log.info("Response: {}" , response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path="{customerId}")
    public ResponseEntity<GetCustomerResponse> getCustomer(@PathVariable("customerId") long customerId) {
        log.info("getCustomer {} ", customerId);

        final GetCustomerResponse getCustomerResponse = customerService.getCustomer(customerId);

        log.info("Found customer {}" , getCustomerResponse);
        return new ResponseEntity<>(getCustomerResponse, HttpStatus.OK);
    }

    @GetMapping(path="/query")
    public ResponseEntity<List<GetCustomerResponse>> findCustomersByLastName(@RequestParam("lastName") String lastName) {
        log.info("getCustomersByLastName {} ", lastName);

        final List<GetCustomerResponse> customers = customerService.findCustomersByLastName(lastName);

        log.info("Found getCustomersByLastNames {}" , customers);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping(path="")
    public ResponseEntity<List<GetCustomerResponse>> findAllCustomers() {
        log.info("findAllCustomers");

        final List<GetCustomerResponse> customers = customerService.findAllCustomers();

        log.info("Found findAllCustomers {}" , customers);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping(path="/update")
    public ResponseEntity<UpdateCustomerResponse> UpdateCustomer(@RequestBody UpdateCustomerRequest updateCustomerRequest) {
        log.info("updateCustomer {}", updateCustomerRequest);

        final UpdateCustomerResponse customerResponse = customerService.updateCustomer(updateCustomerRequest);

        log.info("Found updateCustomer {}", customerResponse);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @DeleteMapping(path="{customerId}")
    public void deleteCustomer(@PathVariable("customerId") long customerId) {
        log.info("deleteCustomer {} ", customerId);

        customerService.deleteCustomer(customerId);

        log.info("Delete customer ");
    }

}
