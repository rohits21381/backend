package com.skyrics.app.services;

import java.util.List;

import com.skyrics.app.entities.User;
import com.skyrics.app.payloads.UserDto;
import com.skyrics.app.payloads.WishListDto;

public interface WishListService {

	//Create
	WishListDto createWishList(WishListDto wishListDto,String userName,Integer productId);

	//update
	WishListDto updateWishListByUser(WishListDto wishListDto,User user);

	//getWishListByWishListId
	WishListDto getWishListByWishListId(Integer wishListId);

	//getAllWishList
	List<WishListDto> getAllWishList();

	//deleteWishListByWishListId
	void deleteWishListByWishListId(Integer wishListId);

	//getWishListByWishListId
	WishListDto findWishListByUser(UserDto userDto);
}
