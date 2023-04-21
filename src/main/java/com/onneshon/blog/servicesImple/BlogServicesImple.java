package com.onneshon.blog.servicesImple;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onneshon.blog.entities.Blog;
import com.onneshon.blog.entities.Category;
import com.onneshon.blog.entities.User;
import com.onneshon.blog.exceptions.ResourceNotFoundException;
import com.onneshon.blog.payloads.BlogDto;
import com.onneshon.blog.repositories.BlogRepo;
import com.onneshon.blog.repositories.CategoryRepo;
import com.onneshon.blog.repositories.UserRepo;
import com.onneshon.blog.services.BlogServices;

@Service
public class BlogServicesImple implements BlogServices{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private BlogRepo blogRepo;
	
	@Override
	public BlogDto addBlog(BlogDto blogDto, int userId) {
		
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		Category category = categoryRepo.findById(blogDto.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", blogDto.getCategoryId()));
			
		Blog blog = this.blogDtoToBlog(blogDto);		
		blog.setAddedDate(new Date());
		blog.setUser(user);
		blog.setCategory(category);		
		
		
		System.out.println("Blog Dto:\n"+blogDto);
		System.out.println("Blog:\n"+blog);
		
//		Blog savedBlog = blogRepo.save(blog);
		
		
		return this.blogToBlogDto(blog);
	}

	@Override
	public BlogDto updateBlog(BlogDto blog, int userId) {
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
