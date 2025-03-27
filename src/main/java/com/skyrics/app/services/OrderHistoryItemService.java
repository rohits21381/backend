package com.skyrics.app.services;

import java.util.List;
import java.util.Set;

import com.skyrics.app.payloads.UserDto;
import com.skyrics.app.utility.OrderItemByUserResponse;
import com.skyrics.app.utility.OrderItemResponse;
import com.skyrics.app.payloads.OrderDetailsDto;
import com.skyrics.app.payloads.OrderHistoryDto;
import com.skyrics.app.payloads.OrderHistoryItemDto;


public interface OrderHistoryItemService {

	//Create
	OrderHistoryItemDto createOrderHistoryItem(OrderHistoryDto orderHistoryDto,OrderDetailsDto orderDetailsDto, UserDto userDto,Integer productId,String resp);

	//update
	OrderHistoryItemDto updateOrderHistoryItem(OrderHistoryItemDto orderHistoryItemDto,Integer orderHistoryItemId);

	//getOrderHistoryItemByOrderHistoryItemId
	OrderHistoryItemDto getOrderHistoryItemByOrderHistoryItemId(Integer orderHistoryItemId);

	//getAllOrderHistoryItem
	OrderItemResponse getAllOrderHistoryItem(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

	//deleteOrderHistoryItemByOrderHistoryItemId
	void deleteOrderHistoryItemByOrderHistoryItemId(Integer orderHistoryItemId);

	//findAllOrderHistoryItemByOrderHistory
	OrderItemByUserResponse findAllOrderHistoryItemByUser(Integer pageNumber,Integer pageSize,String sortBy,String sortDir,UserDto userDto);

	//deleteOrderHistoryItemByOrderHistoryItemId
	void deleteOrderHistoryItemByOrderHistoryIdAndProductId(UserDto userDto,Integer productId);
	
	Set<OrderHistoryItemDto> findAllOrderHistoryItemByOrderDetails(Integer orderDetailsId);

}
