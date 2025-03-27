package com.skyrics.app.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class OrderHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer orderHistoryId;
	
	@OneToOne
	private User user;
	
	@OneToMany(mappedBy = "orderHistory",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<OrderHistoryItem>  orderHistoryItem = new HashSet<>();
}
