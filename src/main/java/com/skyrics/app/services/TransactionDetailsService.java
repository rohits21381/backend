package com.skyrics.app.services;

import com.skyrics.app.entities.TransactionDetails;
import com.skyrics.app.payloads.TransactionDetailsDto;

public interface TransactionDetailsService {

	TransactionDetails createTransaction(Double amount);
}
