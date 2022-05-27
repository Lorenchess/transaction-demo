package com.lorenchess.springboottransactiondemo.dto;

import lombok.Data;
/**
 * The data we are going to send back to the client throughout the http response
 * */
@Data
public class OrderResponse {
    private String orderTackingNumber;
    private String status;
    private String message;
}
