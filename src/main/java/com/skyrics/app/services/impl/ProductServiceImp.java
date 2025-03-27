package com.skyrics.app.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.skyrics.app.dao.ProductCriteriaRepository;
import com.skyrics.app.dao.ProductRepository;
import com.skyrics.app.entities.Category;
import com.skyrics.app.entities.Product;
import com.skyrics.app.entities.User;
import com.skyrics.app.exceptions.ResourceNotFoundException;
import com.skyrics.app.model.ProductPage;
import com.skyrics.app.model.ProductSearchCriteria;
import com.skyrics.app.payloads.CategoryDto;
import com.skyrics.app.payloads.ProductDto;
import com.skyrics.app.payloads.UserDto;
import com.skyrics.app.services.CategoryService;
import com.skyrics.app.services.ProductService;
import com.skyrics.app.services.UserService;
import com.skyrics.app.utility.ProductResponse;

@Service
public class ProductServiceImp implements  ProductService{

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductCriteriaRepository productCriteriaRepository;

	@Autowired
	private UserService userService;

	@Autowired
	public CategoryService categoryService;

	@Override
	public ProductDto createProduct(String fileName,String fileName1,String fileName2,String fileName3,String fileName4, ProductDto productDto, String userName,String title) {
		UserDto userDto = userService.getUserByUserName(userName);
		CategoryDto categoryDto = new CategoryDto();
		try {
			categoryDto = this.categoryService.getCategoryByTitle(title);
		} catch (Exception e) {
			categoryDto.setTitle(title);
			categoryService.createCategory(categoryDto);
		}
		Category category = modelMapper.map(categoryDto, Category.class);
		User user = modelMapper.map(userDto, User.class);
		Product product = modelMapper.map(productDto, Product.class);
		LocalDate myObj = LocalDate.now();
		product.setProductImage(fileName);
		product.setProductImage1(fileName1);
		product.setProductImage2(fileName2);
		product.setProductImage3(fileName3);
		product.setProductImage4(fileName4);
		product.setAddDateProduct(myObj);
		product.setUser(user);
		product.setCategory(category);
		Product saveProduct = productRepository.save(product);
		return modelMapper.map(saveProduct, ProductDto.class);
	}

	@Override
	public ProductDto createProduct(ProductDto productDto, Integer userId, Integer categoryId) {
		UserDto userDto = userService.getUserById(userId);
		CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
		Category category = modelMapper.map(categoryDto, Category.class);
		User user = modelMapper.map(userDto, User.class);
		Product product = this.modelMapper.map(productDto,Product.class);
		product.setUser(user);
		product.setCategory(category);
		Product savePost = this.productRepository.save(product);
		return this.modelMapper.map(savePost,ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, Integer productId) {
		Product product = this.productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("product","productId",productId));
		product.setProductName(productDto.getProductName());
		product.setProductPrice(productDto.getProductPrice());
		product.setProductCatagoryTitle(productDto.getProductCatagoryTitle());
		product.setProductImage(productDto.getProductImage());
		product.setProductInf(productDto.getProductInf());
		Product updateProduct = this.productRepository.save(product);
		return this.modelMapper.map(updateProduct,ProductDto.class);
	}

	@Override
	public ProductDto getProductById(Integer productId) {
		Product product = this.productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product","productId",productId));
		return this.modelMapper.map(product,ProductDto.class);
	}

	@Override
	public ProductResponse getAllProduct(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable pageInf = PageRequest.of(pageNumber,pageSize,sort);
		Page<Product> pageAllProduct = productRepository.findAll(pageInf);
		List<Product> Products = pageAllProduct.getContent();
		Set<ProductDto> productDtos = Products.stream().map((post)-> this.modelMapper.map(post, ProductDto.class)).collect(Collectors.toSet());
		ProductResponse productResponse = new ProductResponse();
		productResponse.setContent(productDtos);
		productResponse.setLastPage(pageAllProduct.isLast());
		productResponse.setPageNumber(pageAllProduct.getNumber());
		productResponse.setPageSize(pageAllProduct.getSize());
		productResponse.setTotalPage(pageAllProduct.getTotalPages());
		productResponse.setTotalElement(pageAllProduct.getTotalElements());

		return productResponse;
	}

	@Override
	public void deletProduct(Integer productId){
		Product dlt = this.productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product","productId",productId));
		this.productRepository.delete(dlt);

	}

	@Override
	public Set<ProductDto> getAllProductByCategory(Integer categoryId) {
		CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
		Category category = modelMapper.map(categoryDto, Category.class);
		Set<Product> products = this.productRepository.findByCategory(category);
		Set<ProductDto> productDtos = products.stream().map((product)-> this.modelMapper.map(product,ProductDto.class)).collect(Collectors.toSet());
		return productDtos;
	}

	@Override
	public Set<ProductDto> getAllProductByUser(Integer userId) {
		UserDto userDto = userService.getUserById(userId);
		User user = modelMapper.map(userDto, User.class);
		Set<Product> products = this.productRepository.findByUser(user);
		Set<ProductDto> productDtos = products.stream().map((product)-> this.modelMapper.map(product,ProductDto.class)).collect(Collectors.toSet());
		return productDtos;
	}

	@Override
	public Set<ProductDto> searchProductByTitle(String keyword) {
		Set<Product> findByTitleContaining = this.productRepository.findByProductCatagoryTitle(keyword);
		Set<ProductDto> titleProduct = findByTitleContaining.stream().map((title)->this.modelMapper.map(title,ProductDto.class)).collect(Collectors.toSet());
		return titleProduct;
	}

	@Override
	public Set<ProductDto> searchProductByContent(String keyword) {
		Set<Product> findByContent = this.productRepository.findByContent("%"+keyword+"%");
		Set<ProductDto> contentProduct = findByContent.stream().map((content)->this.modelMapper.map(content,ProductDto.class)).collect(Collectors.toSet());
		return contentProduct;
	}

	@Override
	public ProductResponse getAllProductByCriteria(Integer pageNumber, Integer pageSize, String sortBy,
			String sortDir, ProductSearchCriteria productSearchCriteria) {
		ProductPage productPage = new ProductPage();
		productPage.setPAGE_NUMBER(pageNumber);
		productPage.setPAGE_Size(pageSize);
		productPage.setSORT_BY(sortBy);
		productPage.setSortDirection(sortDir);

		Page<Product> findAllWithFilters = productCriteriaRepository.findAllWithFilters(productPage, productSearchCriteria);
		List<Product> Products = findAllWithFilters.getContent();
		Set<ProductDto> productDtos = Products.stream().map((post)-> this.modelMapper.map(post, ProductDto.class)).collect(Collectors.toSet());
		ProductResponse productResponse = new ProductResponse();
		productResponse.setContent(productDtos);
		productResponse.setLastPage(findAllWithFilters.isLast());
		productResponse.setPageNumber(findAllWithFilters.getNumber());
		productResponse.setPageSize(findAllWithFilters.getSize());
		productResponse.setTotalPage(findAllWithFilters.getTotalPages());
		productResponse.setTotalElement(findAllWithFilters.getTotalElements());

		return productResponse;
	}
}
