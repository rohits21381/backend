package com.skyrics.app.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class OrderHistoryItem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer orderHistoryItemId;
	
	private Integer totaleItemQuantity;
	
	private String orderStatus;
	
	private String paymentOrderId;
	
	private String paymentMethod;
	
	private Double totaleProductPrice;
	
	@DateTimeFormat
	private LocalDate orderHistoryDate;
		
	@ManyToOne
	private OrderHistory orderHistory;
	
	@OneToOne
	private Product product;
	
	@ManyToOne
	private OrderDetails orderDetails;
	
}
