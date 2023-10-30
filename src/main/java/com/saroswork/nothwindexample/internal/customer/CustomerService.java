package com.saroswork.nothwindexample.internal.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;


    public List<Customer> findAll() {
        return customerRepository.findAll();
    }


    public Customer insert(Customer customer) {
        if (customerRepository.existsById(customer.getCustomerID()))
            throw new CustomerException("ERROR: customer id " + customer.getCustomerID() + " already exists");

        return customerRepository.save(customer);
    }


    public Customer update(Customer customer) {
        if (!customerRepository.existsById(customer.getCustomerID()))
            throw new CustomerException("ERROR: customer does not exists");
        return customerRepository.save(customer);
    }


    public Customer delete(String id) {

        Optional<Customer> optionalCustomer = customerRepository.findById(id);


        if (optionalCustomer.isEmpty()) {
            throw new CustomerException("ERROR: customer id " + id + " not exists");
        }
        Customer customer = optionalCustomer.get();
        customerRepository.delete(customer);
        return customer;
    }

}
