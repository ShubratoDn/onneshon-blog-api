package com.onneshon.blog.services;

import java.util.List;

import com.onneshon.blog.payloads.UserDto;

public interface UserServices {

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
