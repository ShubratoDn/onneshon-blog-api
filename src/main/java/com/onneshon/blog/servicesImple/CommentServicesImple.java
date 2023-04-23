package com.onneshon.blog.servicesImple;

import org.springframework.beans.factory.annotation.Autowired;

import com.onneshon.blog.entities.Blog;
import com.onneshon.blog.entities.Comment;
import com.onneshon.blog.entities.User;
import com.onneshon.blog.exceptions.ResourceNotFoundException;
import com.onneshon.blog.payloads.CommentDto;
import com.onneshon.blog.payloads.UserDto;
import com.onneshon.blog.repositories.BlogRepo;
import com.onneshon.blog.repositories.CommentRepo;
import com.onneshon.blog.repositories.UserRepo;
import com.onneshon.blog.services.CommentServices;

public class CommentServicesImple implements CommentServices {

	@Autowired
	private BlogRepo blogRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Override
	public CommentDto addComment(CommentDto commentDto, int blogId, UserDto userDto) {
		
		Blog blog = blogRepo.findById(blogId).orElseThrow(()-> new ResourceNotFoundException("Blog", "blog id", blogId)); 
		
		User user = userRepo.findById(userDto.getId()).orElseThrow(()-> new ResourceNotFoundException("User", "User id", userDto.getId()));
		
		Comment comment = this.commentDtoToComment(commentDto);
		comment.setBlog(blog);
		comment.setUser(user);		
		Comment savedComment = commentRepo.save(comment);
		
		return this.commentToCommentDto(savedComment);
	}

	
	//deleting comment
	@Override
	public void deleleComment(int commentId) {
		Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment ", "Comment id", commentId));		
		commentRepo.delete(comment);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private UserServicesImple us = new UserServicesImple();
	
	//Comment dto to comment
	Comment commentDtoToComment(CommentDto comDto) {		
		Comment com = new Comment();
		com.setCommentId(comDto.getCommentId());
		com.setContent(comDto.getContent());
		com.setUser(this.us.userDtoSecureToUser(comDto.getUser()));	
		
		return com;		
	}
	
	
	//comment to CommentDto
	CommentDto commentToCommentDto(Comment com) {
		
		CommentDto comDto = new CommentDto();
		
		comDto.setCommentId(com.getCommentId());
		comDto.setContent(com.getContent());		
		comDto.setUser(this.us.userToUserDtoSecure(com.getUser()));	
		
		return comDto;		
	}
	
}
