package com.saroswork.nothwindexample.internal.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o " +
            "WHERE CAST(o.orderID AS string) LIKE concat(:searchTerm,'%')")
    List<Order> search(@Param("searchTerm") String searchTerm);
}
