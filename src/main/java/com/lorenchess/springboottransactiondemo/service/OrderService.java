package com.lorenchess.springboottransactiondemo.service;

import com.lorenchess.springboottransactiondemo.dto.OrderRequest;
import com.lorenchess.springboottransactiondemo.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}
