package com.skyrics.app.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class WishListItemDto {


	private Integer wishListItemId;
	
	@JsonIgnore
	private WishListDto wishList;
	
	private ProductDto product;
}
