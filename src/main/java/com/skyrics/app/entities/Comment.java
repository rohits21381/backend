package com.skyrics.app.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Comment {

	@Id()
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer commentId;

	private String content;
	
	@ManyToOne
	private Product product;
}
