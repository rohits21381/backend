package com.skyrics.app.services;

import java.util.List;

import com.skyrics.app.entities.User;
import com.skyrics.app.payloads.UserDto;
import com.skyrics.app.payloads.OrderHistoryDto;
import com.skyrics.app.payloads.OrderHistoryItemDto;

public interface OrderHistoryService {

	//Create
	OrderHistoryItemDto createOrderHistory(String userName,Integer productId,String resp);

	//update
	OrderHistoryDto updateOrderHistoryByUser(OrderHistoryDto orderHistoryDto,User user);

	//getOrderHistoryByOrderHistoryId
	OrderHistoryDto getOrderHistoryByOrderHistoryId(Integer orderHistoryId);

	//getAllOrderHistory
	List<OrderHistoryDto> getAllOrderHistory();

	//deleteOrderHistoryByOrderHistoryId
	void deleteOrderHistoryByOrderHistoryId(Integer orderHistoryId);

	//getOrderHistoryByOrderHistoryId
	OrderHistoryDto findOrderHistoryByUser(UserDto userDto);
}
