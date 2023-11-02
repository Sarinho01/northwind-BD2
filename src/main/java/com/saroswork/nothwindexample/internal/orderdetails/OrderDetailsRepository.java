package com.saroswork.nothwindexample.internal.orderdetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailID> {
    public List<OrderDetails> findByOrderID(Integer id);

}
