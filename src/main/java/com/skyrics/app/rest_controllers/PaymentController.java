package com.skyrics.app.rest_controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skyrics.app.entities.TransactionDetails;
import com.skyrics.app.services.TransactionDetailsService;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {
	
	@Autowired
	private TransactionDetailsService createTransactionDetails;

	@PostMapping("/create")
	public ResponseEntity<TransactionDetails> createTransactionDetails(@RequestBody Double amout){
		TransactionDetails createTransaction = createTransactionDetails.createTransaction(amout);
		return new ResponseEntity<TransactionDetails>(createTransaction,HttpStatus.CREATED);
	}
}
