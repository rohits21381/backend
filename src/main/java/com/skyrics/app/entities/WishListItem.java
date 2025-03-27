package com.skyrics.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class WishListItem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer wishListItemId;
	
	@ManyToOne
	private WishList wishList;
	
	@OneToOne
	private Product product;
}
