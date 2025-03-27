package com.skyrics.app.services.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyrics.app.dao.CartItemRepository;
import com.skyrics.app.entities.Cart;
import com.skyrics.app.entities.CartItem;
import com.skyrics.app.exceptions.ResourceNotFoundException;
import com.skyrics.app.payloads.ProductDto;
import com.skyrics.app.payloads.UserDto;
import com.skyrics.app.payloads.CartDto;
import com.skyrics.app.payloads.CartItemDto;
import com.skyrics.app.services.CartItemService;
import com.skyrics.app.services.ProductService;
import com.skyrics.app.services.CartService;

@Service
public class CartItemServiceImpl implements CartItemService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private CartService cartService;
	
	@Override
	public CartItemDto createCartItem(CartItemDto cartItemDto,CartDto cartDto, Integer productId) {
		// TODO Auto-generated method stub
		ProductDto productById = productService.getProductById(productId);
		Integer cartId = cartDto.getCartId();
		System.out.println("cartId; "+cartId);
		if (productById.getProductQuantity() == 0) {
			new ResourceNotFoundException("Product out of stack", "ProductId", productId);
		}
		
		CartItem findCartItemByCartIdAndProductId = cartItemRepository.findCartItemByCartIdAndProductId(cartId, productId);
		if (findCartItemByCartIdAndProductId == null) {
			cartItemDto.setProduct(productById);
			cartItemDto.setCart(cartDto);
			cartItemDto.setTotaleItemQuantity(1);
			cartItemDto.setTotaleProductPrice(productById.getProductPrice());
			CartItem cartItem = modelMapper.map(cartItemDto, CartItem.class);
			CartItem save = cartItemRepository.save(cartItem);
			CartItemDto CartItemDto1 = modelMapper.map(save, CartItemDto.class);
			return CartItemDto1;
		}
		findCartItemByCartIdAndProductId.setTotaleItemQuantity(findCartItemByCartIdAndProductId.getTotaleItemQuantity() + 1);
		findCartItemByCartIdAndProductId.setTotaleProductPrice(productById.getProductPrice() * findCartItemByCartIdAndProductId.getTotaleItemQuantity());
		CartItem save = cartItemRepository.save(findCartItemByCartIdAndProductId);
		CartItemDto CartItemDto1 = modelMapper.map(save, CartItemDto.class);
		return CartItemDto1;
	}
 
	@Override
	public CartItemDto updateCartItem(CartItemDto cartItemDto, Integer cartItemId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<CartItemDto> getAllCartItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCartItemByCartItemId(Integer cartItemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<CartItemDto> findAllCartItemByUser(UserDto userDto) {
		// TODO Auto-generated method stub
		CartDto findCartByUser = cartService.findCartByUser(userDto);
		Cart cart = modelMapper.map(findCartByUser, Cart.class);
		Set<CartItem> findByCart = cartItemRepository.findByCart(cart);
		Set<CartItemDto> cartItemDto = findByCart.stream().map((cart1)->modelMapper.map(cart1, CartItemDto.class)).collect(Collectors.toSet());
		return cartItemDto;
	}

	@Override
	public void deleteCartItemByCartIdAndProductId(UserDto userDto, Integer productId) {
		// TODO Auto-generated method stub
		CartDto findCartByUser = cartService.findCartByUser(userDto);
		Integer cartId = findCartByUser.getCartId();	
		cartItemRepository.deleteCartItemByCartIdAndProductId(cartId, productId);
	}

	@Override
	public CartItemDto findCartItemByCartIdAndProductId(Integer cartId, Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<CartItemDto> findByCart(CartDto cartDto) {
		// TODO Auto-generated method stub
		Cart cart = modelMapper.map(cartDto, Cart.class);
		Set<CartItem> findByCart = cartItemRepository.findByCart(cart);
		Set<CartItemDto> productDtos = findByCart.stream().map((product)-> this.modelMapper.map(product,CartItemDto.class)).collect(Collectors.toSet());
		return productDtos;
	}

	@Override
	public Integer deleteAllCartItemByCartId(Integer cartId) {
		// TODO Auto-generated method stub
		System.out.println("-----------------deleye 1");
		Integer deleteAllCartItemByCartId = cartItemRepository.deleteAllCartItemByCartId(cartId);
		System.out.println("-----------------deleye 2");
		return deleteAllCartItemByCartId;
	}

}
