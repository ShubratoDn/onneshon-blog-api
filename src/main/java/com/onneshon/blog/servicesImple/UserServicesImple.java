package com.onneshon.blog.servicesImple;

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
		userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "UserID ", userId));
			
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setImage(userDto.getImage());
		
		User updatedUser = userRepo.save(user);
		
		return this.userToUserDto(updatedUser);
	}

	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDto getUserById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public List<UserDto> getAllUser() {
		// TODO Auto-generated method stub
		return null;
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
