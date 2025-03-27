package com.skyrics.app.entities;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class TransactionDetails {
	
	private String orderId;
    private String currency;
    private Integer amount;
    private String transactionKey;
   
}
