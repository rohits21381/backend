package com.skyrics.app.services.impl;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyrics.app.dao.WishListItemRepository;
import com.skyrics.app.entities.WishList;
import com.skyrics.app.entities.WishListItem;
import com.skyrics.app.exceptions.ResourceNotFoundException;
import com.skyrics.app.payloads.ProductDto;
import com.skyrics.app.payloads.UserDto;
import com.skyrics.app.payloads.WishListDto;
import com.skyrics.app.payloads.WishListItemDto;
import com.skyrics.app.services.ProductService;
import com.skyrics.app.services.WishListIemService;
import com.skyrics.app.services.WishListService;

@Service
public class WishListIemServiceImpl implements WishListIemService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private WishListItemRepository wishListItemRepository;
	
	@Autowired
	private WishListService wishListService;
	
	@Override
	public WishListItemDto createWishListItem(WishListItemDto wishListItemDto,WishListDto wishListDto, Integer productId) {
		// TODO Auto-generated method stub
		ProductDto productById = productService.getProductById(productId);
		Integer wishListId=wishListDto.getWishListId();
		if (productById.getProductQuantity() == 0) {
			new ResourceNotFoundException("Product out of stack", "ProductId", productId);
		}
		
		WishListItem findWishListItemByWishListIdAndProductId = wishListItemRepository.findWishListItemByWishListIdAndProductId(wishListId, productId);
		if (findWishListItemByWishListIdAndProductId == null) {
			wishListItemDto.setProduct(productById);
			wishListItemDto.setWishList(wishListDto);
			WishListItem wishListItem = modelMapper.map(wishListItemDto, WishListItem.class);
			WishListItem save = wishListItemRepository.save(wishListItem);
			WishListItemDto wishListItemDto1 = modelMapper.map(save, WishListItemDto.class);
			return wishListItemDto1;
		}
		 throw new ResourceNotFoundException("Product already Exist in WishList", "ProductId", productId);
	}

	@Override
	public WishListItemDto updateWishListItem(WishListItemDto wishListItemDto, Integer wishListItemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WishListItemDto getWishListItemByWishListItemId(Integer wishListItemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WishListItemDto> getAllWishListItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteWishListItemByWishListItemId(Integer wishListItemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<WishListItemDto> findAllWishListItemByUser(UserDto userDto) {
		// TODO Auto-generated method stub
		WishListDto findWishListByUser = wishListService.findWishListByUser(userDto);
		WishList wishList = modelMapper.map(findWishListByUser, WishList.class);
		Set<WishListItem> findByWishList = wishListItemRepository.findByWishList(wishList);
		Set<WishListItemDto> wishListItemDto = findByWishList.stream().map((wishList1)->modelMapper.map(wishList1, WishListItemDto.class)).collect(Collectors.toSet());
		return wishListItemDto;
	}

	@Override
	public void deleteWishListItemByWishListIdAndProductId(UserDto userDto, Integer productId) {
		// TODO Auto-generated method stub
		WishListDto findWishListByUser = wishListService.findWishListByUser(userDto);
		Integer wishListId = findWishListByUser.getWishListId();
		wishListItemRepository.deleteWishListItemByWishListIdAndProductId(wishListId, productId);
	}

}
