package com.skyrics.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer cartItemId;
	
	private Integer totaleItemQuantity;
	
	private Double totaleProductPrice;
		
	@ManyToOne
	private Cart cart;
	
	@OneToOne
	private Product product;
}
