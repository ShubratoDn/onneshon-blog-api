package com.onneshon.blog.payloads;

import java.sql.Date;

import com.onneshon.blog.entities.Category;
import com.onneshon.blog.entities.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlogDto {	
	
	private int id;
	
	@NotEmpty(message = "Insert blog title")
	@Size(min = 5, message = "Minimum 5 character need for title")
	private String blogTitle;
	
	@NotEmpty(message = "Content can not empty")
	private String blogContent;
	
	private String blogImage;

	private Category category;
	
	private Date addedDate;
	
	@NotNull(message = "Category can not empty")
	private int categoryId;

	private User user;
	
}
