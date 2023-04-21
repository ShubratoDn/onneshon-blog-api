package com.onneshon.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

	private int categoryId;	
	private String categoryDescription;
	private String categoryTitle;
	
}
