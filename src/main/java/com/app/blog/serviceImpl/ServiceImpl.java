package com.app.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.blog.entity.User;
import com.app.blog.exceptions.ResouceNotFoundException;
import com.app.blog.payloads.UserDto;
import com.app.blog.repositries.UserRepo;
import com.app.blog.services.UserService;
@Service
public class ServiceImpl implements UserService {

	
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method Uset
	User user =this.dtotoUser(userDto);
	User savedUser=this.userRepo.save(user);
		return userTodto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto , Integer userId) {
		// TODO Auto-generated method stub
	
		User user=this.userRepo.findById(userId)
				.orElseThrow(()-> new ResouceNotFoundException("User","Id",userId));
		
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword()); 
		user.setAbout(userDto.getAbout());
		
		User Updateduser=this.userRepo.save(user);
		
		return this.userTodto(Updateduser);
	}

	@Override
	public UserDto getuserbyId(Integer userId) {
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResouceNotFoundException("User","Id",userId));
		
		return this.userTodto(user);
	}

	@Override
	public List<UserDto> getAlluser() {
		// TODO Auto-generated method stub	
		
		List<User>users=this.userRepo.findAll();
	List< UserDto>userDtos=	users.stream().map(user->userTodto(user)).collect(Collectors.toList());
		
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		
		System.out.println(userId);
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResouceNotFoundException("User","Id", userId));
		this.userRepo.delete(user);
		
	}
	public User dtotoUser(UserDto userDto) {
		
		
		User user =this.modelMapper.map(userDto,User.class);
		
		return user;
		
		 
	}

	public UserDto userTodto(User user) {
		
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		
		return userDto;
		
		
	}

	@Override
	public UserDetailsService userDetailsService() {
		// TODO Auto-generated method stub
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				// TODO Auto-generated method stub
				return userRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User Not Found!!"));
			}
		};
		
	
	}
	
}
