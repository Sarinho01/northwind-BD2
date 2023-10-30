package com.saroswork.nothwindexample.internal.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Override
    boolean existsById(String s);
}
