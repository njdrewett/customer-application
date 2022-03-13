package com.drewett.microservices.customer.api;

import java.util.Objects;

public class CreateCustomerResponse {

    private Long id;
    private boolean success;

    public CreateCustomerResponse(final Long id, final boolean success) {
        this.id = id;
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "CreateCustomerRequest{" +
                "id=" + id +
                ",success " + success +
                "}";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateCustomerResponse that = (CreateCustomerResponse) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
