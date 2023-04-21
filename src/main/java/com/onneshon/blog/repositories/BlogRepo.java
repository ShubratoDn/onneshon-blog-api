package com.onneshon.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onneshon.blog.entities.Blog;
import com.onneshon.blog.entities.Category;
import com.onneshon.blog.entities.User;

public interface BlogRepo extends JpaRepository<Blog, Integer>{
	
	List<Blog> findByUser(User user);
	
	List<Blog> findByCategory(Category category);
	
}
