package com.onneshon.blog.servicesImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.onneshon.blog.entities.Category;
import com.onneshon.blog.payloads.CategoryDto;
import com.onneshon.blog.repositories.CategoryRepo;
import com.onneshon.blog.services.CategoryServices;

public class CategoryServiceImple implements CategoryServices{

	@Autowired
	CategoryRepo catRepo;
	
	
	//Adding Category
	@Override
	public CategoryDto addCategory(CategoryDto catDto) {
		Category cat = this.catDtoTocat(catDto); 
		Category addedCat = catRepo.save(cat);		
		return this.catTocatDto(addedCat);
	}

	
	
	//update category
	@Override
	public CategoryDto updateCategory(CategoryDto catDto) {
		Category cat = this.catDtoTocat(catDto); 		
		cat.setCategoryDescription(catDto.getCategoryDescription());
		cat.setCategoryTitle(catDto.getCategoryTitle());
		
		Category updatedCat = catRepo.save(cat);
		
		return this.catTocatDto(updatedCat);
	}

	@Override
	public void deleteCategory(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CategoryDto getCategoryById(int catId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	//_______________________________________
	//DTO conversion
	//___________________________________
	Category catDtoTocat(CategoryDto catDto) {
		Category cat = new Category();
		
		cat.setCategoryId(catDto.getCategoryId());
		cat.setCategoryDescription(catDto.getCategoryDescription());
		cat.setCategoryTitle(catDto.getCategoryTitle());
		
		return cat;
	}
	
	CategoryDto catTocatDto(Category cat) {
		CategoryDto catDto = new CategoryDto();
		
		catDto.setCategoryId(cat.getCategoryId());
		catDto.setCategoryDescription(cat.getCategoryDescription());
		catDto.setCategoryTitle(cat.getCategoryTitle());
		
		return catDto;
	}
	
	
	
	
}
