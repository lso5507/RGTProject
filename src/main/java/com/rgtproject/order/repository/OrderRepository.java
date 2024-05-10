package com.rgtproject.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rgtproject.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
