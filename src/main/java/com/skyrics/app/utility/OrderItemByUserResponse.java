package com.skyrics.app.utility;

import java.util.Set;

import com.skyrics.app.payloads.OrderHistoryItemDto;

import lombok.Data;

@Data
public class OrderItemByUserResponse {

	private Set<OrderHistoryItemDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalElement;
	private int totalPage;
	private boolean lastPage;
}
