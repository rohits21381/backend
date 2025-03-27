package com.skyrics.app.utility;

import java.util.Set;

import com.skyrics.app.payloads.ProductDto;

import lombok.Data;

@Data
public class ProductResponse {

	private Set<ProductDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalElement;
	private int totalPage;
	private boolean lastPage;
}
