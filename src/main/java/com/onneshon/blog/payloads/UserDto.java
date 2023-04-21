package com.onneshon.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;	
	
	@NotEmpty(message = "Name can not be empty")
	@Size(min =3, message = "Name should contain minimum 3 character")
	private String name;
	
	@Size(min = 4, message = "Minimum 4 character required")
	private String password;
	
	
	@Email(message = "Invalid Email")
	private String email;
	
	@NotEmpty(message = "Image Cannot be empty")
	private String image;
	
	@Size(max = 999, message = "Maximum 1000 characters allowed")
	private String about;

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", image="
				+ image + ", about=" + about + "]";
	}
	
	
}
