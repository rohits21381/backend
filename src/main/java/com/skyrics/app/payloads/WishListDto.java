package com.skyrics.app.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class WishListDto {


	private Integer wishListId;
	
	@JsonIgnore
	private UserDto user;
	
}
