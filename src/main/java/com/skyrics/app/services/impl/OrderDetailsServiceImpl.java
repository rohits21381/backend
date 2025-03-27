package com.skyrics.app.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyrics.app.dao.OrderDetailsRepository;
import com.skyrics.app.entities.OrderDetails;
import com.skyrics.app.entities.User;
import com.skyrics.app.payloads.OrderDetailsDto;
import com.skyrics.app.payloads.OrderHistoryItemDto;
import com.skyrics.app.payloads.UserDto;
import com.skyrics.app.services.OrderDetailService;
import com.skyrics.app.services.OrderHistoryItemService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailService{
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OrderHistoryItemService orderHistoryItemService;

	@Override
	public OrderDetailsDto createOrderDetail(UserDto userDto, OrderDetailsDto orderDetailsDto) {
		// TODO Auto-generated method stub
		orderDetailsDto.setUser(userDto);
		LocalDate myObj = LocalDate.now();
		orderDetailsDto.setOrderDetailsDate(myObj);
		OrderDetails orderDetails = modelMapper.map(orderDetailsDto, OrderDetails.class);
		OrderDetails save = orderDetailsRepository.save(orderDetails);
		return  modelMapper.map(save, OrderDetailsDto.class);
	}

	@Override
	public OrderDetailsDto getOrderDetailByOrderDetailsId(Integer orderDetailsId) {
		// TODO Auto-generated method stub
		Optional<OrderDetails> findById = orderDetailsRepository.findById(orderDetailsId);
		OrderDetailsDto detailsDto = modelMapper.map(findById, OrderDetailsDto.class);
		System.out.println("***"+detailsDto);
		return detailsDto;
	}

}
