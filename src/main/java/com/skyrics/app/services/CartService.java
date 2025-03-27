package com.skyrics.app.services;

import java.util.List;

import com.skyrics.app.entities.User;
import com.skyrics.app.payloads.CartDto;
import com.skyrics.app.payloads.UserDto;

public interface CartService {

	//Create
	CartDto createCart(CartDto cartDto,String userName,Integer productId);

	//update
	CartDto updateCartByUser(CartDto cartDto,User user);

	//getCartByCartId
	CartDto getCartByCartId(Integer cartId);

	//getAllCart
	List<CartDto> getAllCart();

	//deleteCartByCartId
	void deleteCartByCartId(Integer cartId);

	//getCartByCartId
	CartDto findCartByUser(UserDto userDto);
}
