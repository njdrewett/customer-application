package com.drewett.microservices.customer.services;

import com.drewett.microservices.customer.api.CreateCustomerRequest;
import com.drewett.microservices.customer.api.GetCustomerResponse;
import com.drewett.microservices.customer.api.UpdateCustomerRequest;
import com.drewett.microservices.customer.api.UpdateCustomerResponse;
import com.drewett.microservices.customer.domain.Customer;
import com.drewett.microservices.customer.domain.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerService {

    final CustomerRepository customerRepository;

    @Autowired
    CustomerService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Long createCustomer(final CreateCustomerRequest createCustomerRequest) {
        log.info("CreateCustomer");

        Customer customer = new Customer(null, createCustomerRequest.getFirstName(), createCustomerRequest.getLastName(),
                createCustomerRequest.getMiddleName(), createCustomerRequest.getDateOfBirth());

        Customer savedCustomer = customerRepository.save(customer);

        return savedCustomer.getId();
    }

    public GetCustomerResponse getCustomer(final Long customerId) {
        log.info("getCustomer {}", customerId);

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));

        return new GetCustomerResponse(customer.getId(), customer.getFirstName(), customer.getMiddleName(),
                customer.getLastName(), customer.getDateOfBirth());
    }

    public List<GetCustomerResponse> findCustomersByLastName(String lastName) {
        log.info("getCustomersByLastName {} ", lastName);

        List<Customer> customers = customerRepository.findByLastName(lastName);
        List<GetCustomerResponse> getCustomerResponses = toGetCustomerResponses(customers);

        log.info("Found getCustomersByLastNames {}", getCustomerResponses);
        return getCustomerResponses;
    }

    private List<GetCustomerResponse> toGetCustomerResponses(List<Customer> customers) {
        return customers.stream()
                .map(customer -> new GetCustomerResponse(customer.getId(), customer.getFirstName(), customer.getMiddleName(), customer.getLastName(), customer.getDateOfBirth()))
                .collect(Collectors.toList());
    }

    public List<GetCustomerResponse> findAllCustomers() {
        log.info("findAllCustomers");

        List<Customer> customers = customerRepository.findAll();
        List<GetCustomerResponse> getCustomerResponses = toGetCustomerResponses(customers);

        log.info("Found findAllCustomers {}", getCustomerResponses);
        return getCustomerResponses;
    }

    public UpdateCustomerResponse updateCustomer(final UpdateCustomerRequest updateCustomerRequest) {
        log.info("updateCustomer {}", updateCustomerRequest);

        UpdateCustomerResponse response = new UpdateCustomerResponse(updateCustomerRequest.getId(), false);

        Customer customer = customerRepository.findById(updateCustomerRequest.getId()).orElseThrow(() -> new CustomerNotFoundException(updateCustomerRequest.getId()));

        customer.setDateOfBirth(updateCustomerRequest.getDateOfBirth());
        customer.setFirstName(updateCustomerRequest.getFirstName());
        customer.setLastName(updateCustomerRequest.getLastName());
        customer.setMiddleName(updateCustomerRequest.getMiddleName());
        customerRepository.save(customer);
        response.setSuccess(true);

        log.info("Found updateCustomer {}", response);
        return response;
    }

    public void deleteCustomer(long customerId) {
        log.info("deleteCustomer: {} ", customerId);
        customerRepository.deleteById(customerId);
        log.info("deletedCustomer: {} ", customerId);
    }
}
