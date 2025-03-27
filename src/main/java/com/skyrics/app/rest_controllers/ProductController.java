package com.skyrics.app.rest_controllers;

import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skyrics.app.model.ProductSearchCriteria;
import com.skyrics.app.payloads.ApiRespons;
import com.skyrics.app.payloads.ProductDto;
import com.skyrics.app.services.ProductService;
import com.skyrics.app.utility.AppConstant;
import com.skyrics.app.utility.ProductResponse;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

	@Autowired
	private ProductService productService;


	//create
	@PostMapping("/product/{userId}/category/{categoryId}")
	public ResponseEntity<ProductDto> createPost(@RequestBody ProductDto productDto,@PathVariable Integer userId,@PathVariable Integer categoryId){
		ProductDto createProductDto = this.productService.createProduct(productDto, userId, categoryId);
		return new ResponseEntity<ProductDto>(createProductDto,HttpStatus.CREATED);
	}

	//get posts by user
	@GetMapping("/products/user/{userId}")
	public ResponseEntity<Set<ProductDto>> getPostByUser(@PathVariable("userId") Integer userId){
		Set<ProductDto> productDtos = this.productService.getAllProductByUser(userId); 
		return new ResponseEntity<Set<ProductDto>>(productDtos,HttpStatus.OK);
	}

	//get posts by Category
	@GetMapping("/products/category/{categoryId}")
	public ResponseEntity<Set<ProductDto>> getPostByCategory(@PathVariable("categoryId") Integer categoryId){
		Set<ProductDto> allProducttByCategory = this.productService.getAllProductByCategory(categoryId);
		return new ResponseEntity<Set<ProductDto>>(allProducttByCategory,HttpStatus.OK);
	}

	//get all post
	@GetMapping("/products")
	public ResponseEntity<ProductResponse> getAllProduct(
			@RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_Size ,required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false) String sortDir){
		System.out.println("add product work......");
		ProductResponse product = this.productService.getAllProduct(pageNumber, pageSize, sortBy, sortDir);
		return ResponseEntity.ok(product);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<ProductDto> getPostById(@PathVariable("productId") Integer productId){
		ProductDto postById = this.productService.getProductById(productId);
		return new  ResponseEntity<ProductDto>(postById,HttpStatus.OK);
	}

	@DeleteMapping("/product/delete/{productId}")
	public ResponseEntity<ApiRespons> deletePost(@PathVariable("productId") Integer productId) {
		this.productService.deletProduct(productId);
		return new ResponseEntity<ApiRespons>(new ApiRespons("post is successfully deleted",true),HttpStatus.OK);
	}

	@PutMapping("/product/update/{productId}")
	public ResponseEntity<ProductDto> updatePost(@RequestBody ProductDto productDto,@PathVariable("productId") Integer productId) {
		ProductDto updatePost = this.productService.updateProduct(productDto, productId);
		return ResponseEntity.ok(updatePost);
	}

	//search by title
	@GetMapping("/product/title/seach/{keyword}")
	public ResponseEntity<Set<ProductDto>> searchPostBYTitle(@PathVariable("keyword") String keyword){
		Set<ProductDto> searchPost = this.productService.searchProductByTitle(keyword);
		return new ResponseEntity<Set<ProductDto>>(searchPost,HttpStatus.OK);
	}

	//search by content
	@GetMapping("/product/content/seach/{keyword}")
	public ResponseEntity<Set<ProductDto>> searchPos(@PathVariable("keyword") String keyword){
		Set<ProductDto> searchPost = this.productService.searchProductByContent(keyword);
		return new ResponseEntity<Set<ProductDto>>(searchPost,HttpStatus.OK);
	}

	//get all post
	@GetMapping("/products/criteria")
	public ResponseEntity<ProductResponse> getAllProductByCriteria(
			@RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_Size ,required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false) String sortDir,@RequestBody ProductSearchCriteria productSearchCriteria){
		System.out.println("add product search work......");
		String productName = productSearchCriteria.getProductName();
		System.out.println("**********"+productName);

		ProductResponse product = this.productService.getAllProductByCriteria(pageNumber, pageSize, sortBy, sortDir, productSearchCriteria);
		return ResponseEntity.ok(product);
	}
}
