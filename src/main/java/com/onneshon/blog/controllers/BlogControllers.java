package com.onneshon.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onneshon.blog.payloads.BlogDto;
import com.onneshon.blog.services.BlogServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class BlogControllers {

	@Autowired
	BlogServices blogServices;
	
	//add post
	@PostMapping("/user/{userId}/blog")
	public ResponseEntity<?> addBlog(
			@Valid @RequestBody BlogDto blogDto,
			@PathVariable int userId
			){
		
		BlogDto addedBlog = blogServices.addBlog(blogDto, userId);
		
		return ResponseEntity.ok(addedBlog);
	}
	
}
