package com.saroswork.nothwindexample.internal.customer;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService implements Serializable {


    @Autowired
    private CustomerRepository customerRepository;


    public List<Customer> findAll(String filterText) {
        if(filterText == null || filterText.isEmpty())
            return customerRepository.findAll();
        return customerRepository.search(filterText);
    }

    public Customer findById(String id){
        return customerRepository.findByCustomerID(id);
    }


    public Customer insert(Customer customer) {
        if(customer == null)
            throw new CustomerException("ERROR: customer is null");
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

    public List<Customer> findTOP10Customers(){
        List<String> nameTop10 = customerRepository.findTOP10Customers();
        List<Customer> customerList = new ArrayList<>();

        nameTop10.forEach(c -> customerList.add(this.findById(c)));

        return customerList;
    }

}
