package com.onneshon.blog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 100, nullable = false)
	private String name;
	
	@Column(length = 500, nullable = false)
	private String password;
	
	@Column(length = 200, nullable = false)
	private String email;
	
	@Column(length = 400, nullable = false)
	private String image;
	
	@Column(length = 1000, nullable = false)
	private String about;
	
	
}
