package com.skyrics.app.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyrics.app.dao.CartRepository;
import com.skyrics.app.entities.Cart;
import com.skyrics.app.entities.User;
import com.skyrics.app.payloads.CartDto;
import com.skyrics.app.payloads.CartItemDto;
import com.skyrics.app.payloads.UserDto;
import com.skyrics.app.services.CartItemService;
import com.skyrics.app.services.CartService;
import com.skyrics.app.services.UserService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private CartRepository cartRepository ;

	@Autowired
	private CartItemService cartIemService ;

	@Override
	public CartDto createCart(CartDto cartDto, String userName, Integer productId) {
		// TODO Auto-generated method stub
		UserDto userDto = userService.getUserByUserName(userName);
		User user = modelMapper.map(userDto, User.class);
		Cart cart = cartRepository.findCartByUser(user);
		if (cart==null) {
			cart =new Cart();
			cart.setUser(user);
			cart = cartRepository.save(cart);
		}
		
		cartDto = modelMapper.map(cart, CartDto.class);
		CartItemDto cartItemDto = new CartItemDto();
		@SuppressWarnings("unused")
		CartItemDto cartItem = cartIemService.createCartItem(cartItemDto,cartDto,productId);
		return modelMapper.map(cart, CartDto.class);
	}

	@Override
	public CartDto updateCartByUser(CartDto cartDto, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartDto getCartByCartId(Integer cartId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CartDto> getAllCart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCartByCartId(Integer cartId) {
		// TODO Auto-generated method stub

	}

	@Override
	public CartDto findCartByUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = modelMapper.map(userDto, User.class);
		Cart findCartByUser = cartRepository.findCartByUser(user);
		return modelMapper.map(findCartByUser, CartDto.class);
	}
}
