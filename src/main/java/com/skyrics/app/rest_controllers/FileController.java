package com.skyrics.app.rest_controllers;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.skyrics.app.payloads.ProductDto;
import com.skyrics.app.services.FileService;
import com.skyrics.app.services.ProductService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {

	@Autowired
	private FileService fileService;

	@Autowired
	private ProductService productService;

	@Value("${spring.banner.image.location}")
	private String path;


	@PostMapping("/product/image/upload/{productId}")
	public ResponseEntity<ProductDto> uploadFile(@RequestParam("image")MultipartFile image,@PathVariable("productId") Integer productId) throws IOException{

		ProductDto product = this.productService.getProductById(productId);
		String fileName = this.fileService.uploadImage(path, image);
		product.setProductImage(fileName);
		ProductDto updateProduct = this.productService.updateProduct(product, productId);
		return new ResponseEntity<ProductDto>(updateProduct,HttpStatus.OK);
	}
	
	//method to serve file
	@GetMapping(value = "/product/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException {
			InputStream resource = this.fileService.getResource(path, imageName);
			response.setContentType(MediaType.IMAGE_PNG_VALUE);
			StreamUtils.copy(resource,response.getOutputStream());
	}

}