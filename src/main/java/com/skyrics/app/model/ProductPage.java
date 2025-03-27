package com.skyrics.app.model;

import org.springframework.data.domain.Sort;

import lombok.Data;

@Data
public class ProductPage {
	private Integer PAGE_NUMBER= 0;
	private Integer PAGE_Size= 10;
	private String SORT_BY= "productName";
	private String sortDirection = "ASC";
	
}
