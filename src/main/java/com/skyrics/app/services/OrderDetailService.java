package com.skyrics.app.services;

import java.util.Set;

import com.skyrics.app.payloads.OrderDetailsDto;
import com.skyrics.app.payloads.OrderHistoryItemDto;
import com.skyrics.app.payloads.UserDto;

public interface OrderDetailService {

	// create OrderDetailService
	OrderDetailsDto createOrderDetail(UserDto userDto, OrderDetailsDto orderDetailsDto);
	
	OrderDetailsDto getOrderDetailByOrderDetailsId(Integer orderDetailsId);
}
