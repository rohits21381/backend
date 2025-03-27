package com.skyrics.app.payloads;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class OrderDetailsDto {

	private Integer orderDetailsId;
	
	private UserDto user;
	
	private LocalDate OrderDetailsDate;
}
