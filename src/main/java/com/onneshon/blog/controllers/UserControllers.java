package com.onneshon.blog.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onneshon.blog.helpers.FileValidation;
import com.onneshon.blog.payloads.ApiResponse;
import com.onneshon.blog.payloads.UserDto;
import com.onneshon.blog.payloads.ValidationResponse;
import com.onneshon.blog.services.UserServices;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@RestController
@RequestMapping("/api")
public class UserControllers {

	@Autowired
	private UserServices userServices;
	
	@Autowired
	private ObjectMapper mapper;
	
	
	
	// OLD add new user
	@PostMapping("/user/old")
	public ResponseEntity<UserDto> addUserOld(@Valid @RequestBody UserDto userDto,
			@RequestParam("image") MultipartFile file) {

		try {
			System.out.println(file.getName());
		} catch (Exception e) {
			// TODO: handle exception
		}

//		UserDto addedUser = userServices.addUser(userDto);
		UserDto addedUser = new UserDto();
		return new ResponseEntity<UserDto>(addedUser, HttpStatus.CREATED);
	}
	
	
	
	//adding user
	@PostMapping("/user/")
	public ResponseEntity<?> addUser(
			@RequestParam("userData") String userData,
			@RequestParam("image") MultipartFile file) {

		UserDto userDto = null;
		try {
			//STEP 1: converting the user Data into UserDto
			userDto = mapper.readValue(userData, UserDto.class);
		} catch (Exception e) {
			ApiResponse resp = new ApiResponse("InvalidDataConversion", false, "User Data Convert failed");
			return ResponseEntity.badRequest().body(resp);
		}
		
		//STEP 2: Validating User Data
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		
		//checking if there is any error
		if(!violations.isEmpty()) {
			//@Autowire korle problem hoitasilo 
			ValidationResponse validResp = new ValidationResponse();
			Map<String, String> resp = validResp.getErrors(violations);
			return ResponseEntity.badRequest().body(resp);
		}
		
		
		
		//STEP 3: file validation		
		Map<String, String> imageViolation = new FileValidation().imageValidation(file);
		if(!imageViolation.isEmpty()) {
			return ResponseEntity.badRequest().body(imageViolation);
		}
		
		
		

		return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
	}
	
	
	
	
	
	// Update New User Info
	@PutMapping("/user/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable int userId) {
		UserDto updatedUser = userServices.updateUser(userDto, userId);
		return new ResponseEntity<UserDto>(updatedUser, HttpStatus.CREATED);
	}

	// delete user by id
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable int userId) {
		userServices.deleteUser(userId);

		ApiResponse reponse = new ApiResponse("UserDelete", true, "User Deleted");

		return new ResponseEntity<ApiResponse>(reponse, HttpStatus.OK);
	}

	// get user by id
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable int userId) {
		UserDto user = userServices.getUserById(userId);
		return ResponseEntity.ok(user);
	}

	// get all user by id
	@GetMapping("/users/")
	public ResponseEntity<List<UserDto>> getAllUser() {
		List<UserDto> allUser = userServices.getAllUser();
		return ResponseEntity.ok(allUser);
	}





	@PostMapping("/test")
	public ResponseEntity<?> upload(
			@RequestParam("image") MultipartFile file,
			@RequestParam("userData") String userData
			) {
		System.out.println(file.getOriginalFilename());

		UserDto user = null;
		try {

			user = mapper.readValue(userData, UserDto.class);

		} catch (Exception e) {
			ResponseEntity.badRequest().body("bad req");
		}

		System.out.println(user);
		
		
		// Validate the User's object
	    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    Validator validator = factory.getValidator();
	    Set<ConstraintViolation<UserDto>> violations = validator.validate(user);
		
		System.out.println(violations);
		
		 if (violations.isEmpty()) {
		        // Do something with the validated User object
		        // ...
		        return ResponseEntity.ok("User created successfully");
		    } else {		    	
		    	ValidationResponse vr = new ValidationResponse();
		    	Map<String, String> resp = vr.getErrors(violations);
		    	
		    	return ResponseEntity.badRequest().body(resp);
		    }	
		 

	}

}
