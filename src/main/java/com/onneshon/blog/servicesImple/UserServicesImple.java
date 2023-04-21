package com.onneshon.blog.servicesImple;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onneshon.blog.entities.User;
import com.onneshon.blog.exceptions.ResourceNotFoundException;
import com.onneshon.blog.payloads.UserDto;
import com.onneshon.blog.repositories.UserRepo;
import com.onneshon.blog.services.UserServices;

@Service
public class UserServicesImple implements UserServices {	
	
	@Autowired
	UserRepo userRepo;
	
	
	//adding new User
	@Override
	public UserDto addUser(UserDto userDto) {
		User user = this.userDtoToUser(userDto);		
		User addedUser = userRepo.save(user);					
		return userToUserDto(addedUser);
	}

	
	
	//update user info
	@Override
	public UserDto updateUser(UserDto userDto, int userId) {
		User user = new User();
		user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "UserID ", userId));
			
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());		
		
		System.out.println(user.getPassword());
		System.out.println("================================");
		System.out.println("Working");
		System.out.println("================================");
		System.out.println(userDto.getPassword());
		User updatedUser = userRepo.save(user);
		
		return this.userToUserDto(updatedUser);
	}

	
	
	//deleting user
	@Override
	public void deleteUser(int userId) {
		
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "UserID ", userId));
		
		userRepo.delete(user);
		
	}

	
	//get user by id
	@Override
	public UserDto getUserById(int userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "UserID ", userId));		
		return this.userToUserDto(user);
	}
	
	
	//get all user
	@Override
	public List<UserDto> getAllUser() {
		
		List<User> userList =  userRepo.findAll();
		List<UserDto> users = new ArrayList<>();
		
		for(User user : userList){
			users.add(this.userToUserDto(user));
		}
		
		return users;
	}
	
	
	
	
	//user to userDto 
	public UserDto userToUserDto(User user) {
		
		UserDto userDto = new UserDto();
		
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		userDto.setImage(user.getImage());	
		
		return userDto;
	}
	
	
	//user DTo to User
	public User userDtoToUser(UserDto userDto) {		
		User user = new User();
		
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		user.setImage(userDto.getImage());
		
		return user;	
	}


	
	
	

}
