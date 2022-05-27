package com.lorenchess.springboottransactiondemo.service;

import com.lorenchess.springboottransactiondemo.dto.OrderRequest;
import com.lorenchess.springboottransactiondemo.dto.OrderResponse;
import com.lorenchess.springboottransactiondemo.entities.Order;
import com.lorenchess.springboottransactiondemo.entities.Payment;
import com.lorenchess.springboottransactiondemo.exception.PaymentException;
import com.lorenchess.springboottransactiondemo.repository.OrderRepo;
import com.lorenchess.springboottransactiondemo.repository.PaymentRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepo orderRepo;
    private final PaymentRepo paymentRepo;

    public OrderServiceImpl(OrderRepo orderRepo, PaymentRepo paymentRepo) {
        this.orderRepo = orderRepo;
        this.paymentRepo = paymentRepo;
    }

    /**
     * A universally unique identifier (UUID) is a 128-bit label used for information in computer systems.
     * The term globally unique identifier (GUID) is also used. When generated according to the standard methods,
     * UUIDs are, for practical purposes, unique. we use it in this method to set the unique tracking number to the order.
     *
     * In this method we have two main operations,saving the order: orderRepo.save(order) and saving the payment: paymentRepo.save(payment)
     * We need to annotate the class with @Transactional so if the payment fails we are not saving the order into the database in this
     * way we handle multiple transactions in one method;
     * */


    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        //Convert from OrderRequest datatype to Order entity type, this is necessary to save into the database our order
        //Since we have an Order field composed in our OrderResponse DTO class we can use it here
        Order order = orderRequest.getOrder();
        order.setStatus("IN-PROGRESS");
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
        orderRepo.save(order);

        //We are going to support only DEBIT card payments

        Payment payment = orderRequest.getPayment();

        if (!payment.getType().equals("DEBIT")){
          throw new PaymentException("Payment card type not support");
        }
        payment.setOrderId(order.getId());
        paymentRepo.save(payment);

        //Create and set the response that we are sending back to the client
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTackingNumber(order.getOrderTrackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESS");

        return orderResponse;
    }
}
