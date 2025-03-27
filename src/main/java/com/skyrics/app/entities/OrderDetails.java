package com.skyrics.app.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer orderDetailsId;
	
	@ManyToOne
	private User user;
	
	@DateTimeFormat
	private LocalDate OrderDetailsDate;
	
}
