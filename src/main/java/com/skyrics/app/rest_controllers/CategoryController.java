package com.skyrics.app.rest_controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skyrics.app.payloads.ApiRespons;
import com.skyrics.app.payloads.CategoryDto;
import com.skyrics.app.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	//POST-create user
	@PostMapping("/save")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(createCategory,HttpStatus.CREATED);
	}
	
	
	//PUT-update User
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateUser(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("categoryId") Integer categoryId){
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, categoryId);
		return ResponseEntity.ok(updateCategory);
	}
	
	//DELETE-delete user
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<ApiRespons> deleteCategory(@PathVariable("categoryId") Integer categoryId){
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiRespons>(new ApiRespons("categoryId deleted successfully...", true),HttpStatus.OK);
	}
	
	//GET-get user
	@GetMapping("/all")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
	
		return ResponseEntity.ok(this.categoryService.getAllCategorys());
	}
	
	//GET-get user by id
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleUser(@PathVariable Integer categoryId){
		return ResponseEntity.ok(this.categoryService.getCategoryById(categoryId));
	}
}
