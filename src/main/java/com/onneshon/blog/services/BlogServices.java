package com.onneshon.blog.services;

import com.onneshon.blog.payloads.BlogDto;

public interface BlogServices {

	//create Blog
	BlogDto addBlog(BlogDto blog);
	
	//update Blog
	BlogDto updateBlog(BlogDto blog);
	
	
}
