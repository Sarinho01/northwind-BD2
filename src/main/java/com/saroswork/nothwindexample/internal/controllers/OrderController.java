package com.saroswork.nothwindexample.internal.controllers;

import com.saroswork.nothwindexample.internal.entities.Order;
import com.saroswork.nothwindexample.internal.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("Orders/")

public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/")
    public ResponseEntity<List<Order>> findAll(){
        List<Order> orders = orderRepository.findAll();
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Integer id){
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id não encontrado");
        Order order = optionalOrder.get();

        return ResponseEntity.ok(order);
    }

    @PostMapping("/")
    public ResponseEntity insert(@RequestBody Order order){
        System.out.println(order.toString());
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.ok(savedOrder);
    }


}
