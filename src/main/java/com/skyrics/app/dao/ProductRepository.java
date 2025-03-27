package com.skyrics.app.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.skyrics.app.entities.Category;
import com.skyrics.app.entities.Product;
import com.skyrics.app.entities.User;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	Set<Product> findByCategory(Category category);

	Set<Product> findByUser(User user);
	
	Set<Product> findByProductCatagoryTitle(String keyword);

	@Query(value =  "select * from product where product_inf like %:key%",nativeQuery = true)
	Set<Product> findByContent(@Param("key") String keyword);

	
}
