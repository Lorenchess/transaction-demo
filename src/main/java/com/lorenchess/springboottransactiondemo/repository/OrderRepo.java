package com.lorenchess.springboottransactiondemo.repository;


import com.lorenchess.springboottransactiondemo.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
