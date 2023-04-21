package com.onneshon.blog.payloads;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;


public class ValidationResponse {

//	private Set<ConstraintViolation<UserDto>> violations;
//	
//	public ValidationResponse(Set<ConstraintViolation<UserDto>> violations) {
//		this.violations = (Set<ConstraintViolation<UserDto>>) violations;
//	}
//	
	
	public Map<String, String> sendMessage(Set<ConstraintViolation<UserDto>> violations) {	
		Map<String, String> response = new HashMap<>();	
		
		for(ConstraintViolation<?> violation : violations) {			
			String fieldName = violation.getPropertyPath().toString();
			String message = violation.getMessage();			
			response.put(fieldName, message);			
		}
		
		return response;				
	}
	
}
