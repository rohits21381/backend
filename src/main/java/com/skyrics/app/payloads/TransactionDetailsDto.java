package com.skyrics.app.payloads;

import lombok.Data;

@Data
public class TransactionDetailsDto {

	private String orderId;
    private String currency;
    private Integer amount;
    private String transactionKey;

}
