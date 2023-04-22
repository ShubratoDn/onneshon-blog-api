package com.onneshon.blog.servicesImple;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	@Autowired
	private CategoryServiceImple catService;
	
	@Autowired
	private UserServicesImple userService;
	
	
	@Override
	public BlogDto addBlog(BlogDto blogDto, int userId) {
		//user, category thik ache ki na check kora hocche
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		Category category = categoryRepo.findById(blogDto.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", blogDto.getCategoryId()));
		
		//value set kora hocche
		Blog blog = this.blogDtoToBlog(blogDto);		
		blog.setAddedDate(new Date());
		blog.setUser(user);
		blog.setCategory(category);		
		
		//uploading data
		Blog savedBlog = blogRepo.save(blog);		
		
		return this.blogToBlogDto(savedBlog);
	}

	
	//update blog
	@Override
	public BlogDto updateBlog(BlogDto blogDto, int blogId) {
		//user, category thik ache ki na check kora hocche
		Blog blog = blogRepo.findById(blogId).orElseThrow(()-> new ResourceNotFoundException("Blog", "BlogId", blogId));		
//		Category category = categoryRepo.findById(blogDto.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", blogDto.getCategoryId()));
		
		blog.setBlogTitle(blogDto.getBlogTitle());
		blog.setBlogContent(blogDto.getBlogContent());
		blog.setBlogImage(blog.getBlogImage());
		
		Blog savedBlog = blogRepo.save(blog);
		
		return this.blogToBlogDto(savedBlog);
	}

	

	//deleteblog
	@Override
	public void deleteBlog(int blogId) {		
		Blog blog = blogRepo.findById(blogId).orElseThrow(()-> new ResourceNotFoundException("Blog", "BlogId", blogId));		
		blogRepo.delete(blog);		
	}

	//get blog by id
	@Override
	public BlogDto getBlogById(int blogId) {
		Blog blog = blogRepo.findById(blogId).orElseThrow(()-> new ResourceNotFoundException("Blog", "BlogId", blogId));
		return this.blogToBlogDto(blog);
	}
	
	
	
	//get all blogs
	@Override
	public List<BlogDto> getAllBlogs() {
		
		List<Blog> allBlogs = blogRepo.findAll();
		List<BlogDto> blogs = new ArrayList<>();
		for(Blog blog: allBlogs) {
			blogs.add(this.blogToBlogDto(blog));
		}		
		return blogs;
	}

	
	//get blogs by user id
	@Override
	public List<BlogDto> getAllBlogsByUser(int userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));

		List<Blog> allBlogs = blogRepo.findByUser(user);
		List<BlogDto> blogs = new ArrayList<>();
		for(Blog blog: allBlogs) {
			blogs.add(this.blogToBlogDto(blog));
		}		
		return blogs;
	}

	//get blogs by category id
	@Override
	public List<BlogDto> getAllBlogsByCategory(int catId) {		
		Category category = categoryRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("Category", "category id",catId));
		
		List<Blog> allBlogs = blogRepo.findByCategory(category);
		List<BlogDto> blogs = new ArrayList<>();
		for(Blog blog: allBlogs) {
			blogs.add(this.blogToBlogDto(blog));
		}	
		
		return blogs;
	}
	
	
	
	
	
	
	
	
	//DTO conversion
	public Blog blogDtoToBlog(BlogDto blogDto) {
		Blog blog = new Blog();
		
		blog.setId(blogDto.getId());
		blog.setBlogTitle(blogDto.getBlogTitle());
		blog.setBlogContent(blogDto.getBlogContent());
		blog.setBlogImage(blogDto.getBlogImage());
		blog.setAddedDate(blogDto.getAddedDate());
		
//		blog.setCategory(this.catService.catDtoTocat(blogDto.getCategory()));
//		blog.setUser(this.userService.userDtoToUser(blogDto.getUser()));
		
		return blog;
		
	}
	
	
	public BlogDto blogToBlogDto(Blog blog) {
		BlogDto blogDto = new BlogDto();
		
		blogDto.setId(blog.getId());
		blogDto.setBlogTitle(blog.getBlogTitle());
		blogDto.setBlogContent(blog.getBlogContent());
		blogDto.setBlogImage(blog.getBlogImage());
		blogDto.setAddedDate(blog.getAddedDate());
		blogDto.setCategoryId(blog.getCategory().getCategoryId());
		
		blogDto.setCategory(this.catService.catTocatDto(blog.getCategory()));
		blogDto.setUser(this.userService.userToUserDtoSecure(blog.getUser()));
		
		return blogDto;
		
	}

	
	
	
}
