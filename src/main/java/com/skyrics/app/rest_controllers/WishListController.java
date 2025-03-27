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
import com.skyrics.app.payloads.WishListDto;
import com.skyrics.app.payloads.WishListItemDto;
import com.skyrics.app.services.UserService;
import com.skyrics.app.services.WishListIemService;
import com.skyrics.app.services.WishListService;

@RestController
@RequestMapping("/api/wishlist")
@CrossOrigin(origins = "http://localhost:4200")
public class WishListController {

	@Autowired
	private WishListService wishListService; 
	
	@Autowired
	private WishListIemService wishListIemService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/save/productId/{productId}")
	public ResponseEntity<WishListDto> createWishList(@PathVariable("productId") Integer productId,Principal principal){
		System.out.println("wishlist save start****************");
		String userName = principal.getName();
		WishListDto createWishList = wishListService.createWishList(null, userName, productId);
		return new ResponseEntity<WishListDto>(createWishList,HttpStatus.CREATED);
	}
	
	@GetMapping("/get-all-by-user")
	public ResponseEntity<Set<WishListItemDto>> getwishlistByCUser(Principal principal){
		System.out.println("wishlist getwishlistByCUser start****************");
		String name = principal.getName();
		UserDto userByUserName = userService.getUserByUserName(name);
		Set<WishListItemDto> findAllWishListItemByUser = wishListIemService.findAllWishListItemByUser(userByUserName);
		return new ResponseEntity<Set<WishListItemDto>>(findAllWishListItemByUser,HttpStatus.OK);
	}
	
	// wishlist product delete productId
	@DeleteMapping("/wishlist-product/delete/{productId}")
	public ResponseEntity<ApiRespons> deleteWishListItemByWishListIdAndProductId(@PathVariable("productId") Integer productId,Principal principal) {
		String name = principal.getName();
		UserDto userDto = userService.getUserByUserName(name);
		this.wishListIemService.deleteWishListItemByWishListIdAndProductId(userDto, productId);
		return new ResponseEntity<ApiRespons>(new ApiRespons("post is successfully deleted",true),HttpStatus.OK);
	}
	
}	