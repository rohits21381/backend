package com.skyrics.app.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyrics.app.dao.CategoryRepository;
import com.skyrics.app.entities.Category;
import com.skyrics.app.exceptions.ResourceNotFoundException;
import com.skyrics.app.payloads.CategoryDto;
import com.skyrics.app.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	public CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category cDtoToC = this.modelMapper.map(categoryDto,Category.class);
		Category saveCategory = this.categoryRepository.save(cDtoToC);
		return this.modelMapper.map(saveCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","category id", categoryId));
		cat.setTitle(categoryDto.getTitle());
		Category updateCat = this.categoryRepository.save(cat);
		return this.modelMapper.map(updateCat,CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategorys() {
		System.out.println("Get all s category start...............");
		List<Category> categories = this.categoryRepository.findAll();
		System.out.println("Get all s1 category...............");
		List<CategoryDto> categoryDto = categories.stream().map((cat)-> this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		System.out.println("Get all s2 category end...............");

		return categoryDto;
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category getCat = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "category id", categoryId));
		
		return this.modelMapper.map(getCat,CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category catDel = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "category id", categoryId));
		this.categoryRepository.delete(catDel);
	}

	@Override
	public CategoryDto getCategoryByTitle(String title) {
		// TODO Auto-generated method stub
		Category categoryByTitle = this.categoryRepository.findByTitle(title);
		return this.modelMapper.map(categoryByTitle,CategoryDto.class);
	}
}
