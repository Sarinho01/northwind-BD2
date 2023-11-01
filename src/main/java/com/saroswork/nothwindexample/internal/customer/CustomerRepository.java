package com.saroswork.nothwindexample.internal.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Override
    boolean existsById(String s);

    @Query("SELECT c FROM Customer c "+
            "WHERE c.customerID LIKE upper(concat(:searchTerm, '%')) ")
    List<Customer> search(@Param("searchTerm") String searchTerm);
}
