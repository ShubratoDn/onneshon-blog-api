package com.onneshon.blog.services;


import com.onneshon.blog.payloads.CommentDto;
import com.onneshon.blog.payloads.UserDto;

public interface CommentServices {

	//adding comment
	CommentDto addComment(CommentDto commentDto, int blogId, UserDto user);
	
	//deleting comment
	void deleleComment(int commentId);
	
}
