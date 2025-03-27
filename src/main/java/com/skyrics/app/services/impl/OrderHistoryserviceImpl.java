package com.skyrics.app.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyrics.app.dao.OrderHistoryRepository;
import com.skyrics.app.entities.OrderHistory;
import com.skyrics.app.entities.User;
import com.skyrics.app.payloads.OrderDetailsDto;
import com.skyrics.app.payloads.OrderHistoryDto;
import com.skyrics.app.payloads.OrderHistoryItemDto;
import com.skyrics.app.payloads.UserDto;
import com.skyrics.app.services.OrderHistoryItemService;
import com.skyrics.app.services.OrderHistoryService;
import com.skyrics.app.services.UserService;

@Service
public class OrderHistoryserviceImpl implements OrderHistoryService{
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderHistoryRepository orderHistoryRepository ;


	@Autowired
	private OrderHistoryItemService orderHistoryIemService ;

	@Override
	public OrderHistoryItemDto createOrderHistory(String userName,Integer productId,String resp) {
		// TODO Auto-generated method stub
		UserDto userDto = userService.getUserByUserName(userName);
		User user = modelMapper.map(userDto, User.class);
		OrderHistory orderHistory = orderHistoryRepository.findOrderHistoryByUser(user);
		if (orderHistory==null) {
			orderHistory =new OrderHistory();
			orderHistory.setUser(user);
			orderHistory = orderHistoryRepository.save(orderHistory);
		}
		OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
		OrderHistoryDto orderHistoryDto = modelMapper.map(orderHistory, OrderHistoryDto.class);
		@SuppressWarnings("unused")
		OrderHistoryItemDto orderHistoryItemDto = orderHistoryIemService.createOrderHistoryItem(orderHistoryDto,orderDetailsDto,userDto,productId,resp);
		return modelMapper.map(orderHistoryItemDto, OrderHistoryItemDto.class);
	}

	@Override
	public OrderHistoryDto updateOrderHistoryByUser(OrderHistoryDto orderHistoryDto, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderHistoryDto getOrderHistoryByOrderHistoryId(Integer orderHistoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderHistoryDto> getAllOrderHistory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrderHistoryByOrderHistoryId(Integer orderHistoryId) {
		// TODO Auto-generated method stub

	}

	@Override
	public OrderHistoryDto findOrderHistoryByUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = modelMapper.map(userDto, User.class);
		OrderHistory findOrderHistoryByUser = orderHistoryRepository.findOrderHistoryByUser(user);
		return modelMapper.map(findOrderHistoryByUser, OrderHistoryDto.class);
	}
}
