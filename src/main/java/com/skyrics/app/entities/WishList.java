package com.skyrics.app.entities;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class WishList {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer wishListId;
	
	@OneToOne
	private User user;
	
	@OneToMany(mappedBy = "wishList",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<WishListItem>  wishListItem = new HashSet<>();
}
