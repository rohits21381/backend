package com.skyrics.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skyrics.app.entities.Comment;
import com.skyrics.app.entities.Product;

public interface  CommentRepository extends JpaRepository<Comment,Integer>{

	List<Comment> findByProduct(Product product);

	Comment findBycommentId(Integer commentId);
	
}
