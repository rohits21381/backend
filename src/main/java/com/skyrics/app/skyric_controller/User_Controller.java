package com.skyrics.app.skyric_controller;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class User_Controller {
	
//	@Autowired
//	private CartService cartService;
//	
//	@PostMapping("/all-carts/user/{userId}")
//	public ResponseEntity<List<CartDto>> getCartByUserId(@PathVariable Integer userId){
//		List<CartDto> allCartByUser = cartService.getAllCartByUser(userId);
//		return new ResponseEntity<List<CartDto>>(allCartByUser,HttpStatus.OK);	
//	}
}
