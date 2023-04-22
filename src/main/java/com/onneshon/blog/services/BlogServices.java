package com.onneshon.blog.services;

import java.util.List;

import com.onneshon.blog.payloads.BlogDto;

public interface BlogServices {

	//create Blog
	BlogDto addBlog(BlogDto blog, int userId);
	
	//update Blog
	BlogDto updateBlog(BlogDto blog, int blogId);
	
	//delete blog
	void deleteBlog(int blogId);
	
	//get blog by id
	BlogDto getBlogById(int blogId);
	
	//get all blogs
	List<BlogDto> getAllBlogs();
	
	//get all blogs by User
	List<BlogDto> getAllBlogsByUser(int userId);
	
	//get all Blogs by category id
	List<BlogDto> getAllBlogsByCategory(int catId);
}
