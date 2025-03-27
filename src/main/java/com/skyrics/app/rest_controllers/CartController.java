package com.skyrics.app.rest_controllers;

import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skyrics.app.payloads.ApiRespons;
import com.skyrics.app.payloads.UserDto;
import com.skyrics.app.payloads.CartDto;
import com.skyrics.app.payloads.CartItemDto;
import com.skyrics.app.services.UserService;
import com.skyrics.app.services.CartItemService;
import com.skyrics.app.services.CartService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {
	
	@Autowired
	private CartService cartService; 
	
	@Autowired
	private CartItemService cartIemService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/save/productId/{productId}")
	public ResponseEntity<CartDto> createCart(@PathVariable("productId") Integer productId,Principal principal){
		String userName = principal.getName();
		CartDto createCart = cartService.createCart(null, userName, productId);
		return new ResponseEntity<CartDto>(createCart,HttpStatus.CREATED);
	}
	
	@GetMapping("/get-all-by-user")
	public ResponseEntity<Set<CartItemDto>> getCartByCUser(Principal principal){
		String name = principal.getName();
		UserDto userByUserName = userService.getUserByUserName(name);
		Set<CartItemDto> findAllCartItemByUser = cartIemService.findAllCartItemByUser(userByUserName);
		return new ResponseEntity<Set<CartItemDto>>(findAllCartItemByUser,HttpStatus.OK);
	}
	
	// Cart product delete productId
	@DeleteMapping("/cart-product/delete/{productId}")
	public ResponseEntity<ApiRespons> deleteCartItemByCartIdAndProductId(@PathVariable("productId") Integer productId,Principal principal) {
		String name = principal.getName();
		UserDto userDto = userService.getUserByUserName(name);
		System.out.println("Cart delet start****************");
		this.cartIemService.deleteCartItemByCartIdAndProductId(userDto, productId);
		return new ResponseEntity<ApiRespons>(new ApiRespons("post is successfully deleted",true),HttpStatus.OK);
	}
}
