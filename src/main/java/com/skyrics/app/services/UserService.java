package com.skyrics.app.services;

import java.util.List;

import com.skyrics.app.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto,Integer userId);
	UserDto getUserById(Integer userId);
	UserDto getUserByUserName(String userName);
	List<UserDto> getAllUser();
	void deleteUser(Integer userId);
}
