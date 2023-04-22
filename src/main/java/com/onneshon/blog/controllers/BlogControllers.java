package com.onneshon.blog.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onneshon.blog.payloads.ApiResponse;
import com.onneshon.blog.payloads.BlogDto;
import com.onneshon.blog.payloads.ValidationResponse;
import com.onneshon.blog.services.BlogServices;
import com.onneshon.blog.services.FileService;
import com.onneshon.blog.servicesImple.FileServicesImple;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@RestController
@RequestMapping("/api")
public class BlogControllers {

	@Autowired
	private BlogServices blogServices;
	
	@Autowired
	private ObjectMapper mapper;
	
	//add post
	@PostMapping("/user/{userId}/blog")
	public ResponseEntity<?> addBlog(
			@RequestParam("blogData") String blogData,	
			@RequestParam("blogImage") MultipartFile image,
			@PathVariable int userId
			){
		
		BlogDto blog = null;
		try {
			blog = mapper.readValue(blogData, BlogDto.class);
		} catch (JsonProcessingException e) {
			ApiResponse resp = new ApiResponse("InvalidDataConversion", false, "Invalid Blog data");
			return ResponseEntity.badRequest().body(resp);
		}
		
		
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<BlogDto>> violations = validator.validate(blog);
		
		
		
		// checking if there is any error
		if (!violations.isEmpty()) {
			// @Autowire korle problem hoitasilo
			ValidationResponse validResp = new ValidationResponse();
			Map<String, String> resp = validResp.getBlogErrors(violations);
			return ResponseEntity.badRequest().body(resp);
		}
		
		
		
		FileService file = new FileServicesImple();
		
		Map<String, String> imageViolation = file.blogImageValidation(image);
		if(!imageViolation.isEmpty()) {
			return ResponseEntity.badRequest().body(imageViolation);
		}	
		
		String blogImagePath = file.uploadBlogImage(image);
		if(blogImagePath == null) {
			 return ResponseEntity.badRequest().body(new HashMap<>().put("FileError", "File Upload Fail"));
		}
		
		
		blog.setBlogImage(blogImagePath);		
		BlogDto addedBlog = blogServices.addBlog(blog, userId);
		
		return ResponseEntity.ok(addedBlog);
	}
	
	//update blog
	@PutMapping("/blog/{blogId}")	
	public ResponseEntity<?> updateBlog(@RequestBody BlogDto blogDto, @PathVariable int blogId){
		BlogDto updateBlog = blogServices.updateBlog(blogDto, blogId);
		return ResponseEntity.ok(updateBlog);
	}
	
	
}
