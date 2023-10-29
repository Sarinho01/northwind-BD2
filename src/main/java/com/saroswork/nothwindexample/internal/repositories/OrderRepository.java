package com.saroswork.nothwindexample.internal.repositories;

import com.saroswork.nothwindexample.internal.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
