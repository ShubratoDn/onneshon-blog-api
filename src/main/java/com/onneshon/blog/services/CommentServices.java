package com.onneshon.blog.services;


import com.onneshon.blog.payloads.CommentDto;

public interface CommentServices {

	//adding comment
	CommentDto addComment(CommentDto commentDto, int blogId);
	
	//deleting comment
	void deleleComment(int commentId);
	
}
