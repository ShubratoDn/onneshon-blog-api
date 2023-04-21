package com.onneshon.blog.services;

import com.onneshon.blog.payloads.BlogDto;

public interface BlogServices {

	//create Blog
	BlogDto addBlog(BlogDto blog, int userId);
	
	//update Blog
	BlogDto updateBlog(BlogDto blog, int userId);
	
	
}
