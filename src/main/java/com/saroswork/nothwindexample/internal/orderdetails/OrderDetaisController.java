package com.saroswork.nothwindexample.internal.orderdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderDetaisController {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;


    public List<OrderDetails> findAll(){
        return orderDetailsRepository.findAll();
    }

    public OrderDetails insert( OrderDetails orderDetails){
        return orderDetailsRepository.save(orderDetails);
    }


}
