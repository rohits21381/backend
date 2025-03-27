package com.skyrics.app.services;

import java.util.List;
import com.skyrics.app.payloads.CategoryDto;

public interface CategoryService {

	//create
	 CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//all get
	List<CategoryDto> getAllCategorys();
	
	//single get
	CategoryDto getCategoryById(Integer categoryId);
	
	CategoryDto getCategoryByTitle(String title);
	
	//delete
	void deleteCategory(Integer categoryId);

}
