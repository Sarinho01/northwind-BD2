package com.saroswork.nothwindexample.internal.controllers;

import com.saroswork.nothwindexample.internal.entities.Customer;
import com.saroswork.nothwindexample.internal.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/")
    public ResponseEntity<List<Customer>> findAll(){
        List<Customer> customers = customerRepository.findAll();
        return ResponseEntity.ok().body(customers);
    }

}
