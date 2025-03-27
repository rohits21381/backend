package com.skyrics.app.services.impl;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.skyrics.app.dao.OrderHistoryItemRepository;
import com.skyrics.app.entities.OrderDetails;
import com.skyrics.app.entities.OrderHistory;
import com.skyrics.app.entities.OrderHistoryItem;
import com.skyrics.app.payloads.CartDto;
import com.skyrics.app.payloads.CartItemDto;
import com.skyrics.app.payloads.OrderDetailsDto;
import com.skyrics.app.payloads.OrderHistoryDto;
import com.skyrics.app.payloads.OrderHistoryItemDto;
import com.skyrics.app.payloads.ProductDto;
import com.skyrics.app.payloads.UserDto;
import com.skyrics.app.services.CartItemService;
import com.skyrics.app.services.CartService;
import com.skyrics.app.services.OrderDetailService;
import com.skyrics.app.services.OrderHistoryItemService;
import com.skyrics.app.services.OrderHistoryService;
import com.skyrics.app.services.ProductService;
import com.skyrics.app.utility.OrderItemByUserResponse;
import com.skyrics.app.utility.OrderItemResponse;


@Service
public class OrderHistoryItemServiceImpl implements OrderHistoryItemService{

	private Sort.Direction sort = Sort.Direction.ASC;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private CartService cartService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private ProductService productService ;

	@Autowired
	private OrderHistoryItemRepository orderHistoryItemRepository;

	@Autowired
	private OrderHistoryService orderHistoryService;

	
	@Override
	public OrderHistoryItemDto createOrderHistoryItem(OrderHistoryDto orderHistoryDto,OrderDetailsDto orderDetailsDto, UserDto userDto, Integer productId,String resp) {
		// TODO Auto-generated method stub	
		String paymentMethod=null;
		if(resp==null) {
			paymentMethod="COD";
		}else {
			paymentMethod="Online";
		}
		OrderHistoryItemDto orderHistoryItemDtoSave = null;

		if(productId!=null) {
			OrderDetailsDto orderDetailsDto1 = orderDetailService.createOrderDetail(userDto, orderDetailsDto);
			ProductDto productDto = productService.getProductById(productId);			

			OrderHistoryItemDto orderHistoryItemDto = new OrderHistoryItemDto();
			orderHistoryItemDto.setOrderDetails(orderDetailsDto1);
			orderHistoryItemDto.setOrderHistory(orderHistoryDto);
			orderHistoryItemDto.setProduct(productDto);
			orderHistoryItemDto.setTotaleItemQuantity(1);
			orderHistoryItemDto.setTotaleProductPrice(productDto.getProductPrice());
			LocalDate myObj = LocalDate.now();
			orderHistoryItemDto.setOrderHistoryDate(myObj);
			orderHistoryItemDto.setPaymentOrderId(resp);
			orderHistoryItemDto.setPaymentMethod(paymentMethod);
			orderHistoryItemDto.setOrderStatus("Order Is Plased");
			OrderHistoryItem orderHistoryItem = modelMapper.map(orderHistoryItemDto, OrderHistoryItem.class);
			OrderHistoryItem orderHistoryItemSave = orderHistoryItemRepository.save(orderHistoryItem);
			orderHistoryItemDtoSave = modelMapper.map(orderHistoryItemSave, OrderHistoryItemDto.class);			

		}else {			
			OrderDetailsDto orderDetailsDto1 = orderDetailService.createOrderDetail(userDto, orderDetailsDto);
			CartDto findCartByUser = cartService.findCartByUser(userDto);
			Integer cartId = findCartByUser.getCartId(); 

			Set<CartItemDto> findByCartItemDto = cartItemService.findByCart(findCartByUser);		
			Set<OrderHistoryItemDto>orderHistoryItemDto = findByCartItemDto.stream().map((orderHistory1)->modelMapper.map(orderHistory1, OrderHistoryItemDto.class)).collect(Collectors.toSet());
			
	        orderHistoryItemDtoSave = new OrderHistoryItemDto();
			orderHistoryItemDtoSave.setOrderDetails(orderDetailsDto1);
			orderHistoryItemDtoSave.setOrderHistory(orderHistoryDto);
			LocalDate myObj = LocalDate.now();
			orderHistoryItemDtoSave.setOrderHistoryDate(myObj);
			orderHistoryItemDtoSave.setOrderStatus("Order Is Plased");
			orderHistoryItemDtoSave.setPaymentOrderId(resp);
			orderHistoryItemDtoSave.setPaymentMethod(paymentMethod);
	        for (Iterator iterator = orderHistoryItemDto.iterator(); iterator.hasNext();) {
				OrderHistoryItemDto orderHistoryItemDto2 = (OrderHistoryItemDto) iterator.next();
				orderHistoryItemDto2.setOrderStatus("Order Is Plased");
				orderHistoryItemDto2.setPaymentOrderId(resp);
				orderHistoryItemDto2.setPaymentMethod(paymentMethod);
				orderHistoryItemDto2.setOrderHistory(orderHistoryDto);
				orderHistoryItemDto2.setOrderDetails(orderDetailsDto1);
				LocalDate myObj1 = LocalDate.now();
				orderHistoryItemDto2.setOrderHistoryDate(myObj1);
			}

			Set<OrderHistoryItem> orderHistoryItem = orderHistoryItemDto.stream().map((orderHistoryItem1)->modelMapper.map(orderHistoryItem1, OrderHistoryItem.class)).collect(Collectors.toSet());

			List<OrderHistoryItem> saveAll = orderHistoryItemRepository.saveAll(orderHistoryItem);
			Set<OrderHistoryItemDto> orderHistoryItemDtoSaveAll = saveAll.stream().map((orderHistoryItem1)->modelMapper.map(orderHistoryItem1, OrderHistoryItemDto.class)).collect(Collectors.toSet());
			Integer deleteAllCartItemByCartId = cartItemService.deleteAllCartItemByCartId(cartId);
			

		}

		return orderHistoryItemDtoSave;
	}

