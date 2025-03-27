package com.skyrics.app.services;

import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.skyrics.app.model.ProductSearchCriteria;
import com.skyrics.app.payloads.ProductDto;
import com.skyrics.app.utility.ProductResponse;

public interface ProductService {

	//post-create product
	ProductDto createProduct(ProductDto productDto, Integer userId,Integer categoryId);

	ProductDto createProduct(String fileName,String fileName1,String fileName2,String fileName3,String fileName4,ProductDto productDto, String userName,String title);

	//put-update product
	ProductDto updateProduct(ProductDto productDto, Integer productId);

	//get by id product
	ProductDto getProductById(Integer productId);

	//get all product
	ProductResponse getAllProduct(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

	//get all product by criteria
	ProductResponse getAllProductByCriteria(Integer pageNumber,Integer pageSize,String sortBy,String sortDir,ProductSearchCriteria productSearchCriteria);

	//delete product
	void deletProduct(Integer productId);

	//get all product by category
	Set<ProductDto> getAllProductByCategory(Integer categoryId);

	//get all product by user
	Set<ProductDto> getAllProductByUser(Integer userId);

	//search product by title
	Set<ProductDto> searchProductByTitle(String keyword);

	//search product by content
	Set<ProductDto> searchProductByContent(String keyword);

}
