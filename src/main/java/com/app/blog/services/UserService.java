package com.app.blog.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.app.blog.payloads.UserDto;


public interface UserService {

	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getuserbyId(Integer userId);
	List<UserDto>getAlluser();
	UserDetailsService userDetailsService();
	
	void deleteUser(Integer userId);
	

	
}
