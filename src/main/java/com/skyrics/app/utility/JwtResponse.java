package com.skyrics.app.utility;

import com.skyrics.app.payloads.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

	private String jwtToken;
	private boolean success;
	private UserDto user;
}
