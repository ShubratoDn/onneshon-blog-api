package com.onneshon.blog.payloads;

import java.util.Date;

import com.onneshon.blog.entities.Category;
import com.onneshon.blog.entities.User;

import jakarta.validation.constraints.Min;
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
	
	@NotNull(message = "Category can not Null")
    @Min(value = 1, message = "Please, insert Category")
	private int categoryId;

	private User user;

	@Override
	public String toString() {
		return "BlogDto [id=" + id + ", blogTitle=" + blogTitle + ", blogContent=" + blogContent + ", blogImage="
				+ blogImage + ", category=" + category + ", addedDate=" + addedDate + ", categoryId=" + categoryId
				+ ", user=" + user + "]";
	}
	
	
	
}
