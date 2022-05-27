package com.lorenchess.springboottransactiondemo.repository;

import com.lorenchess.springboottransactiondemo.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment, Long> {

}
