package com.skyrics.app.services;

import java.util.List;
import java.util.Set;

import com.skyrics.app.payloads.UserDto;
import com.skyrics.app.payloads.WishListDto;
import com.skyrics.app.payloads.WishListItemDto;

public interface WishListIemService {

	//Create
	WishListItemDto createWishListItem(WishListItemDto wishListItemDto,WishListDto wishListDto,Integer productID);

	//update
	WishListItemDto updateWishListItem(WishListItemDto wishListItemDto,Integer wishListItemId);

	//getWishListItemByWishListItemId
	WishListItemDto getWishListItemByWishListItemId(Integer wishListItemId);

	//getAllWishListItem
	List<WishListItemDto> getAllWishListItem();

	//deleteWishListItemByWishListItemId
	void deleteWishListItemByWishListItemId(Integer wishListItemId);

	//findAllWishListItemByWishList
	Set<WishListItemDto> findAllWishListItemByUser(UserDto userDto);

	//deleteWishListItemByWishListItemId
	void deleteWishListItemByWishListIdAndProductId(UserDto userDto,Integer productId);
}
