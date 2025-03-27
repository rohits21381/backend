package com.skyrics.app.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyrics.app.dao.WishListRepository;
import com.skyrics.app.entities.User;
import com.skyrics.app.entities.WishList;
import com.skyrics.app.payloads.UserDto;
import com.skyrics.app.payloads.WishListDto;
import com.skyrics.app.payloads.WishListItemDto;
import com.skyrics.app.services.UserService;
import com.skyrics.app.services.WishListIemService;
import com.skyrics.app.services.WishListService;

@Service
public class WishListServiceImpl implements WishListService{

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private WishListRepository wishListRepository ;

	@Autowired
	private WishListIemService wishListIemService ;

	@Override
	public WishListDto createWishList(WishListDto wishListDto, String userName, Integer productId) {
		// TODO Auto-generated method stub
		UserDto userDto = userService.getUserByUserName(userName);
		User user = modelMapper.map(userDto, User.class);
		WishList wishList = wishListRepository.findWishListByUser(user);
		if (wishList==null) {
			wishList =new WishList();
			wishList.setUser(user);
			wishList = wishListRepository.save(wishList);
		}
		
		wishListDto = modelMapper.map(wishList, WishListDto.class);
		WishListItemDto wishListItemDto = new WishListItemDto();
		
		@SuppressWarnings("unused")
		WishListItemDto wishListItem = wishListIemService.createWishListItem(wishListItemDto,wishListDto,productId);
		return modelMapper.map(wishList, WishListDto.class);
	}

	@Override
	public WishListDto updateWishListByUser(WishListDto wishListDto, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WishListDto getWishListByWishListId(Integer wishListId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WishListDto> getAllWishList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteWishListByWishListId(Integer wishListId) {
		// TODO Auto-generated method stub

	}

	@Override
	public WishListDto findWishListByUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = modelMapper.map(userDto, User.class);
		WishList findWishListByUser = wishListRepository.findWishListByUser(user);
		return modelMapper.map(findWishListByUser, WishListDto.class);
	}
}
