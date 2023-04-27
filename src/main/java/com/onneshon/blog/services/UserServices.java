package com.onneshon.blog.services;

import java.util.List;

import com.onneshon.blog.payloads.UserDto;

public interface UserServices {

	//register New User
	UserDto registerUser(UserDto user);
	
	
	//adding new user
	UserDto addUser(UserDto user);
	
	//Update user
	UserDto updateUser(UserDto user, int userId);
	
	//delete user
	void deleteUser(int userId);
	
	//get user by id
	UserDto getUserById(int userId);
	
	//get all user
	List<UserDto>  getAllUser();
	
}
