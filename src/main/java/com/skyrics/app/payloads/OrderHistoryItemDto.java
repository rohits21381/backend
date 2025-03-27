package com.skyrics.app.payloads;

import java.time.LocalDate;
import lombok.Data;

@Data
public class OrderHistoryItemDto {

	private Integer orderHistoryItemId;

	private Integer totaleItemQuantity;

	private Double totaleProductPrice;

	private String orderStatus;

	private String paymentOrderId;

	private OrderHistoryDto orderHistory;

	private ProductDto product;

	private OrderDetailsDto orderDetails;

	private LocalDate orderHistoryDate;
	
	private String paymentMethod;

}
