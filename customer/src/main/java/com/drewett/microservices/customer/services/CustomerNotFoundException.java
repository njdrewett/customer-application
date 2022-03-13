package com.drewett.microservices.customer.services;

public class CustomerNotFoundException extends RuntimeException {

    private final Long customerId;
    public CustomerNotFoundException(final Long customerId) {
        super("ID :" +customerId);
        this.customerId = customerId;
    }
}
