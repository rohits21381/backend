package com.skyrics.app.skyric_controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skyrics.app.payloads.ProductDto;
import com.skyrics.app.services.FileService;
import com.skyrics.app.services.ProductService;


@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class Admin_Controllers {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductService productService;

	@Value("${spring.banner.image.location}")
	private String path;

	@Autowired
	private FileService fileService;

	@PostMapping("/add-product")
	public ResponseEntity<ProductDto> adminSaveProduct(@RequestParam("productDto") String productInf,
			Principal principal,
			@RequestParam("productImage") MultipartFile image,
			@RequestParam("productImage1") MultipartFile image1,
			@RequestParam("productImage2") MultipartFile image2,
			@RequestParam("productImage3") MultipartFile image3,
			@RequestParam("productImage4") MultipartFile image4 ) throws JsonMappingException, JsonProcessingException {
		ProductDto productDto = objectMapper.readValue(productInf, ProductDto.class);
		String uploadImageName = "default.jpg";
		String uploadImageName1 = "default.jpg";
		String uploadImageName2 = "default.jpg";
		String uploadImageName3 = "default.jpg";
		String uploadImageName4 = "default.jpg";
		
		ProductDto createProduct = new ProductDto();
		try {
			uploadImageName = this.fileService.uploadImage(path, image);
			uploadImageName1 = this.fileService.uploadImage(path, image1);
			uploadImageName2 = this.fileService.uploadImage(path, image2);
			uploadImageName3 = this.fileService.uploadImage(path, image3);
			uploadImageName4 = this.fileService.uploadImage(path, image4);
			System.out.println(" image upload  done");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("default.jpg image upload");
		}
		finally {

			String userName = principal.getName();
			String title = productDto.getProductCatagoryTitle();
			createProduct = this.productService.createProduct(uploadImageName,uploadImageName1,uploadImageName2,uploadImageName3,uploadImageName4,productDto,userName,title);
		}
	
		return new ResponseEntity<ProductDto>(createProduct,HttpStatus.CREATED);
	}
}
