package com.skyrics.app.payloads;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ProductDto {


	private Integer postId;

    @NotEmpty(message = "not empty name")
	private String productName;

    @NotEmpty(message = "not empty name")
	private Double productPrice;
    
	private String productImage;
	
	private String productImage1;
	
	private String productImage2;
	
	private String productImage3;
	
	private String productImage4;
	
	private LocalDateTime addDateProduct;
	
    @NotEmpty(message = "not empty name")
	private String productCatagoryTitle;
    
    @NotEmpty(message = "not empty name")
	private String productInf;
    
    @NotEmpty(message = "not empty name")
    private String productType;
    
    @NotEmpty(message = "not empty name")
    private Integer productQuantity;
    
    @NotEmpty(message = "not empty name")
    private String productColor;
    
    @NotEmpty(message = "not empty name")
    private String productMaterial;
    
    @NotEmpty(message = "not empty name")
    private String productBrand;
    
    @NotEmpty(message = "not empty name")
    private String productSpecification;
    
    private CategoryDto category;
    
}
