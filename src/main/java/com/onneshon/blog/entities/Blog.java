package com.onneshon.blog.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "blogs")
@Getter
@Setter
@NoArgsConstructor
public class Blog {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 500, nullable = false)
	private String blogTitle;
	
	@Column(length = 5000, nullable = false)
	private String blogContent;
	
	@Column(length = 500, nullable = false)
	private String blogImage;

	private Date addedDate;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
}
