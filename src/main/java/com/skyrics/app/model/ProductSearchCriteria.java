package com.skyrics.app.model;

import lombok.Data;

@Data
public class ProductSearchCriteria {

    private String productName;
	private Long productPrice;
	private String productColor;
	private String productCatagoryTitle;
	private String productType;
	private String productBrand;
}
