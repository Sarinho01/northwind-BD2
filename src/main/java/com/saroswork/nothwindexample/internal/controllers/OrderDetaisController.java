package com.saroswork.nothwindexample.internal.controllers;

import com.saroswork.nothwindexample.internal.entities.OrderDetails;
import com.saroswork.nothwindexample.internal.repositories.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/OrderDetails")
public class OrderDetaisController {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @GetMapping("/")
    public ResponseEntity<List<OrderDetails>> findAll(){
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        return ResponseEntity.ok(orderDetailsList);
    }
    @PostMapping("/")
    public ResponseEntity insert(@RequestBody OrderDetails orderDetails){
        System.out.println(orderDetails);
        OrderDetails orderDetailsSaved = orderDetailsRepository.save(orderDetails);
        return ResponseEntity.ok(orderDetailsSaved);
    }


}
