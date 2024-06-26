package com.saroswork.nothwindexample.internal.orderdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;


@Service
public class OrderDetailsService implements Serializable {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;


    public List<OrderDetails> findAll(){
        return orderDetailsRepository.findAll();
    }

    public OrderDetails insert( OrderDetails orderDetails){
        return orderDetailsRepository.save(orderDetails);
    }

    public List<OrderDetails> insertAll(List<OrderDetails> orderDetailsList){
        return orderDetailsRepository.saveAll(orderDetailsList);
    }
    public List<OrderDetails> findByOrderId(Integer id){
        return orderDetailsRepository.findByOrderID(id);
    }


}
