package com.saroswork.nothwindexample.internal.repositories;

import com.saroswork.nothwindexample.internal.entities.OrderDetailID;
import com.saroswork.nothwindexample.internal.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailID> {

}
