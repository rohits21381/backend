package com.skyrics.app.services.impl;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.skyrics.app.entities.TransactionDetails;
import com.skyrics.app.services.TransactionDetailsService;


@Service
public class TransactionDetailsServiceImpl implements TransactionDetailsService{

    private static final String KEY = "rzp_test_FRgKEQXrrfofvt";
    private static final String KEY_SECRET = "DIQOaDfIQipj5SRZiswcMjkb";
    private static final String CURRENCY = "INR";

    
    @Override
	 public TransactionDetails createTransaction(Double amount) {

	        try {

	            JSONObject jsonObject = new JSONObject();
	            jsonObject.put("amount", (amount * 100) );
	            jsonObject.put("currency", CURRENCY);

	            RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);

	            Order order = razorpayClient.orders.create(jsonObject);
	            TransactionDetails transactionDetails =  prepareTransactionDetails(order);
	            return transactionDetails;
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        return null;
	    }

	    private TransactionDetails prepareTransactionDetails(Order order) {
	        String orderId = order.get("id");
	        String currency = order.get("currency");
	        Integer amount = order.get("amount");

	        TransactionDetails transactionDetails = new TransactionDetails();
	        transactionDetails.setAmount(amount);
	        transactionDetails.setCurrency(currency);
	        transactionDetails.setOrderId(orderId);
	        transactionDetails.setTransactionKey(KEY);
	        return transactionDetails;
	    }

}