	@Override
	public OrderHistoryItemDto updateOrderHistoryItem(OrderHistoryItemDto orderHistoryItemDto, Integer orderHistoryItemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderHistoryItemDto getOrderHistoryItemByOrderHistoryItemId(Integer orderHistoryItemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItemResponse getAllOrderHistoryItem(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		// TODO Auto-generated method stub
		System.out.println("getAllOrderHistoryItem s1");
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable pageInf = PageRequest.of(pageNumber,pageSize,sort);
		System.out.println("getAllOrderHistoryItem s1.2");

		Page<OrderHistoryItem> findAll = orderHistoryItemRepository.findAll(pageInf);
		System.out.println("getAllOrderHistoryItem s1.3");

		List<OrderHistoryItem> orderHistoryItem = findAll.getContent();
		Set<OrderHistoryItemDto> items = orderHistoryItem.stream().map((item)-> this.modelMapper.map(item, OrderHistoryItemDto.class)).collect(Collectors.toSet());
		OrderItemResponse orderItemResponse = new OrderItemResponse();
		orderItemResponse.setContent(items);
		orderItemResponse.setLastPage(findAll.isLast());
		orderItemResponse.setPageNumber(findAll.getNumber());
		orderItemResponse.setPageSize(findAll.getSize());
		orderItemResponse.setTotalElement(findAll.getTotalElements());
		orderItemResponse.setTotalPage(findAll.getTotalPages());
		System.out.println("getAllOrderHistoryItem s1 end");

		return orderItemResponse;
	}

	@Override
	public void deleteOrderHistoryItemByOrderHistoryItemId(Integer orderHistoryItemId) {
		// TODO Auto-generated method stub

	}

	@Override
	public OrderItemByUserResponse findAllOrderHistoryItemByUser(Integer pageNumber,Integer pageSize,String sortBy,String sortDir,UserDto userDto) {
		// TODO Auto-generated method stub
		OrderHistoryDto findOrderHistoryByUser = orderHistoryService.findOrderHistoryByUser(userDto);
		OrderHistory orderHistory = modelMapper.map(findOrderHistoryByUser, OrderHistory.class);
		Integer orderHistoryId = orderHistory.getOrderHistoryId();
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable pageInf = PageRequest.of(pageNumber,pageSize,sort);
		System.out.println(" findAllOrderHistoryItemByUser is work 1********************");
		System.out.println(" findAllOrderHistoryItemByUser is work 1 orderHistoryId: "+orderHistoryId);

		Page<OrderHistoryItem> findByOrderHistory = orderHistoryItemRepository.findByOrderHistory(orderHistoryId,pageInf);
		System.out.println(" findAllOrderHistoryItemByUser is work 2********************");
		
		Set<OrderHistoryItemDto>orderHistoryItemDto = findByOrderHistory.stream().map((OrderHistory1)->modelMapper.map(OrderHistory1, OrderHistoryItemDto.class)).collect(Collectors.toSet());
		
		OrderItemByUserResponse orderItemByUserResponse = new OrderItemByUserResponse();
		orderItemByUserResponse.setContent(orderHistoryItemDto);
		orderItemByUserResponse.setLastPage(findByOrderHistory.isLast());
		orderItemByUserResponse.setPageNumber(findByOrderHistory.getNumber());
		orderItemByUserResponse.setPageSize(findByOrderHistory.getSize());
		orderItemByUserResponse.setTotalElement(findByOrderHistory.getTotalElements());
		orderItemByUserResponse.setTotalPage(findByOrderHistory.getTotalPages());
		return orderItemByUserResponse;
	}

	@Override
	public void deleteOrderHistoryItemByOrderHistoryIdAndProductId(UserDto userDto, Integer productId) {
		// TODO Auto-generated method stub
		OrderHistoryDto findOrderHistoryByUser = orderHistoryService.findOrderHistoryByUser(userDto);
		Integer orderHistoryId = findOrderHistoryByUser.getOrderHistoryId();	
		orderHistoryItemRepository.deleteOrderHistoryItemByOrderHistoryIdAndProductId(orderHistoryId, productId);
	}

	@Override
	public Set<OrderHistoryItemDto> findAllOrderHistoryItemByOrderDetails(Integer orderDetailsId) {
		// TODO Auto-generated method stub
		OrderDetailsDto orderDetailsDto1 = orderDetailService.getOrderDetailByOrderDetailsId(orderDetailsId);
		OrderDetails orderDetails = modelMapper.map(orderDetailsDto1, OrderDetails.class);
		Set<OrderHistoryItem> findByOrderDetails = orderHistoryItemRepository.findByOrderDetails(orderDetails);
		Set<OrderHistoryItemDto> collect = findByOrderDetails.stream().map((details)-> modelMapper.map(details, OrderHistoryItemDto.class)).collect(Collectors.toSet());
		return collect;
	}

}
