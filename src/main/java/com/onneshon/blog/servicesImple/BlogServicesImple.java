package com.onneshon.blog.servicesImple;

import org.springframework.stereotype.Service;

import com.onneshon.blog.entities.Blog;
import com.onneshon.blog.payloads.BlogDto;
import com.onneshon.blog.services.BlogServices;

@Service
public class BlogServicesImple implements BlogServices{

	@Override
	public BlogDto addBlog(BlogDto blog) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlogDto updateBlog(BlogDto blog) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	
	
	
	
	
	//DTO conversion
	Blog blogDtoToBlog(BlogDto blogDto) {
		Blog blog = new Blog();
		
		blog.setId(blogDto.getId());
		blog.setBlogTitle(blogDto.getBlogTitle());
		blog.setBlogContent(blogDto.getBlogContent());
		blog.setBlogImage(blogDto.getBlogImage());
		blog.setAddedDate(blogDto.getAddedDate());
		
		blog.setCategory(blogDto.getCategory());
		blog.setUser(blogDto.getUser());
		
		return blog;
		
	}
	
	
	BlogDto blogToBlogDto(Blog blog) {
		BlogDto blogDto = new BlogDto();
		
		blogDto.setId(blog.getId());
		blogDto.setBlogTitle(blog.getBlogTitle());
		blogDto.setBlogContent(blog.getBlogContent());
		blogDto.setBlogImage(blog.getBlogImage());
		blogDto.setAddedDate(blog.getAddedDate());
		
		blogDto.setCategory(blog.getCategory());
		blogDto.setUser(blog.getUser());
		
		return blogDto;
		
	}
	
	
	
	
}
