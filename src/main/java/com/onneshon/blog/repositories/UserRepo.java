package com.onneshon.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onneshon.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}