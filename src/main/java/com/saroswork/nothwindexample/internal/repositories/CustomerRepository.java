package com.saroswork.nothwindexample.internal.repositories;

import com.saroswork.nothwindexample.internal.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
