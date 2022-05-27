package com.lorenchess.springboottransactiondemo.dto;

import com.lorenchess.springboottransactiondemo.entities.Order;
import com.lorenchess.springboottransactiondemo.entities.Payment;
import lombok.Data;

/**
 * A DTO class that contains the data from both entities, so we do not expose our entities data
 * */
@Data
public class OrderRequest {
    private Order order;
    private Payment payment;
}
