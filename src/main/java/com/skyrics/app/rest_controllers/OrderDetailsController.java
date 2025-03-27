package com.skyrics.app.rest_controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skyrics.app.payloads.OrderDetailsDto;
import com.skyrics.app.payloads.OrderHistoryItemDto;
import com.skyrics.app.services.OrderDetailService;
import com.skyrics.app.services.OrderHistoryItemService;


@RestController
@RequestMapping("/api/orderdertails")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderDetailsController {

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private OrderHistoryItemService orderHistoryItemService;

	@GetMapping("/get-by-orderId/{orderDertailsId}")
	public ResponseEntity<OrderDetailsDto> getOrderDetailsByorderDertailsId(@PathVariable("orderDertailsId") Integer orderDertailsId){
		System.out.println("*************orderDetailService controller.............");
		OrderDetailsDto orderDetailsDto= orderDetailService.getOrderDetailByOrderDetailsId(orderDertailsId);
		return new ResponseEntity<OrderDetailsDto>(orderDetailsDto,HttpStatus.OK);

	}

	@GetMapping("/find-order_details-by-orderId/{orderDertailsId}")
	public ResponseEntity<Set<OrderHistoryItemDto>> findAllOrderHistoryItemByOrderDetails(@PathVariable("orderDertailsId") Integer orderDertailsId){
		System.out.println("*************orderDetailService controller.............");
		Set<OrderHistoryItemDto> findAllOrderHistoryItemByOrderDetails = orderHistoryItemService.findAllOrderHistoryItemByOrderDetails(orderDertailsId);
		return new ResponseEntity<Set<OrderHistoryItemDto>>(findAllOrderHistoryItemByOrderDetails,HttpStatus.OK);

	}
}
