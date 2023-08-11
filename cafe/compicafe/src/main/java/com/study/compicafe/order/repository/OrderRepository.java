package com.study.compicafe.order.repository;

import com.study.compicafe.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findById(int orderCode);

}
