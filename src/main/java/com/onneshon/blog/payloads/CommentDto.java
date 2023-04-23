package com.onneshon.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

	private int id;
	
	@NotEmpty(message = "Write Something!")
	private String content;
	
	private	UserDtoSecure user;
	
}
