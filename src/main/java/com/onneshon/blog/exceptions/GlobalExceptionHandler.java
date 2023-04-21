package com.onneshon.blog.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	//validation error handler
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler (MethodArgumentNotValidException ex){
		
		Map<String, String> response = new HashMap<>(); 
		response.put("ValidationError", "Invalid Input");
		
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		
		for(ObjectError error : allErrors) {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();			
			response.put(fieldName, message);
		}		
		return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);		
	}
	
	
	
	
	//resource not found Exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, String>> resourceNotFoundExceptionHandler(
			ResourceNotFoundException ex) {

		Map<String, String> response = new HashMap<>();		

		response.put("error", ex.getMessage());
		
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
	}
	
	
	
}