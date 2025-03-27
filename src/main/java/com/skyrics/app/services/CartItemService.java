package com.skyrics.app.services;

import java.util.List;
import java.util.Set;

import com.skyrics.app.payloads.UserDto;
import com.skyrics.app.entities.Cart;
import com.skyrics.app.payloads.CartDto;
import com.skyrics.app.payloads.CartItemDto;

public interface CartItemService {
	//Create
	CartItemDto createCartItem(CartItemDto cartItemDto,CartDto CartDto,Integer productID);

	//update
	CartItemDto updateCartItem(CartItemDto cartItemDto,Integer CartItemId);

	//getCartItemByCartItemId
	CartItemDto findCartItemByCartIdAndProductId(Integer cartId,Integer productId);

	//getAllCartItem
	List<CartItemDto> getAllCartItem();

	//deleteCartItemByCartItemId
	void deleteCartItemByCartItemId(Integer cartItemId);

	//findAllCartItemByCart
	Set<CartItemDto> findAllCartItemByUser(UserDto userDto);
	
	Set<CartItemDto> findByCart(CartDto cartDto);

	//deleteCartItemByCartItemId
	void deleteCartItemByCartIdAndProductId(UserDto userDto,Integer productId);
	
	Integer deleteAllCartItemByCartId(Integer cartId);
}
