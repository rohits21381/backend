package com.skyrics.app.rest_controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skyrics.app.entities.AcDetail;
import com.skyrics.app.payloads.OrderHistoryItemDto;
import com.skyrics.app.payloads.UserDto;
import com.skyrics.app.services.OrderHistoryItemService;
import com.skyrics.app.services.OrderHistoryService;
import com.skyrics.app.services.UserService;
import com.skyrics.app.utility.AppConstant;
import com.skyrics.app.utility.OrderItemByUserResponse;
import com.skyrics.app.utility.OrderItemResponse;

@RestController
@RequestMapping("/api/orderhistory")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderHistoryController {

	@Autowired
	private OrderHistoryService orderHistoryService; 

	@Autowired
	private OrderHistoryItemService orderHistoryIemService;

	@Autowired
	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	@PostMapping("/save/product")
	public ResponseEntity<OrderHistoryItemDto> createOrderHistoryByProductId(@RequestParam("productId") Integer productId,@RequestParam("resp") String resp,Principal principal){
		String userName = principal.getName();
		String paymentId =null;
		if(resp != "null") {
			try {
				AcDetail readValue = objectMapper.readValue(resp, AcDetail.class);
				paymentId=readValue.getRazorpay_payment_id();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		OrderHistoryItemDto createOrderHistory = orderHistoryService.createOrderHistory(userName,productId,paymentId);
		return new ResponseEntity<OrderHistoryItemDto>(createOrderHistory,HttpStatus.CREATED);
	}

	@PostMapping("/save/cart")
	public ResponseEntity<OrderHistoryItemDto> createOrderHistoryByCart(@RequestParam("resp") String resp, Principal principal){
		String userName = principal.getName();
		Integer productId=null;
		String paymentId =null;
		System.out.println("resp:     "+resp);
		if(resp != "null") {
			try {
				AcDetail readValue = objectMapper.readValue(resp, AcDetail.class);
				paymentId=readValue.getRazorpay_payment_id();
				System.out.println("paymentId:   "+paymentId);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		OrderHistoryItemDto createOrderHistory = orderHistoryService.createOrderHistory(userName,productId,paymentId);
		return new ResponseEntity<OrderHistoryItemDto>(createOrderHistory,HttpStatus.CREATED);
	}

	@GetMapping("/get-all-by-user")
	public ResponseEntity<OrderItemByUserResponse> getOrderHistoryByCUser(
			@RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_Size ,required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false) String sortDir,Principal principal){
		String name = principal.getName();
		UserDto userByUserName = userService.getUserByUserName(name);
		OrderItemByUserResponse findAllOrderHistoryItemByUser = orderHistoryIemService.findAllOrderHistoryItemByUser(pageNumber, pageSize, sortBy, sortDir, userByUserName);
		return new ResponseEntity<OrderItemByUserResponse>(findAllOrderHistoryItemByUser,HttpStatus.OK);
	}

	//get all OrderHistoryItem
	@GetMapping("/items")
	public ResponseEntity<OrderItemResponse> getAllOrderHistoryItem(
			@RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_Size ,required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false) String sortDir){
		OrderItemResponse items = this.orderHistoryIemService.getAllOrderHistoryItem(pageNumber, pageSize, sortBy, sortDir);
		return ResponseEntity.ok(items);
	}


}
