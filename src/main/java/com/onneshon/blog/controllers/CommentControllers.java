package com.onneshon.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.onneshon.blog.payloads.ApiResponse;
import com.onneshon.blog.payloads.BlogDto;
import com.onneshon.blog.payloads.CommentDto;
import com.onneshon.blog.services.CommentServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CommentControllers {

	@Autowired
	private CommentServices commentServices;
	
	@PostMapping("/blog/{blogId}/comment")
	public ResponseEntity<?> addComment(
			@Valid @RequestBody CommentDto comment,
			@PathVariable int blogId			
			){
		
		CommentDto addedComment = commentServices.addComment(comment, blogId);		
		
		return ResponseEntity.ok(addedComment);
	}
	
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<?> deleteComment(@PathVariable int commentId){
		commentServices.deleleComment(commentId);
		ApiResponse response  = new ApiResponse("CommentDeletation", true, "Comment Deleted");
		
		return ResponseEntity.ok(response);
		
	}
	
	
	@GetMapping("/comment/blog/{blogId}")
	public ResponseEntity<?> getCommentsByBlog(@PathVariable int blogId){
		BlogDto blogDto = new BlogDto();
		blogDto.setId(blogId);
		
		List<CommentDto> commentsForBlog = commentServices.getCommentsForBlog(blogDto);
		
		return ResponseEntity.ok(commentsForBlog);
	}
	
	
}
