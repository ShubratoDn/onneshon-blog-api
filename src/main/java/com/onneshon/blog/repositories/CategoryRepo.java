package com.onneshon.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onneshon.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	List<Category> findByCategoryTitle(String title);
}
