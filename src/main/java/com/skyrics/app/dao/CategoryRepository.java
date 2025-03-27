package com.skyrics.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skyrics.app.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer>{

	Category findByTitle(String title);

}
