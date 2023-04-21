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

import com.onneshon.blog.payloads.CategoryDto;
import com.onneshon.blog.services.CategoryServices;

@RestController
@RequestMapping("/api/category")
public class CategoryControllers {

	@Autowired
	CategoryServices catServices;
	
	//creating category
	@PostMapping("/")
	public ResponseEntity<?> addCategory(@RequestBody CategoryDto catDto){
		CategoryDto addCategory = catServices.addCategory(catDto);
		return new ResponseEntity<>(addCategory, HttpStatus.CREATED);
	}
	
	//update category
	@PutMapping("/category/{catId}")
	public ResponseEntity<?> updateCategory(@RequestBody CategoryDto catDto, @PathVariable("catId") int catId){
		CategoryDto updateCategory = catServices.updateCategory(catDto, catId);
		return new ResponseEntity<>(updateCategory, HttpStatus.CREATED);
	}
	
	
}
