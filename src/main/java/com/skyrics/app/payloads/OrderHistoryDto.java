package com.skyrics.app.payloads;

import java.util.HashSet;
import java.util.Set;

import com.skyrics.app.entities.OrderHistoryItem;
import com.skyrics.app.entities.User;

import lombok.Data;

@Data
public class OrderHistoryDto {

	private Integer orderHistoryId;
	
	private UserDto user;
	
}
