package com.onneshon.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onneshon.blog.payloads.UserDto;
import com.onneshon.blog.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserControllers {
    
	@Autowired
	private UserServices userServices;	
	
	
	//add new user 
	@PostMapping("/user/")
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto){
		UserDto addedUser = userServices.addUser(userDto);		
		return new ResponseEntity<UserDto>(addedUser,HttpStatus.CREATED);
	}
	
	
	//Update New User Info
	@PutMapping("/user/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable int userId){
		UserDto addedUser = userServices.addUser(userDto);		
		return new ResponseEntity<UserDto>(addedUser,HttpStatus.CREATED);
	}
	
	
}
