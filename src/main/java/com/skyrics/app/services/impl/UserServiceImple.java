package com.skyrics.app.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skyrics.app.dao.RoleRepository;
import com.skyrics.app.dao.UserRepository;
import com.skyrics.app.entities.Role;
import com.skyrics.app.entities.User;
import com.skyrics.app.exceptions.ResourceNotFoundException;
import com.skyrics.app.payloads.UserDto;
import com.skyrics.app.services.UserService;
import com.skyrics.app.utility.AppConstant;

@Service
public class UserServiceImple implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		user.setPass(this.passwordEncoder.encode(userDto.getPass()));
		Role roles = this.roleRepository.findById(AppConstant.ADMIN).get();
		user.getRoles().add(roles);
		User saveUser = this.userRepository.save(user);
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		user.setAddress(userDto.getAddress());
		user.setCity(userDto.getCity());
		user.setEmail(userDto.getEmail());
		user.setFname(userDto.getFname());
		user.setLname(userDto.getLname());
		user.setNumber(userDto.getNumber());
		user.setState(userDto.getState());
		user.setZipcode(userDto.getZipcode());
		user.setCpass(userDto.getCpass());
		user.setPass(this.passwordEncoder.encode(userDto.getPass()));
		User saveUser = this.userRepository.save(user);
		UserDto userToDto = this.userToDto(saveUser);
		return userToDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = this.userRepository.findAll();
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		this.userRepository.delete(user);
	}
	
	private User dtoToUser(UserDto userDto) {
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPass(userDto.getPass());
//		user.setAbout(userDto.getAbout());
//		return user;
		User map = this.modelMapper.map(userDto, User.class);
		return map;
	}
	
	private UserDto userToDto(User user) {
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPass(user.getPass());
//		userDto.setAbout(user.getAbout());
//		return userDto;
		UserDto map = this.modelMapper.map(user, UserDto.class);
		return map;
	}

	@Override
	public UserDto getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(userName).orElseThrow(()-> new ResourceNotFoundException("User","not Found: "+userName, 0));
		UserDto userDto = modelMapper.map(user,UserDto.class);
		return userDto;
	}

}
