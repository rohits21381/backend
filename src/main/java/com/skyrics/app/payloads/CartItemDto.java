package com.skyrics.app.payloads;

import lombok.Data;

@Data
public class CartItemDto {

	private Integer cartItemId;
	
	private Integer totaleItemQuantity;
	
	private CartDto cart;
	
	private Double totaleProductPrice;
	
	private ProductDto product;
}
