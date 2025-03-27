package com.skyrics.app.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer postId;
	
	private String productName;
	
	private Double productPrice;
	
	private String productImage;
	
	private String productImage1;
	
	private String productImage2;
	
	private String productImage3;
	
	private String productImage4;
	
	private String productCatagoryTitle;
	
	@Column(length = 1000)
	private String productInf;
	
	@Column(length = 500)
	private String productType;
	
	private Integer productQuantity;
	
	private String productColor;
	
	private String productMaterial;
	
	private String productBrand;
	
	@DateTimeFormat
	private LocalDate addDateProduct;
	
	@Column(length = 5000)
	private String productSpecification;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Category category;
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Comment> comments= new HashSet<>();

}
