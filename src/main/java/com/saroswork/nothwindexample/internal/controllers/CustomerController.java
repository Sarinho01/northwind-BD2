package com.saroswork.nothwindexample.internal.controllers;

import com.saroswork.nothwindexample.internal.entities.Customer;
import com.saroswork.nothwindexample.internal.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/")
    public ResponseEntity insert(@RequestBody Customer customer){
        if(customerRepository.existsById(customer.getCustomerID()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Identificador já está em uso");

        Customer insertedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(insertedCustomer);
    }

    @PatchMapping("/")
    public ResponseEntity update(@RequestBody Customer customer){
        if(!customerRepository.existsById(customer.getCustomerID()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Esse customer não existe");
        Customer insertedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(insertedCustomer);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable String id){

        Optional<Customer> optionalCustomer = customerRepository.findById(id);


        if(optionalCustomer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Esse customer não existe");
        }
        Customer customer = optionalCustomer.get();
        customerRepository.delete(customer);
        return ResponseEntity.ok(customer);
    }

}
