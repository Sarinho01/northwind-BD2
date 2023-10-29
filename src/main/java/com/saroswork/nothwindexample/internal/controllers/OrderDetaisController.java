package com.saroswork.nothwindexample.internal.controllers;

import com.saroswork.nothwindexample.internal.entities.OrderDetails;
import com.saroswork.nothwindexample.internal.repositories.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/OrderDetails")
public class OrderDetaisController {
    @Autowired
    private OrderDetailsRepository orderDetails;

    @GetMapping("/")
    public ResponseEntity<List<OrderDetails>> findAll(){
        List<OrderDetails> orderDetailsList = orderDetails.findAll();
        return ResponseEntity.ok(orderDetailsList);
    }


}
