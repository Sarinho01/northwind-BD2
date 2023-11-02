package com.saroswork.nothwindexample.internal.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service

public class OrderService implements Serializable {

    @Autowired
    private OrderRepository orderRepository;


    public List<Order> findAll(String searchTerm){
        if(searchTerm == null || searchTerm.isEmpty()){
            return orderRepository.findAll();
        }
        return orderRepository.search(searchTerm);
    }

    public Order findById(Integer id){
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isEmpty())
            throw new OrderException("ERROR: id "+ id +" Not found");

        return optionalOrder.get();
    }


    public Order insert(Order order){
        return orderRepository.save(order);
    }


}
