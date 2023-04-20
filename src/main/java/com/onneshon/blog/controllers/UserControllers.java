package com.onneshon.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onneshon.blog.payloads.ApiResponse;
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
		UserDto updatedUser = userServices.updateUser(userDto, userId);		
		return new ResponseEntity<UserDto>(updatedUser,HttpStatus.CREATED);
	}
	
	
	//delete user by id
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<ApiResponse> deleteUser( @PathVariable int userId){
		userServices.deleteUser(userId);	
		
		ApiResponse reponse = new ApiResponse("UserDelete", true,"User Deleted");
		
		return new ResponseEntity<ApiResponse>(reponse, HttpStatus.OK);
	}
	
	
	
	
	//get user by id
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserDto> getUser( @PathVariable int userId){
		UserDto user = userServices.getUserById(userId);
		return ResponseEntity.ok(user);		
	}
	
	
	
	//get all user by id
	@GetMapping("/users/")
	public ResponseEntity<List<UserDto>> getAllUser(){
		List<UserDto> allUser = userServices.getAllUser();
		return ResponseEntity.ok(allUser);		
	}
	
	
	
}
