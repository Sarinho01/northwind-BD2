package com.saroswork.nothwindexample.internal.repositories;

import com.saroswork.nothwindexample.internal.entities.Customer;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Override
    boolean existsById(String s);
}
